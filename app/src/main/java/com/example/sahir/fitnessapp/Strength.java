package com.example.sahir.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Strength extends AppCompatActivity implements View.OnClickListener {

    Button button1,button2,button3,button4;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);

        button1=findViewById(R.id.strength_squat);
        button2=findViewById(R.id.strength_pushup);
        button3=findViewById(R.id.strength_weight);
        button4=findViewById(R.id.strength_pullup);

        imageButton=findViewById(R.id.imageButton_strength);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        imageButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.strength_squat:
                startActivity(new Intent(Strength.this,Squat.class));
                break;
            case R.id.strength_pushup:
                startActivity(new Intent(Strength.this,Pushup.class));
                break;
            case R.id.strength_weight:
                startActivity(new Intent(Strength.this,WeightLifting.class));
                break;
            case R.id.strength_pullup:
                startActivity(new Intent(Strength.this,PullUp.class));
                break;
            case R.id.imageButton_strength:
                startActivity(new Intent(Strength.this,MainExercise.class));
                break;
        }
    }
}
