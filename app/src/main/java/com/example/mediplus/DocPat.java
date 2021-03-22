package com.example.mediplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Doctor.Doctor_login;
import com.example.mediplus.Doctor.Doctor_signup;
import com.example.mediplus.Patient.Patient_signup;

public class DocPat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_doc_pat);

        Button docb = findViewById(R.id.doc_btn);
        Button patb = findViewById(R.id.pat_btn);

        docb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Doctor_signup.class);
                startActivity(intent);
                finish();
            }
        });

        patb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Patient_signup.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void bckBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), Doctor_login.class);
        startActivity(intent);
        finish();
    }
}
