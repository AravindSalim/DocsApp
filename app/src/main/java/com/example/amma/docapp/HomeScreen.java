package com.example.amma.docapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {
    ImageButton patient_button ;
    ImageButton doctor_button ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        doctor_button = (ImageButton)findViewById(R.id.doctor);
        patient_button = (ImageButton)findViewById(R.id.patient);
        doctor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,Doctor_Login.class));
            }
        });
        patient_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,Patient_Login.class));
            }
        });
        if(isOnline())
            Toast.makeText(this, "Connection Established", Toast.LENGTH_SHORT).show();
        else {
            Intent dialogueIntent = new Intent(Settings.ACTION_SETTINGS);
            Toast.makeText(this,"Please turn on Internet Connectivity to Continue",Toast.LENGTH_LONG).show();
            dialogueIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogueIntent);
        }
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }

}
