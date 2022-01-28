package com.example.mediplus.Covid.user.talkto;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mediplus.Covid.user.talkto.adapter.MessageViewPagerAdapter;
import com.example.mediplus.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChatPatient extends AppCompatActivity {

    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TabLayout tabLayout = findViewById(R.id.tab_layout1);
        ViewPager viewPager = findViewById(R.id.view_pager1);

        fUser = FirebaseAuth.getInstance().getCurrentUser();

        MessageViewPagerAdapter viewPagerAdapter = new MessageViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}