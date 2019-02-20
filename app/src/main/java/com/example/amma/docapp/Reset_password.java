package com.example.amma.docapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_password extends AppCompatActivity {
    Button reset;
    EditText email;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        reset = (Button)findViewById(R.id.reset_button);
        email = (EditText)findViewById(R.id.email_reset);
        mAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordreset();

            }
        });
    }

    private void passwordreset() {
        String mail = email.getText().toString().trim();
        if(mail.equals(""))
        {
            Toast.makeText(getApplicationContext(),"PLease Enter ",Toast.LENGTH_LONG).show();
        }
        mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Reset_password.this, "Mail sent to Email",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Reset_password.this,Patient_Login.class));
                }
                else
                {
                    Toast.makeText(Reset_password.this, "Mail not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
