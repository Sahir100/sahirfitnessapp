package com.example.sahir.fitnessapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RestforStrength extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "my pref";
    private static final String KEY_REST = "rest";

    TextView t1;
    Button button;
    String rest;
    int interval = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restfor_strength);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        rest = sharedPreferences.getString(KEY_REST, null);

        t1 = findViewById(R.id.text_restforstrength);
        button = findViewById(R.id.button_skip1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RestforStrength.this, WeightLifting.class));

            }
        });


        startTimer();
        t1.setText(String.valueOf(rest));
    }

    private void startTimer() {
        Timer timer = new Timer(Integer.parseInt(rest) * 1000, 1000);
        timer.start();
    }

    class Timer extends CountDownTimer {

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            t1.setText(String.valueOf(l / 1000));
        }

        @Override
        public void onFinish() {
            MediaPlayer player = MediaPlayer.create(RestforStrength.this, Settings.System.DEFAULT_NOTIFICATION_URI);
            player.start();
            Toast.makeText(RestforStrength.this, "Time for Next Exercise", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RestforStrength.this, WeightLifting.class));
        }
    }
}

