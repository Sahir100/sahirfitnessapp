package com.example.sahir.fitnessapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "my pref";
    private static final String KEY_REST = "rest";
    private static final String KEY_EXERCISE = "exercise";


    Button b1, b2;
    EditText edit1, edit2;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        b1 = findViewById(R.id.txt);
        b2 = findViewById(R.id.exr);

        edit1 = findViewById(R.id.edit_settime);
        edit2 = findViewById(R.id.edit_settimeexercise);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveuser1();
            }


            private void saveuser1() {
                String rest = edit1.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(KEY_REST, rest);
                    editor.apply();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveuser();
            }


            private void saveuser() {
                String exercise = edit2.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(KEY_EXERCISE, exercise);
                editor.apply();

                startActivity(new Intent(Settings.this,MainExercise.class));

            }

        });


    }
}
