package com.example.mediplus.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Covid.doctor.MainActivityDoctor;
import com.example.mediplus.R;
import com.example.mediplus.appointment.DoctorUI.DoctorAppointments;
import com.example.mediplus.appointment.DoctorUI.MyPatientsActivity;
import com.example.mediplus.appointment.DoctorUI.SearchPatientActivity;


public class DoctorDashboard extends AppCompatActivity {

    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);


    }


    public void appointmentDoc(View view) {

        Intent intent = new Intent(DoctorDashboard.this, DoctorAppointments.class);
        startActivity(intent);
    }

    public void Patientlist(View view) {
        Intent intent = new Intent(DoctorDashboard.this, SearchPatientActivity.class);
        startActivity(intent);
    }

    public void MyPatients(View view) {
        Intent intent = new Intent(DoctorDashboard.this, MyPatientsActivity.class);
        startActivity(intent);
    }

    public void DocProfile(View view) {
       // Intent intent = new Intent(DoctorDashboard.this, DisplayDoctorProfileInfo.class);
        Intent intent = new Intent(DoctorDashboard.this, MainActivityDoctor.class);
        startActivity(intent);
    }
}