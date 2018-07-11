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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Walk extends AppCompatActivity implements View.OnClickListener {

    private static final String SHARED_PREF_NAME = "my pref";
    private static final String KEY_EXERCISE = "exercise";

    TextView textView;
    ImageButton imageButton1, imageButton2, imageButton3;
    ImageView imageView;
    Button button;
    String exercise;
    int interval = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        exercise = sharedPreferences.getString(KEY_EXERCISE, null);


        textView = findViewById(R.id.textview_walk_counter);
        imageButton1 = findViewById(R.id.imageButton_back_walk);
        imageButton2 = findViewById(R.id.imageButton_start_walk);
        imageButton3 = findViewById(R.id.imageButton_next_walk);

        imageView=findViewById(R.id.image_gif_walkinggf);

        button=findViewById(R.id.quitButton_walk);

        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton_back_walk:
                startActivity(new Intent(Walk.this, Climbing.class));
                finish();
                break;
            case R.id.imageButton_start_walk:
                startTimer();
                break;
            case R.id.imageButton_next_walk:
                startActivity(new Intent(Walk.this,Swim.class));
                Toast.makeText(this, "Don't Skip Exercise", Toast.LENGTH_SHORT).show();
                   break;
            case R.id.quitButton_walk:
                AlertDialog.Builder builder = new AlertDialog.Builder(Walk.this);
                builder.setMessage("Do You Want to End Your Current Exercise ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(Walk.this,MainExercise.class));
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

        Glide.with(this)
                .load(R.drawable.walkinggf)
                .into(imageView);

    }

    private void startTimer() {
        Walk.Timer timer = new Walk.Timer(Integer.parseInt(exercise) * 1000, 1000);
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
            MediaPlayer player = MediaPlayer.create(Walk.this, Settings.System.DEFAULT_NOTIFICATION_URI);
            player.start();
            Toast.makeText(Walk.this, "Start Next Exercise", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Walk.this, Swim.class));
        }
    }
}

