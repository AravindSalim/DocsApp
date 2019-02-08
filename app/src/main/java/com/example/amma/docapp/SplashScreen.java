package com.example.amma.docapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import steelkiwi.com.library.DotsLoaderView;

public class SplashScreen extends AppCompatActivity {
   DotsLoaderView dotsLoaderView;
   private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        dotsLoaderView = findViewById(R.id.splashscreen);

        downloadDemo();

        new Handler().postDelayed(new Runnable(){

            @Override

            public void run()
            {
                Intent homeIntent = new Intent(SplashScreen.this,HomeScreen.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

    private void downloadDemo() {
        AsyncTask<String, String, String> demoAsync = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                dotsLoaderView.show();
            }

            @Override
            protected String doInBackground(String... params) {
                try{
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return "done";
            }

            @Override
            protected void onPostExecute(String s) {
                if(s.equals("done"))
                {
                    dotsLoaderView.hide();
                }
            }
        };
        demoAsync.execute();
    }
}
