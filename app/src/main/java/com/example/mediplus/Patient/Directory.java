package com.example.mediplus.Patient;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mediplus.R;

public class Directory extends AppCompatActivity {


    CardView helpline_number,toll_free_number,help_line_mailid,state_helpline_numbers;
    int CALL_CODE = 1;
    Animation textanimation;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        helpline_number = findViewById(R.id.helpline_number);
        toll_free_number = findViewById(R.id.toll_free_number);
        help_line_mailid = findViewById(R.id.helpline_email);
        state_helpline_numbers = findViewById(R.id.state_helpline);
        textView = findViewById(R.id.toll_free_title);
        textanimation = AnimationUtils.loadAnimation(this,R.anim.animation3);
        textView.startAnimation(textanimation);


        pulsatingEffect(helpline_number);
        pulsatingEffect(toll_free_number);
        pulsatingEffect(help_line_mailid);
        pulsatingEffect(state_helpline_numbers);


    }
    public void pulsatingEffect(CardView cardView)
    {
        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                cardView,
                PropertyValuesHolder.ofFloat("scaleX", 1.03f),
                PropertyValuesHolder.ofFloat("scaleY", 1.03f));
        scaleDown.setDuration(900);

        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        scaleDown.start();
    }
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.helpline_number:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "108"));
                startActivity(intent);
                break;
            case R.id.toll_free_number:
                Intent toll_intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91 1056"));
                startActivity(toll_intent);
                break;
            case R.id.helpline_email:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","ncov2019@gov.in", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
            case R.id.state_helpline:
                Uri uri = Uri.parse("https://www.mohfw.gov.in/pdf/coronvavirushelplinenumber.pdf");
                Intent pdf = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(pdf);
                break;
            default:
                Toast.makeText(this, "Default!", Toast.LENGTH_SHORT).show();
        }
    }
}