package com.example.mediplus.Patient;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.R;

public class CovidPrevention extends AppCompatActivity {

    ImageView prevention1,prevention2,prevention3,prevention4,coronavirus;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_prevention);
        prevention1 = findViewById(R.id.prevention1);
        prevention2 = findViewById(R.id.prevention2);
        prevention3 = findViewById(R.id.prevention3);
        prevention4 = findViewById(R.id.prevention4);
        //coronavirus = findViewById(R.id.coronavirus);

        animation = AnimationUtils.loadAnimation(this,R.anim.animation);
       // coronavirus.startAnimation(animation);

        pulsatingEffect(prevention1);
        pulsatingEffect(prevention2);
        pulsatingEffect(prevention3);
        pulsatingEffect(prevention4);



    }
    public void pulsatingEffect(ImageView imageView)
    {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX", 1.03f),
                PropertyValuesHolder.ofFloat("scaleY", 1.03f));
        scaleDown.setDuration(900);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();
    }

    public void Faqs(View view) {

        String url = "https://www.mohfw.gov.in/pdf/FAQ.pdf";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void Discharge(View view) {
        String url = "https://www.mohfw.gov.in/pdf/ReviseddischargePolicyforCOVID19.pdf";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void mustRead(View view) {

        String url = "https://www.mohfw.gov.in/pdf/socialdistancingEnglish.pdf";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Travellers(View view) {

        String url = "https://www.mohfw.gov.in/pdf/PostrerEnglishtraveller.pdf";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
