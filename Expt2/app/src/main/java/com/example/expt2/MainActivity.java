package com.example.expt2;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private CardView homeCard, settingsCard, profileCard, messagesCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeCard = findViewById(R.id.homeCard);
        settingsCard = findViewById(R.id.settingsCard);
        profileCard = findViewById(R.id.profileCard);
        messagesCard = findViewById(R.id.messagesCard);
// Set click listeners
        homeCard.setOnClickListener(this);
        settingsCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
        messagesCard.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {
            int id = v.getId();
            String message = "";
            if (id == R.id.homeCard) {
                message = "Home clicked";
            } else if (id == R.id.settingsCard) {
                message = "Settings clicked";
            } else if (id == R.id.profileCard) {
                message = "Profile clicked";
            } else if (id == R.id.messagesCard) {
                message = "Messages clicked";
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }