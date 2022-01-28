package com.example.mediplus.Patient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.R;
import com.example.mediplus.vaccineslottracker.VaccineActivity;

public class VaccinateNow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccinate_now);



    }

    public void Registernow(View view) {

        String url = "https://selfregistration.cowin.gov.in/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Slot(View view) {
        Intent intent = new Intent(VaccinateNow.this, VaccineActivity.class);
        startActivity(intent);
    }

    public void faq(View view) {

        String url = "https://prod-cdn.preprod.co-vin.in/assets/pdf/FAQ_on_CoWIN_for_Citizens.docx";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }



}