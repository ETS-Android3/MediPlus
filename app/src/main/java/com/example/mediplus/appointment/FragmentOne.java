package com.example.mediplus.appointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mediplus.Database.DoctorHelperClass;
import com.example.mediplus.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentOne extends Fragment {
    static ListView listView;
    static ListViewAdapter adapter;
    List<DoctorHelperClass> myDoctors;

    public static  ListViewAdapter getAdapter() {
        return adapter;
    }

    public static ListView getListView(){return listView;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone_layout, container, false);
        listView = view.findViewById(R.id.myListView);
        myDoctors = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myDoctors.clear();
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    DoctorHelperClass doctor = data.getValue(DoctorHelperClass.class);
                    myDoctors.add(doctor);
                    Collections.sort(myDoctors);
                    adapter = new ListViewAdapter(getContext(), myDoctors);
                    listView.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DisplayDoctorInfo.class);
                intent.putExtra("fullName",myDoctors.get(position).getFullName());
                intent.putExtra("email",myDoctors.get(position).getEmail());
                intent.putExtra("speciality",myDoctors.get(position).getSpeciality());
                intent.putExtra("phoneNo",myDoctors.get(position).getPhoneNo());
                startActivity(intent);
            }
        });




        return view;
    }
}
