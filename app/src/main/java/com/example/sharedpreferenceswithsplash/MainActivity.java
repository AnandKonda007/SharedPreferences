package com.example.sharedpreferenceswithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //private static int Splash=3000;
    SharedPreferences preferences;

    //SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*preferences = getSharedPreferences("userinfo", 0);
        editor=preferences.edit();
        if(preferences.getBoolean("isLoggedIn",false)){
            startActivity(new Intent(MainActivity.this,Home.class));
            // Toast.makeText(this, "already logged in", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "please login First", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(MainActivity.this,Login.class));*/
        actions();
    }

    private void actions() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                preferences=getSharedPreferences(Login.PREFS_Name,MODE_PRIVATE);
                boolean isLoggedIn=preferences.getBoolean("isLoggedIn",false);
                if(isLoggedIn){
                    Intent intent=new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                    finish();

                }else{
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                    finish();
                }



            }
        }, 1000);
    }

}