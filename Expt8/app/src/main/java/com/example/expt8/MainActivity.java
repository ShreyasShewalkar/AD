package com.example.expt8;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView statusText;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI Elements
        Button playButton = findViewById(R.id.playButton);
        Button pauseButton = findViewById(R.id.pauseButton);
        Button stopButton = findViewById(R.id.stopButton);
        seekBar = findViewById(R.id.seekBar);
        statusText = findViewById(R.id.statusText);

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_1);
        seekBar.setMax(mediaPlayer.getDuration());

        // Update SeekBar Progress
        handler.postDelayed(updateSeekBar, 100);

        // Play Button Listener
        playButton.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                statusText.setText("Status: Playing");
                Toast.makeText(MainActivity.this, "Playing Audio", Toast.LENGTH_SHORT).show();
                updateSeekBarProgress();
            }
        });

        // Pause Button Listener
        pauseButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                statusText.setText("Status: Paused");
                Toast.makeText(MainActivity.this, "Audio Paused", Toast.LENGTH_SHORT).show();
            }
        });

        // Stop Button Listener
        stopButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying() || mediaPlayer.getCurrentPosition() > 0) {
                mediaPlayer.stop();
                mediaPlayer.prepareAsync();
                statusText.setText("Status: Stopped");
                seekBar.setProgress(0);
                Toast.makeText(MainActivity.this, "Audio Stopped", Toast.LENGTH_SHORT).show();
            }
        });

        // SeekBar Listener for manual seeking
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    // Runnable to update SeekBar
    private Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        }
    };

    private void updateSeekBarProgress() {
        handler.postDelayed(updateSeekBar, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(updateSeekBar);
    }
}
