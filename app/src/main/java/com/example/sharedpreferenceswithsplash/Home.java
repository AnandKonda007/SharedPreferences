package com.example.sharedpreferenceswithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    TextView username, email, dateOfBirth, phonenumber, address;
    Button button;
    SharedPreferences preferences;
    private long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profileActions();
        buttonActions();
    }

    private void profileActions() {
        username = findViewById(R.id.username2);
        email = findViewById(R.id.email3);
        dateOfBirth = findViewById(R.id.Dob2);
        phonenumber = findViewById(R.id.phonenumber2);
        address = findViewById(R.id.address2);

        preferences = getSharedPreferences("userinfo", 0);

        String Username = preferences.getString("username", "");
        String Email = preferences.getString("email", "");
        String DateOfBirth = preferences.getString("Dob", "");
        String Phonenumber = preferences.getString("phoneno", "");
        String Address = preferences.getString("address", "");


        username.setText("User Name:- " + Username);
        email.setText("Email:- " + Email);
        dateOfBirth.setText("Date Of Birth:- " + DateOfBirth);
        phonenumber.setText("Phone No:- " + Phonenumber);
        address.setText("Address:- " + Address);


    }

    private void buttonActions() {
        button = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=preferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(Home.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, Login.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
            // finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();

    }
}