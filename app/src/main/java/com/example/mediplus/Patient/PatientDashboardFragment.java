package com.example.mediplus.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.mediplus.R;
import com.example.mediplus.appointment.AppointmentsActivity;
import com.example.mediplus.appointment.SearchDoctorSpecialityActivity;


public class PatientDashboardFragment extends Fragment {

   ImageView appointment,findDoc;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_dashboard, container, false);

        appointment = v.findViewById(R.id.appointment);
        findDoc=v.findViewById(R.id.findDoctor);

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

        return v;

    }
}