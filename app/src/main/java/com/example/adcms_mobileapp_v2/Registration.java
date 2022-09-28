package com.example.adcms_mobileapp_v2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registration extends AppCompatActivity {
    EditText registerFirstname, registerLastName, registerEmailAddress, registerPassword,
            registerConfirmation, registerAddress, registerMobile;
    Button registerBtn, registerLoginBtn;
    FirebaseAuth fAuth;
    FirebaseUser user;

    FloatingActionButton fabDate;
    EditText tvDate;
    EditText tvAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerFirstname = findViewById(R.id.registerFirstName);
        registerLastName = findViewById(R.id.registerLastName);
        registerEmailAddress = findViewById(R.id.registerEmailAddress);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmation = findViewById(R.id.registerConfirmation);
        registerAddress = findViewById(R.id.registerAddress);
        registerMobile = findViewById(R.id.registerMobile);
        registerBtn = findViewById(R.id.registerBtn);
        registerLoginBtn = findViewById(R.id.registerLoginBtn);

        fAuth = FirebaseAuth.getInstance();

        fabDate = findViewById(R.id.fabDate);
        tvDate = findViewById(R.id.tvDate);
        tvAge = findViewById(R.id.tvAge);

        registerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Extract Data from the Form



                String FirstName = registerFirstname.getText().toString();
                String Lastname = registerLastName.getText().toString();
                String Email = registerEmailAddress.getText().toString();
                String Password = registerPassword.getText().toString();
                String ConfirmPW = registerConfirmation.getText().toString();
                String Address = registerAddress.getText().toString();
                String Mobile = registerMobile.getText().toString();



                if (FirstName.isEmpty()) {
                    registerFirstname.setError("First Name is required");
                    registerFirstname.requestFocus();
                    return;
                }

                if (Lastname.isEmpty()) {
                    registerLastName.setError("Last Name is required");
                    registerLastName.requestFocus();
                    return;
                }

                if (Email.isEmpty()) {
                    registerEmailAddress.setError("Email address is required");
                    registerEmailAddress.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    registerEmailAddress.setError("Invalid Email");
                    registerEmailAddress.requestFocus();
                    return;
                }

                if (Password.isEmpty()) {
                    registerPassword.setError("Password is required");
                    registerPassword.requestFocus();
                    return;
                }
                if (Password.length() < 8) {
                    registerPassword.setError("Minimum password length is 8 characters");
                    registerPassword.requestFocus();
                    return;
                }

                if (ConfirmPW.isEmpty()) {
                    registerConfirmation.setError("Password confirmation is required");
                    registerConfirmation.requestFocus();
                    return;
                }
                if (!Password.equals(ConfirmPW)) {
                    registerConfirmation.setError("Password doesn't match");
                }

                if (Address.isEmpty()) {
                    registerAddress.setError("Address is required");
                    registerAddress.requestFocus();
                    return;
                }

                if (Mobile.isEmpty()) {
                    registerMobile.setError("Mobile Number is required");
                    registerMobile.requestFocus();
                    return;

                }





                //Validated Data
                //Register user using firebase



                Toast.makeText(Registration.this, "All information are valid", Toast.LENGTH_SHORT).show();
                fAuth.createUserWithEmailAndPassword(Email, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Send User to Next Page
                       // startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       // finish();

                        fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(Registration.this, "Registered Succesfully Please Verify Email", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(Registration.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                });


            }

        });

        fabDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR, year);
                    c.set(Calendar.MONTH, month);
                    c.set(Calendar.DAY_OF_MONTH, day);
                    String format = new SimpleDateFormat("MMMM dd yyyy").format(c.getTime());
                    tvDate.setText(format);
                    tvAge.setText(Integer.toString(calculateAge(c.getTimeInMillis())));
                }
            };

    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }

}