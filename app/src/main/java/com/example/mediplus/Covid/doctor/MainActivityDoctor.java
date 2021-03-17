package com.example.mediplus.Covid.doctor;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mediplus.Covid.doctor.doctorchat.DoctorChatsFragment;
import com.example.mediplus.Covid.doctor.map.MapFragment;
import com.example.mediplus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor);

        BottomNavigationView bottomNavigationView = findViewById(R.id.doctor_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragment_container, new DoctorChatsFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_chat:
                            selectedFragment = new DoctorChatsFragment();
                            break;
                        case R.id.nav_map:
                            selectedFragment = new MapFragment();
                            break;
                        case R.id.nav_signout:
                            FirebaseAuth.getInstance().signOut();//logout

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragment_container, selectedFragment).commit();
                    return true;
                }
            };
}
