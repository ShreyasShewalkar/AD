package com.example.expt4;

import android.content.*;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner subjectSpinner;
    RadioGroup genderRadioGroup;
    CheckBox qualification1, qualification2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectSpinner = findViewById(R.id.subjectSpinner);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        qualification1 = findViewById(R.id.qualificationCheckbox1);
        qualification2 = findViewById(R.id.qualificationCheckbox2);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            String subject = subjectSpinner.getSelectedItem() + "";
            int genderId = genderRadioGroup.getCheckedRadioButtonId();
            String gender = genderId != -1 ? ((RadioButton) findViewById(genderId)).getText().toString() : "Not Selected";
            String q = "";
            if (qualification1.isChecked()) q += "Bachelors ";
            if (qualification2.isChecked()) q += "Masters";

            SharedPreferences.Editor editor = getSharedPreferences("RegistrationData", MODE_PRIVATE).edit();
            editor.putString("subject", subject);
            editor.putString("gender", gender);
            editor.putString("qualifications", q.trim());
            editor.apply();

            Intent i = new Intent(this, ShowActivity.class);
            i.putExtra("subject", subject);
            i.putExtra("gender", gender);
            i.putExtra("qualifications", q.trim());
            startActivity(i);
        });
    }
}
