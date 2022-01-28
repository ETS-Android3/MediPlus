package com.example.mediplus.Doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Covid.doctor.MainActivityDoctor;
import com.example.mediplus.Patient.Directory;
import com.example.mediplus.R;
import com.example.mediplus.appointment.DoctorUI.DisplayDoctorProfileInfo;
import com.example.mediplus.appointment.DoctorUI.DoctorAppointments;
import com.example.mediplus.appointment.DoctorUI.MyPatientsActivity;
import com.example.mediplus.appointment.DoctorUI.SearchPatientActivity;
import com.google.firebase.auth.FirebaseAuth;


public class DoctorDashboard extends AppCompatActivity {

    ImageView img1;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        auth = FirebaseAuth.getInstance();

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
       Intent intent = new Intent(DoctorDashboard.this, DisplayDoctorProfileInfo.class);
       startActivity(intent);
    }

    public void Chats(View view) {
        Intent intent = new Intent(DoctorDashboard.this, MainActivityDoctor.class);
        startActivity(intent);

    }

    public void directory(View view) {
        Intent intent = new Intent(DoctorDashboard.this, Directory.class);
        startActivity(intent);
    }

    public void logoutDoc(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(DoctorDashboard.this);
        builder.setTitle("Info");
        builder.setMessage("Do you want to logout ??");
        builder.setPositiveButton("Take me away!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                auth.signOut();
                startActivity(new Intent(DoctorDashboard.this, Doctor_login.class));
                Toast.makeText(DoctorDashboard.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}