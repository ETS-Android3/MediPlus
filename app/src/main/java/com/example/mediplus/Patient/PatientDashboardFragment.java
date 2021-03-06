package com.example.mediplus.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.mediplus.R;


public class PatientDashboardFragment extends Fragment {

   ImageView appointment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_dashboard, container, false);

        appointment = v.findViewById(R.id.appointment);

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Reminder.class);
                startActivity(intent);
            }
        });

        return v;

    }
}