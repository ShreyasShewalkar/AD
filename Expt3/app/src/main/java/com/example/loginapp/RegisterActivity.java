package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, confirmPassword;
    Button registerBtn;
    TextView loginNow;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.register_confirm_password);
        registerBtn = findViewById(R.id.btn_register);
        loginNow = findViewById(R.id.login_link);

        registerBtn.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();
            String userConf = confirmPassword.getText().toString().trim();

            // Validate inputs
            if (userEmail.isEmpty() || userPass.isEmpty() || userConf.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!userPass.equals(userConf)) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            } else {
                // Check if email already exists
                if (db.checkEmailExists(userEmail)) {
                    Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert user into DB
                    boolean inserted = db.insertUser(userEmail, userPass);
                    if (inserted) {
                        Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });




        loginNow.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

    }
}

