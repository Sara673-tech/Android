package com.example.eyedetectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class DiseaseInformationActivity extends AppCompatActivity {

    MaterialButton btnAgeRelated, btnCataract,
            btnDiabetic, btnGlaucoma, btnPinkEye, btnHypertensive, Myopia, Normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_disease_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Normal = findViewById(R.id.Normal);
        btnPinkEye = findViewById(R.id.btnPinkEye);
        btnHypertensive = findViewById(R.id.btnHypertensive);
        btnGlaucoma = findViewById(R.id.btnGlaucoma);
        Myopia = findViewById(R.id.Myopia);
        btnAgeRelated = findViewById(R.id.btnAgeRelated);
        btnCataract = findViewById(R.id.btnCataract);
        btnDiabetic = findViewById(R.id.btnDiabetic);
        btnAgeRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 1));
            }
        });
        btnCataract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 2));
            }
        });
        btnDiabetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 3));
            }
        });
        btnGlaucoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 4));
            }
        });
        btnPinkEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 5));
            }
        });
        btnHypertensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 6));
            }
        });
        Myopia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 7));
            }
        });
        Normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DiseaseInfoActivity.class)
                        .putExtra("CATEGORY", 8));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
        finishAffinity();
    }
}