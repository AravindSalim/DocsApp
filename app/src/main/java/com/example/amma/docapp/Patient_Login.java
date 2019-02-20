package com.example.amma.docapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Patient_Login extends AppCompatActivity {
    FirebaseAuth mAuth;
      Button patient_register,Log_In ;
      EditText email_sign_in,password_sign_in;
      TextView forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login);
        patient_register =(Button)findViewById(R.id.patient_sign_up_button);
        patient_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Patient_Login.this,Patient_SignUp.class));
            }
        });
        email_sign_in = (EditText)findViewById(R.id.patient_email_for_sign_in);
        password_sign_in = (EditText)findViewById(R.id.patient_pass_for_sign_in);
        Log_In = (Button)findViewById(R.id.patient_sign_in_button);
        forgot_password = (TextView)findViewById(R.id.forg_password);
        mAuth = FirebaseAuth.getInstance();
        Log_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_function();
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Reset_password.class);
                startActivity(intent);
            }
        });

    }

    private void login_function() {
        String email = email_sign_in.getText().toString().trim();
        String password = password_sign_in.getText().toString().trim();

        if(email.isEmpty())
        {
            email_sign_in.setError("Email is Required");
            email_sign_in.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_sign_in.setError("Email is Invalid");
            email_sign_in.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            password_sign_in.setError("Password is Required");
            password_sign_in.requestFocus();
            return;
        }
        if(password.length()<8)
        {
            password_sign_in.setError("Minimum length of character is 8");
            password_sign_in.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Sign-In Successfull!!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Register First to Continue",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
