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

public class Swim extends AppCompatActivity implements View.OnClickListener {

    private static final String SHARED_PREF_NAME = "my pref";
    private static final String KEY_EXERCISE = "exercise";

    TextView textView;
    ImageButton imageButton1, imageButton2, imageButton3;
    ImageView imageView;
    Button button;
    int Interval = 0;
    String exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swim);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        exercise = sharedPreferences.getString(KEY_EXERCISE, null);


        textView = findViewById(R.id.textview_swim_counter);
        imageButton1 = findViewById(R.id.imageButton_back_swim);
        imageButton2 = findViewById(R.id.imageButton_start_swim);
        imageButton3 = findViewById(R.id.imageButton_next_swim);

        imageView=findViewById(R.id.image_gif_swimming);

        button= findViewById(R.id.quitButton_swim);

        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        button.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton_back_swim:
                startActivity(new Intent(Swim.this, Walk.class));
                finish();
                break;
            case R.id.imageButton_start_swim:
                startTimer();
                break;
            case R.id.imageButton_next_swim:
                startActivity(new Intent(Swim.this,MainExercise.class));
                Toast.makeText(this, "Don't Skip Exercise", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quitButton_swim:
                AlertDialog.Builder builder = new AlertDialog.Builder(Swim.this);
                builder.setMessage("Do You Want to End Your Current Exercise ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(Swim.this,MainExercise.class));
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
                .load(R.drawable.swimming)
                .into(imageView);

    }

    private void startTimer() {
        Swim.Timer timer = new Swim.Timer(Integer.parseInt(exercise) * 1000, 1000);
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
            MediaPlayer player = MediaPlayer.create(Swim.this, Settings.System.DEFAULT_NOTIFICATION_URI);
            player.start();
            Toast.makeText(Swim.this, "Well Done", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Swim.this, MainExercise.class));
        }
    }
}

