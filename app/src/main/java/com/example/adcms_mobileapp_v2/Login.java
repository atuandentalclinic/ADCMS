package com.example.adcms_mobileapp_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button createAccountBtn, loginBtn, addpatientBtn;
    EditText username,password, usertype;
    FirebaseAuth firebaseAuth;
    FloatingActionButton fabDate;
    TextView tvDate;
    TextView tvAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = firebaseAuth.getInstance();


        createAccountBtn = findViewById(R.id.loginregisterbtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });

        addpatientBtn = findViewById(R.id.addpatientbtn);
        addpatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PatientActivity.class));
            }
        });


        username = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        usertype = findViewById(R.id.usertype);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Extract  & Validate Data

                if (username.getText().toString().isEmpty()){
                    username.setError("Please specify email");
                    username.requestFocus();
                    return;

                }

                if (password.getText().toString().isEmpty()){
                    password.setError("Please input your password");
                    password.requestFocus();
                    return;
                }

                //Valid data
                //Login User
                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Login Successful

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() !=null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }

}