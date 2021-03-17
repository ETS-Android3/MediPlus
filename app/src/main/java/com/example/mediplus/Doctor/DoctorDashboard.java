package com.example.mediplus.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Covid.doctor.MainActivityDoctor;
import com.example.mediplus.R;



public class DoctorDashboard extends AppCompatActivity {

    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        img1=findViewById(R.id.docappointment);
        img1.setOnClickListener(view -> {
            Intent intent = new Intent(DoctorDashboard.this, MainActivityDoctor.class);
            startActivity(intent);
            finish();
        });
    }


}