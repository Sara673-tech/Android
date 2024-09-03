package com.example.eyedetectionapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultScreenActivity extends AppCompatActivity {

    TextView tvConjuctivity, tvEyeEffected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvConjuctivity = findViewById(R.id.tvConjuctivity);
        tvEyeEffected = findViewById(R.id.tvEyeEffected);

        if (getIntent().getIntExtra("LEFT_CATEGORY", 0) == 1) {
            tvEyeEffected.setText("Eye is Effected");
        } else {
            tvEyeEffected.setText("Eye is not Effected");
        }

        if (getIntent().getIntExtra("RIGHT_CATEGORY", 0) == 0) {
            tvConjuctivity.setText("Cataract");
        } else if (getIntent().getIntExtra("RIGHT_CATEGORY", 0) == 1) {
            tvConjuctivity.setText("Diabetic Retinopathy");
        } else if (getIntent().getIntExtra("RIGHT_CATEGORY", 0) == 2) {
            tvConjuctivity.setText("Glaucoma");
        } else if (getIntent().getIntExtra("RIGHT_CATEGORY", 0) == 3) {
            tvConjuctivity.setText("Normal");
        }


    }
}