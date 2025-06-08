package com.surajvanshsv.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button maleButton, femaleButton;
    SeekBar heightSeekBar;
    TextView heightValue, weightValue, ageValue;
    Button increaseWeight, decreaseWeight, increaseAge, decreaseAge, letsGoButton;

    int height = 160;
    int weight = 70;
    int age = 23;
    String gender = "Male";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Gender Buttons
        maleButton = findViewById(R.id.maleButton);
        femaleButton = findViewById(R.id.femaleButton);

        maleButton.setOnClickListener(v -> {
            gender = "Male";
            maleButton.setBackgroundTintList(getResources().getColorStateList(R.color.purple_500));
            femaleButton.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
        });

        femaleButton.setOnClickListener(v -> {
            gender = "Female";
            femaleButton.setBackgroundTintList(getResources().getColorStateList(R.color.purple_500));
            maleButton.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
        });

        // Height
        heightSeekBar = findViewById(R.id.heightSeekBar);
        heightValue = findViewById(R.id.heightValue);

        heightSeekBar.setMax(220);
        heightSeekBar.setProgress(height);
        heightValue.setText(height + " cm");

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height = progress;
                heightValue.setText(height + " cm");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Weight Controls
        weightValue = findViewById(R.id.weightValue);
        increaseWeight = findViewById(R.id.increaseWeight);
        decreaseWeight = findViewById(R.id.decreaseWeight);

        weightValue.setText(String.valueOf(weight));

        increaseWeight.setOnClickListener(v -> {
            weight++;
            weightValue.setText(String.valueOf(weight));
        });

        decreaseWeight.setOnClickListener(v -> {
            if (weight > 1) weight--;
            weightValue.setText(String.valueOf(weight));
        });
// Age Controls
        ageValue = findViewById(R.id.ageValue);
        increaseAge = findViewById(R.id.increaseAge);
        decreaseAge = findViewById(R.id.decreaseAge);

        ageValue.setText(String.valueOf(age));

        increaseAge.setOnClickListener(v -> {
            age++;
            ageValue.setText(String.valueOf(age));
        });

        decreaseAge.setOnClickListener(v -> {
            if (age > 1) age--;
            ageValue.setText(String.valueOf(age));
        });

        // Let's Go Button
        letsGoButton = findViewById(R.id.letsGoButton);
        letsGoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("gender", gender);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("age", age);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}

