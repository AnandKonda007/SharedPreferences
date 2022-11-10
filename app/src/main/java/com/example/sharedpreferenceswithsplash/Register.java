package com.example.sharedpreferenceswithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Register extends AppCompatActivity {
    EditText email, password, username, Dob, country, pincode, phoneno, address;
    RadioGroup gender;
    Button cancel, register;
    SharedPreferences preferences;
    boolean isAllFieldsChecked = false;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        actions();

    }

    private void actions() {
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        username = findViewById(R.id.username);
        Dob = findViewById(R.id.Dob);
        dobActions();
        country = findViewById(R.id.country);
        pincode = findViewById(R.id.pincode);
        phoneno = findViewById(R.id.phonenumber);
        address = findViewById(R.id.Address);
        gender = findViewById(R.id.gender);
        cancel = findViewById(R.id.cancel);
        register = findViewById(R.id.register2);
        registerActions();


    }

    private void dobActions() {
        Dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                Dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }

        });


    }

    private void registerActions() {
        preferences = getSharedPreferences("userinfo", 0);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    Toast.makeText(Register.this, "Registered Sucessfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));

                } else {
                    Toast.makeText(Register.this, "Please enter Required Fields", Toast.LENGTH_SHORT).show();

                }

                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String usernameValue = username.getText().toString();
                String DobValue = Dob.getText().toString();
                String countryValue = country.getText().toString();
                String pincodeValue = pincode.getText().toString();
                String phonenoValue = phoneno.getText().toString();
                String addressValue = address.getText().toString();
                RadioButton checkbtn = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checkbtn.getText().toString();


                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", emailValue);
                editor.putString("password", passwordValue);
                editor.putString("username", usernameValue);
                editor.putString("Dob", DobValue);
                editor.putString("country", countryValue);
                editor.putString("pincode", pincodeValue);
                editor.putString("phoneno", phonenoValue);
                editor.putString("address", addressValue);
                editor.putString("gender", genderValue);
                editor.apply();


            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyField();
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    private boolean CheckAllFields() {
        if (email.length() == 0 || !email.getText().toString().trim().matches(emailPattern)) {
            email.setError("Please Enter validate email");
            return false;
        }

        if (password.length() == 0) {
            password.setError("This field is required");
            return false;
        }

        if (username.length() == 0) {
            username.setError("username is required");
            return false;
        }

        if (Dob.length() == 0) {
            Dob.setError("Dob is required");
            return false;
        }
        if (country.length() == 0) {
            country.setError("country is required");
            return false;
        }
        if (pincode.length() == 0) {
            pincode.setError("pincode is required");
            return false;
        }
        if (phoneno.length() == 0) {
            phoneno.setError("phoneno is required");
            return false;
        } else if (address.length() == 0) {
            phoneno.setError("address is required");
            return false;
        }


        // after all validation return true.
        return true;
    }

    public void emptyField() {
        email.setText("");
        password.setText("");
        username.setText("");
        Dob.setText("");
        country.setText("");
        pincode.setText("");
        phoneno.setText("");
        address.setText("");
    }
}