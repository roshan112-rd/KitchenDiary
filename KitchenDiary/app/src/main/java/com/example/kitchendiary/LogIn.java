package com.example.kitchendiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    EditText txtEmail,txtPass;
    Button btn_login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().setTitle("Chef Diary");

        txtEmail=(EditText) findViewById(R.id.txt_email2);
        txtPass=(EditText)findViewById(R.id.txt_password2);
        btn_login=(Button) findViewById(R.id.btnLogin);
        firebaseAuth= FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogIn.this, "Logging in, please wait...", Toast.LENGTH_SHORT).show();
                String email = txtEmail.getText().toString().trim();
                String pass = txtPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LogIn.this, "Please insert email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(LogIn.this, "Please insert password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (pass.length() < 6) {
                    Toast.makeText(LogIn.this, "Password too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   startActivity(new Intent(getApplicationContext(),Database.class));


                                } else {
                                    Toast.makeText(LogIn.this, "Login Failed or User Not Avilable", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }


    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp.class));
    }
    public void btn_skip(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void btn_home(MenuItem item) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
