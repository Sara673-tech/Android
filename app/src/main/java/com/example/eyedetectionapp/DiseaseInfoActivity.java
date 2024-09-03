package com.example.eyedetectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class DiseaseInfoActivity extends AppCompatActivity {

    int getCat, getLeftCat, getCategory;
    TextView diseasename, tvSymptoms, tvRisk, tvCauses, tvPrevention, tvTreatment;
    ImageView diseaseIV;
    RelativeLayout diseaseInfoRL;
    MaterialButton btnRightEye, btnLeftEye;
    LinearLayout buttonsLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);
        getCat = getIntent().getIntExtra("RIGHT_CATEGORY", 0);
        getLeftCat = getIntent().getIntExtra("LEFT_CATEGORY", 0);
        getCategory = getIntent().getIntExtra("CATEGORY", 0);
        buttonsLL = findViewById(R.id.buttonsLL);
        btnRightEye = findViewById(R.id.btnRightEye);
        btnLeftEye = findViewById(R.id.btnLeftEye);
        if (getCategory == 0) {
            buttonsLL.setVisibility(View.VISIBLE);
        } else {
            buttonsLL.setVisibility(View.GONE);
        }

        btnLeftEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initViews();
            }
        });

        btnRightEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initViews();
            }
        });

        diseasename = findViewById(R.id.tvDiseaseName);
        diseaseInfoRL = findViewById(R.id.diseaseInfoRL);
        diseaseIV = findViewById(R.id.diseaseIV);
        tvRisk = findViewById(R.id.tvRisk);
        tvSymptoms = findViewById(R.id.tvSymptoms);
        tvCauses = findViewById(R.id.tvCauses);
        tvTreatment = findViewById(R.id.tvTreatment);
        tvPrevention = findViewById(R.id.tvPrevention);

        diseaseInfoRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initViews() {
        if (getCat == 1) {
            diseasename.setText("Age-Related Macular Degeneration");
            diseaseIV.setImageResource(R.drawable.amd);
            tvSymptoms.setText(R.string.amdsymptoms);
            tvRisk.setText(R.string.amdrisk);
            tvCauses.setText(R.string.amdcauses);
            tvPrevention.setText(R.string.amdprevention);
            tvTreatment.setText(R.string.amdtreatment);
        } else if (getCat == 2) {
            diseasename.setText("Cataract Disease");
            diseaseIV.setImageResource(R.drawable.cataract);
            tvSymptoms.setText(R.string.catarctsymptoms);
            tvRisk.setText(R.string.cataractrisk);
            tvCauses.setText(R.string.cataractcauses);
            tvPrevention.setText(R.string.cataractprevention);
            tvTreatment.setText(R.string.cataracttreatment);
        } else if (getCat == 3) {
            diseasename.setText("Diabetes");
            diseaseIV.setImageResource(R.drawable.diabetic);
            tvSymptoms.setText(R.string.diabeticsymptoms);
            tvRisk.setText(R.string.diabeticrisk);
            tvCauses.setText(R.string.diabeticcauses);
            tvPrevention.setText(R.string.diabeticprevention);
            tvTreatment.setText(R.string.diabetictreatment);
        } else if (getCat == 4) {
            diseasename.setText("Glaucoma");
            diseaseIV.setImageResource(R.drawable.glaucoma);
            tvSymptoms.setText(R.string.glaucomasymptoms);
            tvRisk.setText(R.string.glaucomarisk);
            tvCauses.setText(R.string.glaucomacauses);
            tvPrevention.setText(R.string.glaucomaprevention);
            tvTreatment.setText(R.string.glaucomaptreatment);

        } else if (getCat == 5) {
            diseasename.setText("Pink Eye");
            diseaseIV.setImageResource(R.drawable.pinkeye);
            tvSymptoms.setText(R.string.pinkeyesymptoms);
            tvRisk.setText(R.string.pinkeyerisk);
            tvCauses.setText(R.string.pinkeyecauses);
            tvPrevention.setText(R.string.pinkeyeprevention);
            tvTreatment.setText(R.string.pinkeyetreatment);
        } else if (getCat == 6) {
            diseasename.setText("Hypertensive");
            diseaseIV.setImageResource(R.drawable.hypertensiveretinopathy);
            tvSymptoms.setText(R.string.hypertensivesymptoms);
            tvRisk.setText(R.string.hypertensiverisk);
            tvCauses.setText(R.string.hypertensivecauses);
            tvPrevention.setText(R.string.hypertensiveprevention);
            tvTreatment.setText(R.string.hypertensivetreatment);
        } else if (getCat == 7) {
            diseasename.setText("Myopia");
            diseaseIV.setImageResource(R.drawable.myopia);
            tvSymptoms.setText(R.string.myopiasymptoms);
            tvRisk.setText(R.string.myopiarisk);
            tvCauses.setText(R.string.myopiacauses);
            tvPrevention.setText(R.string.myopiaprevention);
            tvTreatment.setText(R.string.myopiatreatment);

        } else if (getCat == 8) {
            diseasename.setText("Normal");
            diseaseIV.setImageResource(R.drawable.normal);
            tvSymptoms.setText(R.string.normalsymptoms);
            tvRisk.setText(R.string.normalrisk);
            tvCauses.setText(R.string.normalcauses);
            tvPrevention.setText(R.string.normalprevention);
            tvTreatment.setText(R.string.normaltreatment);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}