package com.example.mediplus.appointment.DoctorUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Doctor.DoctorDashboard;
import com.example.mediplus.R;
import com.example.mediplus.appointment.models.Patient;
import com.example.mediplus.appointment.models.Relationship;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyPatientsActivity extends AppCompatActivity {
    ListView myPatientsListView;
    List<Patient> Patients;
    List<Patient>  myPatients;
    List<String> myRelationShips;
    static MyPatientsAdapter adapter;

    public static void notifyAdapter()
    {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patients);
        myPatientsListView = findViewById(R.id.myPatients);
        Patients = new ArrayList<>();
        myPatients = new ArrayList<>();
        myRelationShips = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Patients.clear();
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    Patient patient = data.getValue(Patient.class);
                    Patients.add(patient);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Relationships");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myPatients.clear();
                myRelationShips.clear();
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    Relationship relationship = data.getValue(Relationship.class);
                    for(int i=0; i<Patients.size(); i++)
                    {
                        Patient patient = Patients.get(i);
                        if(relationship.getEmailPatient().equals(patient.getEmail()) && relationship.getEmailDoctor().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                        {
                            myRelationShips.add(data.getKey());
                            myPatients.add(patient);
                            adapter = new MyPatientsAdapter(MyPatientsActivity.this, myPatients);
                            myPatientsListView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        myPatientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyPatientsActivity.this, MyPatientInfoActivity.class);
                intent.putExtra("fullName",myPatients.get(position).getFullName());
                intent.putExtra("email",myPatients.get(position).getEmail());
                intent.putExtra("phoneNo",myPatients.get(position).getPhoneNo());
                intent.putExtra("date", myPatients.get(position).getDate());
               // intent.putExtra("address", myPatients.get(position).getUsername());
                intent.putExtra("relationshipId", myRelationShips.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyPatientsActivity.this, DoctorDashboard.class);
        DoctorMenuActivity.setToken(1);
        startActivity(intent);
    }
}
