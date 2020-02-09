package com.example.firebaseauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepage extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView email;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        logout = findViewById(R.id.logout);
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        String getemail = firebaseUser.getEmail();
        email.setText(getemail);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }
}
