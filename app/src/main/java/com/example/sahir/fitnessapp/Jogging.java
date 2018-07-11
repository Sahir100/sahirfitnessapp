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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Timer;

public class Jogging extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "my pref";
    private static final String KEY_EXERCISE = "exercise";

    TextView t;
    ImageButton b,b1,b2;
    ImageView imageView;
    Button b3;
    String exercise;
    int intervel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogging);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        exercise = sharedPreferences.getString(KEY_EXERCISE, null);


        t = findViewById(R.id.text_counter);
        b = findViewById(R.id.imageBs_jogging);
        b1=findViewById(R.id.imageBb_jogging);
        b2=findViewById(R.id.imageBf_jogging);
        b3=findViewById(R.id.quitButton_jogging);

        imageView=findViewById(R.id.image_gif_jogginggf);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jogging.this,Climbing.class));
                Toast.makeText(Jogging.this, "Don't Skip Exercise", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Jogging.this,MainExercise.class));
                Toast.makeText(Jogging.this, "Do Regular Exercise", Toast.LENGTH_SHORT).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Jogging.this);
                builder.setMessage("Do You Want to End Your Current Exercise ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(Jogging.this,MainExercise.class));
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

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });
        t.setText(String.valueOf(exercise));



        Glide.with(this)
                .load(R.drawable.jogginggf)
                .into(imageView);

    }

    private void startTimer() {
        Jogging.Timer timer = new Jogging.Timer(Integer.parseInt(exercise)*1000, 1000);
        timer.start();
    }

    class Timer extends CountDownTimer{

        public Timer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            t.setText(String.valueOf(l/1000));
        }

        @Override
        public void onFinish() {
            MediaPlayer player = MediaPlayer.create(Jogging.this, Settings.System.DEFAULT_NOTIFICATION_URI);
            player.start();
            startActivity(new Intent(Jogging.this,Climbing.class));
            Toast.makeText(Jogging.this, "Start Next Exercise", Toast.LENGTH_SHORT).show();

        }
    }
}



