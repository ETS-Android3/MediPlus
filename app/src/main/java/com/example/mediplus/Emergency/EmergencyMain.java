package com.example.mediplus.Emergency;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mediplus.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EmergencyMain extends AppCompatActivity
{
    //TextView t;
    String a,b,c;
    private String number1;
    private String number2;
    private String number3;
    private String ambulance;
    private String ambulance_message;
    private String police;
    private String police_message;
    private String message;
    Button but;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_main);
        //but=(Button)findViewById(R.id.button5);
        ActivityCompat.requestPermissions(EmergencyMain.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, PackageManager.PERMISSION_GRANTED);
        message="EMERGENCY, NEED HELP!!!";
        ambulance_message="Need medical help, ADRESS: NIE admin block, mananthavadi road, Mysore";
        police_message="Need police help, ADRESS: NIE NRN block, mananthavadi road, Mysore";

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                userphone userph = dataSnapshot.child(Objects.requireNonNull(firebaseAuth.getUid())).getValue(userphone.class) ;
                number1 =  userph.getPhno1();
                number2 = userph.getPhno2();
                number3= userph.getPhno3();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ambulance="9645280546";
        police="7356825244";



    }


    public void send(View view)
    {


        //  String message1 = message.getText().toString();
        // String number1 = number.getText().toString();
        int i;
        //Toast.makeText(VaccineActivity.this,"message"+ a,Toast.LENGTH_SHORT).show();
        SmsManager mysmsmanager = SmsManager.getDefault();
        mysmsmanager.sendTextMessage(number1,null,message,null,null);
        mysmsmanager.sendTextMessage(number2,null,message,null,null);
        mysmsmanager.sendTextMessage(number3,null,message,null,null);
        Toast.makeText(EmergencyMain.this,"Message sent to "+ number1 +" "+ number2 +" "+ number3,Toast.LENGTH_SHORT).show();
       //  mysmsmanager.sendTextMessage(number2,null,message,null,null);
     //   mysmsmanager.sendTextMessage(number3,null,message,null,null);
    }

    public void ambulance(View view)
    {

        SmsManager mysmsmanager = SmsManager.getDefault();

        mysmsmanager.sendTextMessage(ambulance,null,ambulance_message,null,null);
        Toast.makeText(EmergencyMain.this,"Message sent to"+ ambulance,Toast.LENGTH_SHORT).show();
    }
    public void police(View view)
    {
        SmsManager mysmsmanager = SmsManager.getDefault();

        mysmsmanager.sendTextMessage(police,null,police_message,null,null);
        Toast.makeText(EmergencyMain.this,"Message sent to"+ police,Toast.LENGTH_SHORT).show();
    }


    public void adduser(View view) {
        startActivity(new Intent(getApplicationContext(), phone.class));
        finish();
    }
}

