package com.example.mediplus.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mediplus.Patient.Patient_login;
import com.example.mediplus.Patient.Patient_signup;
import com.example.mediplus.R;

public class Doctor_login extends AppCompatActivity {

    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_doctor_login);


        b2=findViewById(R.id.doc_signup);

        b2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Doctor_signup.class);
            startActivity(intent);
            finish();
        });
    }

    public void callForgetPassword(View view) {

    }

    public void DoctorLogIn(View view) {

        startActivity(new Intent(getApplicationContext(), DoctorDashboard.class));
        finish();

    }
}