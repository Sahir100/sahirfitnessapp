package com.example.sahir.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Endurance extends AppCompatActivity implements View.OnClickListener {
    Button ejog, eclimb, ewalk, eswiming;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endurance);


        ejog = findViewById(R.id.endurance_jogging);
        eclimb = findViewById(R.id.endurance_climbing_stairs);
        ewalk = findViewById(R.id.endurance_walk);
        eswiming = findViewById(R.id.endurance_swimming);

        imageButton=findViewById(R.id.imageButton_endurance);

        ejog.setOnClickListener(this);
        eclimb.setOnClickListener(this);
        ewalk.setOnClickListener(this);
        eswiming.setOnClickListener(this);

        imageButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.endurance_jogging:
                 startActivity(new Intent(Endurance.this,Jogging.class));
                  break;
             case R.id.endurance_climbing_stairs:
                 startActivity(new Intent(Endurance.this,Climbing.class));
                 break;
             case R.id.endurance_walk:
                 startActivity(new Intent(Endurance.this,Walk.class));
                 break;
             case R.id.endurance_swimming:
                 startActivity(new Intent(Endurance.this,Swim.class));
                 break;
             case R.id.imageButton_endurance:
                 startActivity(new Intent(Endurance.this,MainExercise.class));
                 break;
         }
    }
}
