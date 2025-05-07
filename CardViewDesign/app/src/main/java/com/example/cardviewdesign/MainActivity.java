package com.example.cardviewdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView cardHome;
    CardView cardAudio;
    CardView cardChat;
    CardView cardCamera;
    CardView cardBluetooth;
    CardView cardSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardHome=findViewById(R.id.cardHome);
        cardChat=findViewById(R.id.cardChat);
        cardAudio=findViewById(R.id.cardAudio);
        cardCamera=findViewById(R.id.cardCamera);
        cardBluetooth=findViewById(R.id.cardBluetooth);
        cardSetting=findViewById(R.id.cardSetting);

        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    showToast("Home clicked!");
            }
        });
        cardSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Settings clicked!");
            }
        });
        cardBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Bluetooth Clicked!");
            }
        });
        cardAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Audio clicked!");
            }
        });
        cardChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Chat clicked!");
            }
        });
        cardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Camera clicked");
            }
        });
    }
    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}