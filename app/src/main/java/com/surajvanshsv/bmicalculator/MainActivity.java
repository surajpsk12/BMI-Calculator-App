package com.surajvanshsv.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText heightInput, weightInput;
    Button calculateButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Link UI components
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultText = findViewById(R.id.resultText);

        // Button Click Event
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculateBMI() {
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        float height = Float.parseFloat(heightStr);
        float weight = Float.parseFloat(weightStr);

        if (height <= 0 || weight <= 0) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        float bmi = weight / (height * height);

        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 24.9) {
            category = "Normal weight";
        } else if (bmi < 29.9) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        resultText.setText("Your BMI: " + bmi + "\nCategory: " + category);
    }
}

