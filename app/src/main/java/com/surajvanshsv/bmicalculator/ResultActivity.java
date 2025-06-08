package com.surajvanshsv.bmicalculator;


import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView bmiValue, bmiCategory, bmiInfo;
    Button findOutMoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bmiValue = findViewById(R.id.bmiValue);
        bmiCategory = findViewById(R.id.bmiCategory);
        bmiInfo = findViewById(R.id.bmiInfo);
        findOutMoreBtn = findViewById(R.id.findOutMoreBtn);

        // Step 1: Get data from intent
        Intent intent = getIntent();
        int height = intent.getIntExtra("height", 160); // in cm
        int weight = intent.getIntExtra("weight", 70); // in kg
        int age = intent.getIntExtra("age", 23);
        String gender = intent.getStringExtra("gender");

        // Step 2: Calculate BMI = weight(kg) / (height in meters)^2
        float heightInMeters = height / 100f;
        float bmi = weight / (heightInMeters * heightInMeters);
        String bmiResult = String.format("%.1f", bmi);

        bmiValue.setText(bmiResult);

        // Step 3: Determine category
        String category;
        String info;

        if (bmi < 18.5) {
            category = "Underweight";
            info = "You are underweight. Consider a nutritious diet and medical advice.";
        } else if (bmi < 24.9) {
            category = "Normal";
            info = "Your weight is in the normal range.\n\nMaintaining a healthy BMI helps reduce risk of chronic diseases.";
        } else if (bmi < 29.9) {
            category = "Overweight";
            info = "You are overweight. Regular exercise and diet changes may help.";
        } else {
            category = "Obese";
            info = "You are in the obese range. Seek medical guidance for healthy weight loss.";
        }

        bmiCategory.setText(category);

        String range = getHealthyRange(height);
        bmiInfo.setText("Your BMI is " + bmiResult + ", indicating you are in the " + category + " range.\n\n" + range + "\n\n" + info);

        // Optional: handle button
        findOutMoreBtn.setOnClickListener(v -> {
            Toast.makeText(ResultActivity.this, "More info feature coming soon!", Toast.LENGTH_SHORT).show();
        });
    }

    // Function to calculate healthy weight range (18.5–24.9)
    private String getHealthyRange(int heightCm) {
        float heightM = heightCm / 100f;
        int minWeight = Math.round(18.5f * heightM * heightM);
        int maxWeight = Math.round(24.9f * heightM * heightM);
        return "For your height, a normal weight range is from " + minWeight + " kg to " + maxWeight + " kg.";
    }
}
