package com.example.sahir.fitnessapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Squat extends AppCompatActivity implements View.OnClickListener {

    private static final String SHARED_PREF_NAME = "my pref";
    private static final String KEY_EXERCISE = "exercise";

    ImageButton imageButton1, imageButton2, imageButton3 ;
    Button button;
    TextView textView;
    String exercise;
    int interval = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        exercise = sharedPreferences.getString(KEY_EXERCISE, null);

        textView = findViewById(R.id.textview_squat_counter);
        imageButton1 = findViewById(R.id.imageButton_back_squat);
        imageButton2 = findViewById(R.id.imageButton_start_squat);
        imageButton3 = findViewById(R.id.imageButton_next_squat);
        button = findViewById(R.id.quitButton_squat);


        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton_back_squat:
                startActivity(new Intent(Squat.this, MainExercise.class));
                Toast.makeText(this, "Do Regular Exercise", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.imageButton_start_squat:
                startTimer();
                break;
            case R.id.imageButton_next_squat:
                startActivity(new Intent(Squat.this, Pushup.class));
                Toast.makeText(this, "Don't Skip Exercise", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quitButton_squat:
                AlertDialog.Builder builder = new AlertDialog.Builder(Squat.this);
                builder.setMessage("Do You Want to End Your Current Exercise ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(Squat.this,MainExercise.class));
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                break;


        }

        textView.setText(String.valueOf(exercise));

    }

    private void startTimer() {

        Timer timer = new Timer(Integer.parseInt(exercise) * 1000, 1000);
        timer.start();
    }

    class Timer extends CountDownTimer {
        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            textView.setText(String.valueOf(l / 1000));
        }


        @Override
        public void onFinish() {
            MediaPlayer player = MediaPlayer.create(Squat.this, Settings.System.DEFAULT_NOTIFICATION_URI);
            player.start();
            Toast.makeText(Squat.this, "Start Next Exercise", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Squat.this, Pushup.class));
        }
    }
}
