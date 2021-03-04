package com.example.mediplus.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mediplus.Database.SessionManager;
import com.example.mediplus.R;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.HashMap;


public class PatientDash extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patient_dash);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_dashbord,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PatientDashboardFragment()).commit();
        bottomMenu();

        //SessionManager sessionManager=new SessionManager(this);
        //HashMap<String, String> userDetails =sessionManager.getUserDetailFromSession();
    }

    private void bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.bottom_nav_dashbord:
                        fragment = new PatientDashboardFragment();
                        break;
                    case R.id.bottom_nav_Menu:
                        fragment = new PatientMenuFragment();
                        break;
                    case R.id.bottom_nav_profile:
                        fragment = new PatientProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });

    }

}

