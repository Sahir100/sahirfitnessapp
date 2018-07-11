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

public class Climbing extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "my pref";
    private static final String KEY_EXERCISE = "exercise";

    ImageView imageView;
    TextView textView;
    ImageButton imageButton, imageButton1, imageButton2;
    Button button;
    String exercise;
    int intervel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_climbing);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        exercise = sharedPreferences.getString(KEY_EXERCISE, null);

        imageView = findViewById(R.id.jumpingbeangif);

        textView = findViewById(R.id.textview_climbing_counter);
        imageButton = findViewById(R.id.imageBs_climbing);
        imageButton1 = findViewById(R.id.imageBb_climbing);
        imageButton2 = findViewById(R.id.imageBf_climbing);
        button = findViewById(R.id.quitButton_climbing);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Climbing.this, Rest.class));
                Toast.makeText(Climbing.this, "Don't Skip Exercise", Toast.LENGTH_SHORT).show();
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Climbing.this, Jogging.class));
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Climbing.this);
                builder.setMessage("Do You Want to End Your Current Exercise ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(Climbing.this,MainExercise.class));
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }

                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();

            }
        });
        textView.setText(String.valueOf(exercise));

        Glide.with(this)
                .load(R.drawable.climbingstairgf)
                .into(imageView);
    }

    private void startTimer() {
        Climbing.Timer timer = new Climbing.Timer(Integer.parseInt(exercise) * 1000, 1000);
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
            MediaPlayer player = MediaPlayer.create(Climbing.this, Settings.System.DEFAULT_NOTIFICATION_URI);
            player.start();
            Toast.makeText(Climbing.this, "Take Some Rest", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Climbing.this, Rest.class));

        }
    }
}


