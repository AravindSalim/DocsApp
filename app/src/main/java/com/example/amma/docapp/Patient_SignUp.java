package com.example.amma.docapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Patient_SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email_signup,password_signup;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_patient__sign_up);
        email_signup = (EditText)findViewById(R.id.email_for_sign_up);
        password_signup = (EditText)findViewById(R.id.pass_for_sign_up);
        signup = (Button)findViewById(R.id.sign_up_button_reg_page);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = email_signup.getText().toString().trim();
        String password = password_signup.getText().toString().trim();

        if(email.isEmpty())
        {
            email_signup.setError("Email is Required");
            email_signup.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_signup.setError("Email is Invalid");
            email_signup.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            password_signup.setError("Password is Required");
            password_signup.requestFocus();
            return;
        }
        if(password.length()<8)
        {
            password_signup.setError("Minimum length of character is 8");
            password_signup.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"User Registered",Toast.LENGTH_SHORT).show();
                              }
                else
                    Toast.makeText(Patient_SignUp.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
