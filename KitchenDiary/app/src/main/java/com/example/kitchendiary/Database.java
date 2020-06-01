package com.example.kitchendiary;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class Database extends AppCompatActivity {
    Button uploadBtn;
    ImageView recImg;
    EditText txt_name, txt_desc, txt_time;
    String imageUrl;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        getSupportActionBar().setTitle("Chef Diary");
        recImg = (ImageView) findViewById(R.id.iv_foodImg);
        txt_name = (EditText) findViewById(R.id.txt_rec_name);
        txt_desc = (EditText) findViewById(R.id.txt_description);
        txt_time = (EditText) findViewById(R.id.text_time);
        uploadBtn=(Button) findViewById(R.id.upbtn);
    }

    public void btnSelectImg(View view) {
        Intent photoPick = new Intent(Intent.ACTION_PICK);
        photoPick.setType("image/*");
        startActivityForResult(photoPick, 1);

    }

    public void uploadImage() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipeImage").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();


                uploadRecipe();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Database.this, "Sorry, something went wrong :(   .", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            recImg.setImageURI(uri);
        } else Toast.makeText(this, "Please pick an image.", Toast.LENGTH_SHORT).show();
    }

    public void btnUpload(View view) {
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt_name.getText().toString().trim();
                String desc = txt_desc.getText().toString().trim();
                String time = txt_time.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(Database.this,"Please insert name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(desc)){
                    Toast.makeText(Database.this,"Please insert description", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(time)){
                    Toast.makeText(Database.this,"Please insert time to prepare dish", Toast.LENGTH_SHORT).show();
                    return;
                }

                else{
                    uploadImage();
                    final ProgressDialog progressDialog = new ProgressDialog(Database.this);
                    progressDialog.setMessage("Image Uploading.....");
                    progressDialog.show();
                }
            }

        });

    }

    public void uploadRecipe() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading.....");
        progressDialog.show();

        FoodData foodData = new FoodData(txt_name.getText().toString(),
                txt_desc.getText().toString(),
                txt_time.getText().toString(),
                imageUrl);
        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Recipe").child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Database.this, "Recipe Uploaded.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Database.this, "Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void btn_home(MenuItem item) {
        startActivity(new Intent(this,MainActivity.class));
    }
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));

    }

}

