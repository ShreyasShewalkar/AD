package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

 public class LoginActivity extends AppCompatActivity {

     EditText email, password;
     Button loginBtn;
     TextView registerNow;
     DBHelper db;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_login);

         db = new DBHelper(this);
         email = findViewById(R.id.login_email);
         password = findViewById(R.id.login_password);
         loginBtn = findViewById(R.id.btn_login);
         registerNow = findViewById(R.id.register_link);

         loginBtn.setOnClickListener(v -> {
             String userEmail = email.getText().toString().trim();
             String userPass = password.getText().toString().trim();

             // Validate user input
             if (userEmail.isEmpty() || userPass.isEmpty()) {
                 Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                 return;
             }

             // Check if user exists in DB
             if (db.checkUser(userEmail, userPass)) {
                 Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                 intent.putExtra("email", userEmail);  // Pass email to next screen
                 startActivity(intent);
                 finish();
                 // Proceed to the next screen after successful login
                 // startActivity(new Intent(LoginActivity.this, HomeActivity.class)); // Example
             } else {
                 Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
             }
         });

         registerNow.setOnClickListener(v -> {
             startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
         });
     }
 }

