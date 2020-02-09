package com.example.firebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login,signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginbtn);
        signup = findViewById(R.id.signupbtn);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),secondActivity.class);
                startActivity(i);
            }
        });

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String getEmail = email.getText().toString();
            String getPassword = password.getText().toString();
            loginmethod(getEmail,getPassword);

        }
    });
    }
    private void loginmethod(String getEmail,String getPassword) {
        mAuth.signInWithEmailAndPassword(getEmail,getPassword)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        finish();
                        Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),homepage.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Please check your email and password",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    protected void onStart() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser != null) {
            finish();
            Intent i = new Intent(getApplicationContext(),homepage.class);
            startActivity(i);
        }
        super.onStart();
    }
}
