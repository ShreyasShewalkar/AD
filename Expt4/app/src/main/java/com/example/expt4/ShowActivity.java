package com.example.expt4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView subject = findViewById(R.id.subjectTextView);
        TextView gender = findViewById(R.id.genderTextView);
        TextView qual = findViewById(R.id.qualificationsTextView);

        SharedPreferences sp = getSharedPreferences("RegistrationData", MODE_PRIVATE);
        subject.setText("Subject: " + sp.getString("subject", ""));
        gender.setText("Gender: " + sp.getString("gender", ""));
        qual.setText("Qualifications: " + sp.getString("qualifications", ""));
    }
}
