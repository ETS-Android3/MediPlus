package com.example.mediplus.Patient;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mediplus.Covid.user.quiz.QuizFragment;
import com.example.mediplus.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class CovidTest extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_test);


        chipNavigationBar = findViewById(R.id.bottom_nav_covid);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_covidt,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new QuizFragment()).commit();
        bottomMenu();
    }

    private void bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.bottom_nav_covidt:
                        fragment = new QuizFragment();
                        break;
                    case R.id.bottom_nav_r:
                        fragment = new PatientMenuFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });

    }
}