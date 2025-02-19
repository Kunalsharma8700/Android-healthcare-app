package com.example.androidhealthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edPassword,edEmail,edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextAppFullName);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edEmail = findViewById(R.id.editTextAppAddress);
        edConfirm = findViewById(R.id.editTextAppFees);
        btn = findViewById(R.id.buttonAppBack);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = edUsername.getText().toString();
                String Email = edEmail.getText().toString();
                String Password = edPassword.getText().toString();
                String Confirm = edConfirm.getText().toString();
                Database db=new Database(getApplicationContext(),"Healthcare",null,1);

                if (Username.length() == 0 || Email.length() == 0 || Password.length() == 0 || Confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
                } else {
                    if (Password.compareTo(Confirm) == 0) {
                        if(isValid(Password)){
                                db.Register(Username,Email,Password);
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                        }else{
                            Toast.makeText(getApplicationContext(), "Password must contain 8 characters,having letter,digit,and a special character", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Confirm Password and Password are not Same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
        public static boolean isValid(String passwordhere) {
            int f1 = 0, f2 = 0, f3 = 0;
            if (passwordhere.length() < 8) {
                return false;
            } else {
                for (int p = 0; p < passwordhere.length(); p++) {
                    if (Character.isLetter(passwordhere.charAt(p))) {
                        f1 = 1;
                    }
                }
                for (int r = 0; r < passwordhere.length(); r++) {
                    if (Character.isLetter(passwordhere.charAt(r))) {
                        f2 = 1;
                    }
                }
                    for (int s = 0; s < passwordhere.length(); s++) {
                        char c=passwordhere.charAt(s);
                        if (c>=33&c<=46||c==64) {
                            f3 = 1;
                        }
                    }
                    if(f1==1&&f2==1&&f3==1){
                        return true;

                    }
                }

            return false;
        }}

