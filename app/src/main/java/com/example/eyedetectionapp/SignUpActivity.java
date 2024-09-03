package com.example.eyedetectionapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import com.example.eyedetectionapp.database.entity.AppDatabase;
import com.example.eyedetectionapp.database.entity.UserEntity;

public class SignUpActivity extends AppCompatActivity {

    TextView tvLogin;
    Button btnSignup;
    EditText edtSignupName, edtSignupEmail, edtSignupPassword, edtSignupConfirmPassword;
    AppDatabase appDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("please wait..");
        appDatabase = AppDatabase.getInstance(SignUpActivity.this);
        tvLogin = findViewById(R.id.tvLogin);
        btnSignup = findViewById(R.id.btnSignup);
        edtSignupName = findViewById(R.id.edtSignupName);
        edtSignupEmail = findViewById(R.id.edtSignupEmail);
        edtSignupPassword = findViewById(R.id.edtSignupPassword);
        edtSignupConfirmPassword = findViewById(R.id.edtSignupConfirmPassword);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSignupName.getText().toString().isEmpty()) {
                    edtSignupName.setError("fill this field");
                } else if (edtSignupEmail.getText().toString().isEmpty()) {
                    edtSignupEmail.setError("fill this field");
                } else if (edtSignupPassword.getText().toString().isEmpty()) {
                    edtSignupPassword.setError("fill this field");
                } else if (edtSignupConfirmPassword.getText().toString().isEmpty()) {
                    edtSignupConfirmPassword.setError("fill this field");
                } else {
                    progressDialog.show();
                    appDatabase.databaseDao().insertUser(new UserEntity(edtSignupName.getText().toString(),
                            edtSignupEmail.getText().toString(),
                            edtSignupPassword.getText().toString()));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finishAffinity();
                        }
                    }, 1000);
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}