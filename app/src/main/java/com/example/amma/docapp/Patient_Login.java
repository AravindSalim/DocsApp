package com.example.amma.docapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Patient_Login extends AppCompatActivity {
      Button patient_register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login);
        patient_register =(Button)findViewById(R.id.patient_sign_up_button);;
        patient_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patient_Login.this,Patient_SignUp.class));
            }
        });
    }
}
