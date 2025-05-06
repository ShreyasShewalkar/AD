package com.example.expt9;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Environment;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    String data = editText.getText().toString();
                    saveDataExternal(data);
                } else {
                    Toast.makeText(this, "Permission denied to write to external storage.", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Button saveInternalButton = findViewById(R.id.saveInternalButton);
        Button saveExternalButton = findViewById(R.id.saveExternalButton);
        Button loadButton = findViewById(R.id.loadButton);

        saveInternalButton.setOnClickListener(v -> {
            String data = editText.getText().toString();
            saveDataInternal(data);
        });

        saveExternalButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
            } else {
                String data = editText.getText().toString();
                saveDataExternal(data);
            }
        });

        loadButton.setOnClickListener(v -> loadData());
    }

    private void saveDataInternal(String data) {
        try {
            File file = new File(getFilesDir(), "data.txt");
            FileStorageHelper.writeToFile(file, data);
            Toast.makeText(this, "Data saved to internal storage!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDataExternal(String data) {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            File file = new File(getExternalFilesDir(null), "data.txt");
            try {
                FileStorageHelper.writeToFile(file, data);
                Toast.makeText(this, "Data saved to external storage!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error saving data.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "External storage is not available.", Toast.LENGTH_SHORT).show();
        }
    }


    private void loadData() {
        File internalFile = new File(getFilesDir(), "data.txt");
        File externalFile = null;

        // Check if external storage is available
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            externalFile = new File(getExternalFilesDir(null), "data.txt");
        }

        // Prioritize external storage if both exist
        File fileToRead = null;
        String storageType = "";

        if (externalFile != null && externalFile.exists()) {
            fileToRead = externalFile;
            storageType = "External Storage";
        } else if (internalFile.exists()) {
            fileToRead = internalFile;
            storageType = "Internal Storage";
        } else {
            Toast.makeText(this, "No saved data found!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Read data
        String data = FileStorageHelper.readFromFile(fileToRead);

        if (data == null || data.trim().isEmpty()) {
            Toast.makeText(this, "File is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display the loaded data
        editText.setText(data);
        Toast.makeText(this, "Loaded from " + storageType, Toast.LENGTH_LONG).show();
    }






}
