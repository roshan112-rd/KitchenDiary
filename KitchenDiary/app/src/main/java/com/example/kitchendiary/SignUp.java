package com.example.kitchendiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText txtEmail, txtPassword, txtPassword2;
    Button btn_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Chef Diary");

        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        txtPassword2 = (EditText) findViewById(R.id.txt_password2);
        btn_register=(Button)findViewById(R.id.regButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  txtEmail.getText().toString().trim();
                String password =  txtPassword.getText().toString();
                String password2 =  txtPassword2.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this, "please enter email ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this, "please enter password ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password2)){
                    Toast.makeText(SignUp.this, "please enter confirm password ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length()<6){
                    Toast.makeText(SignUp.this, "Short password ", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                if (password.equals(password2)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(SignUp.this, "Registration Successful. ", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(SignUp.this, "Authentication failed. ", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });



                }
                else{
                    Toast.makeText(SignUp.this, "Password didn't match. ", Toast.LENGTH_SHORT).show();
                    return;}
            }
        });



    }

    public void btnLogin(View view) {
        startActivity(new Intent(getApplicationContext(),LogIn.class));
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

