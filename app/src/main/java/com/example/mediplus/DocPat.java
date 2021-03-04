package com.example.mediplus;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;

import com.example.mediplus.Doctor.Doctor_login;
import com.example.mediplus.Doctor.Prescription;
import com.example.mediplus.Patient.Patient_login;

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
                Intent intent = new Intent(getApplicationContext(), Doctor_login.class);
                startActivity(intent);
                finish();
            }
        });

        patb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Patient_login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
