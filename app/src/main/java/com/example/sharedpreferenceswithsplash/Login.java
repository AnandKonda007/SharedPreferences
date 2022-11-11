package com.example.sharedpreferenceswithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Login extends AppCompatActivity {
    public static String PREFS_Name = "userinfo";
    EditText email, password;
    Button login, register;
    boolean isAllFieldsChecked = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        actions();
    }

    private void actions() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login1);
        register = findViewById(R.id.register1);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences(Login.PREFS_Name, 0);
                editor = preferences.edit();


                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    String emailValue = email.getText().toString();
                    String passwordValue = password.getText().toString();

                    String registeredEmail = preferences.getString("email", "");
                    String registeredPassword = preferences.getString("password", "");

                    if (emailValue.equals(registeredEmail) && passwordValue.equals(registeredPassword)) {
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                        Toast.makeText(Login.this, "Login  Sucessfull", Toast.LENGTH_SHORT).show();
                    } else if (!email.getText().toString().trim().matches(emailPattern)) {
                        Toast.makeText(Login.this, "Entered Email  Was incorrect ", Toast.LENGTH_SHORT).show();
                    } else if (!emailValue.equals(registeredEmail)) {
                        Toast.makeText(Login.this, "Entered email was incorrect", Toast.LENGTH_SHORT).show();
                    } else if (!passwordValue.equals(registeredPassword)) {
                        Toast.makeText(Login.this, "Entered password was incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "If you want to login First Register With Us", Toast.LENGTH_SHORT).show();

                }


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });


    }

    private boolean CheckAllFields() {
        if (email.length() == 0) {
            email.setError("Email is required");
            return false;
        } else if (password.length() == 0) {
            password.setError("Password is required");
            return false;
        }
        return true;

    }

    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();

    }
}