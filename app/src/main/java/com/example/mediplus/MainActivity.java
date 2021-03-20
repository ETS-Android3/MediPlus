package com.example.mediplus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Doctor.Doctor_login;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIMER =2000;
    ImageView splashbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        splashbg = findViewById(R.id.splash_tq);
        Animation sideAnim,bottomAnim;


        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        splashbg.setAnimation(sideAnim);
        splashbg.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Doctor_login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);

    }

}
