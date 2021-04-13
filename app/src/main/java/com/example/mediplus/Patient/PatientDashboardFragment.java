package com.example.mediplus.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.mediplus.Covid.FirstPage;
import com.example.mediplus.R;
import com.example.mediplus.appointment.AppointmentsActivity;
import com.example.mediplus.appointment.MedicalFolderActivity;
import com.example.mediplus.appointment.SearchDoctorSpecialityActivity;


public class PatientDashboardFragment extends Fragment {

   ImageView appointment,findDoc,homeService,cCare,cTest,cWorld,callBack,directory,rem1,rem2,prs,vir;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_dashboard, container, false);

        appointment = v.findViewById(R.id.appointment);
        findDoc=v.findViewById(R.id.findDoctor);
        homeService=v.findViewById(R.id.homeservice);
        cCare=v.findViewById(R.id.covidcare);
        cTest=v.findViewById(R.id.covidtest);
        cWorld=v.findViewById(R.id.worldcovid);
        callBack=v.findViewById(R.id.callback);
        directory=v.findViewById(R.id.directory);
        rem1=v.findViewById(R.id.reminder1);
        rem2=v.findViewById(R.id.reminder2);
        prs=v.findViewById(R.id.prescriptionpat);
        vir=v.findViewById(R.id.virtual);


        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AppointmentsActivity.class);
                startActivity(intent);
            }
        });



        findDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchDoctorSpecialityActivity.class);
                startActivity(intent);
            }
        });

       homeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),HomeService.class);
                startActivity(intent);
            }
        });

        cCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CovidCare.class);
                startActivity(intent);
            }
        });

        cTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CovidTest.class);
                startActivity(intent);
            }
        });

        cWorld.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), FirstPage.class);
            startActivity(intent);
        });

        callBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Callback.class);
                startActivity(intent);
            }
        });

        rem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Reminder.class);
                startActivity(intent);
            }
        });

        rem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Reminder.class);
                startActivity(intent);
            }
        });

        prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),  MedicalFolderActivity.class);
                startActivity(intent);
            }
        });

        vir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PatientDash.class);
                startActivity(intent);
            }
        });



        return v;

    }
}