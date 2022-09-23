package com.example.adcms_mobileapp_v2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);
        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_position = findViewById(R.id.edit_position);
        final EditText edit_address = findViewById(R.id.edit_address);
        final EditText edit_contacts = findViewById(R.id.edit_contacts);
        Button btn = findViewById(R.id.btn_submit);
        DAOEmployee dao = new DAOEmployee();

        btn.setOnClickListener( v ->
                {
                    Employee emp = new Employee(edit_name.getText().toString(), edit_position.getText().toString(), edit_contacts.getText().toString(), edit_address.getText().toString());
                    dao.add(emp).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(this, "Patient record has been added.", Toast.LENGTH_SHORT).show();

                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();

                    });

                });



           /* HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("name",edit_name.getText().toString());
            hashMap.put("position",edit_position.getText().toString());
            hashMap.put("address",edit_address.getText().toString());
            hashMap.put("contacts",edit_contacts.getText().toString());

            dao.update("-NCZFOdPywxjJepmcfv3", hashMap).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Patient record has been updated.", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();

            });*/

          /*  dao.remove("-NCZFOdPywxjJepmcfv3").addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Patient record has been deleted.", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();

            });*/



        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v ->
        {
            Intent intent = new Intent(PatientActivity.this, RVActivity.class);
            startActivity(intent);
        });

    }
}
