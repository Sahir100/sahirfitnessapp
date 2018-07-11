package com.example.sahir.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends AppCompatActivity {
    EditText height,weight;
    TextView result;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        weight=findViewById(R.id.edit_weight);
        height=findViewById(R.id.edit_height);
        result=findViewById(R.id.text_result);
        calculate=findViewById(R.id.button_bmi);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightstr = weight.getText().toString();
        String heightstr = height.getText().toString();

        if (weightstr != null && !"".equals(weightstr) && heightstr != null && !"".equals(heightstr)) {
            float weightvalue = Float.parseFloat(weightstr);
            float heightvalue = Float.parseFloat(heightstr);

            float bmi1 = weightvalue / (heightvalue * heightvalue);
             double bmi=bmi1/0.00064;
            displayBMI(bmi);
        }
    }

    private void displayBMI(double bmi) {
        result.setText(String.valueOf(bmi));

        if(bmi<18.5){
            Toast.makeText(this, "UnderWeight", Toast.LENGTH_SHORT).show();
        }else if(bmi>=18.5 && bmi<=24.9){
            Toast.makeText(this, "Normal", Toast.LENGTH_SHORT).show();
        }else if(bmi>=25 && bmi<=29.9){
            Toast.makeText(this, "OverWeight", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Obese", Toast.LENGTH_SHORT).show();
        }


    }
}
