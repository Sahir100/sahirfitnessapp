package com.example.sahir.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class trainingplan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainingplan);

     Intent i=new Intent(trainingplan.this,MainExercise.class);
     startActivity(i);
    }
}
