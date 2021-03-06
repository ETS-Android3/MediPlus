package com.example.mediplus.appointment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.mediplus.Patient.PatientDash;
import com.example.mediplus.R;
import com.example.mediplus.appointment.models.Appointment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ScheduleAppointmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView time,day;
    Intent intent;
    DatabaseReference databaseReference;
    FirebaseUser user;
    List <Appointment> appointments;
    String emailPatient, emailDoctor;
    String timeOfAppointment;
    TimePicker timepicker;
    public int num_of_patients_at_same_time=3;
    Button b1,b2;
    String currentDateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);
        day = findViewById(R.id.date);
        time = findViewById(R.id.time);
        timepicker = findViewById(R.id.add_time_picker);
        b1= findViewById(R.id.confirm);
        b2=findViewById(R.id.cancel);
        intent = getIntent();
        emailDoctor = intent.getStringExtra("email");
        appointments = new ArrayList<>();

    }

    public void chooseDay(View view) {
        DialogFragment datePicker = new MyDatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            new SweetAlertDialog(ScheduleAppointmentActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("You can't schedule an appointment on sunday !")
                    .show();
            double number = 1.3;
            System.out.println((int) number);
            System.out.println(number - (int)number);
            return;
        }

        final String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        day.setText(currentDateString);
        day.setVisibility(View.VISIBLE);


    }

    public void confirm(View view) {
        if(TextUtils.isEmpty(day.getText()) && TextUtils.isEmpty(time.getText()))
        {
            new SweetAlertDialog(ScheduleAppointmentActivity.this)
                    .setTitleText("Scheduling appointment")
                    .setContentText("Please pick a day !")
                    .show();
            return;
        }
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Patients");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                emailPatient = dataSnapshot.child(user.getUid()).child("email").getValue(String.class);
                Appointment appointment = new Appointment(day.getText().toString(),time.getText().toString(), emailDoctor, emailPatient);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Appointments");
                reference.push().setValue(appointment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        new SweetAlertDialog(ScheduleAppointmentActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Congratulations")
                                .setContentText("Your appointment is registered successfully")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intent = new Intent(ScheduleAppointmentActivity.this, PatientDash.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        new SweetAlertDialog(ScheduleAppointmentActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void cancel(View view) {
        finish();
    }

    public void proceed(View view) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Appointments");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointments.clear();
                for(DataSnapshot data : dataSnapshot.getChildren())
                {
                    Appointment appointment = data.getValue(Appointment.class);
                    if(appointment.getEmailDoctor().equals(emailDoctor) && appointment.getDate().equals(currentDateString))
                    {
                        if(appointment.getEmailPatient().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                        {
                            new SweetAlertDialog(ScheduleAppointmentActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("You can't have more than one appointment with the same doctor in one day")
                                    .show();
                            return;
                        }
                        appointments.add(appointment);
                    }
                }
                if(appointments.size() >= 42)
                {
                    new SweetAlertDialog(ScheduleAppointmentActivity.this)
                            .setTitleText("Scheduling an appointment!")
                            .setContentText("Day chosen is full, please pick another day !")
                            .show();
                    return;
                }
                else
                {
                    //int x=((int)appointments.size())/(int)num_of_patients_at_same_time;
                    int x=1;
                    Log.d("debug: ","x: "+x+", app.size:"+appointments.size()+" num: "+num_of_patients_at_same_time);
                    double timeFull = (double)(x*30)/60;
                    int hour =timepicker.getHour();
                    double minutes =timepicker.getMinute();
                    Log.d("debug","timeFull: "+timeFull);
                    Log.d("debug","hour: "+hour);
                    Log.d("debug","minutes: "+minutes);

                    timeOfAppointment = hour+":"+minutes;
                }
                new SweetAlertDialog(ScheduleAppointmentActivity.this)
                        .setTitleText("Scheduling appointment")
                        .setContentText("If you confirm below, your appointment will be scheduled the "+currentDateString+" at "+timeOfAppointment+" !")
                        .hide();

                time.setText(timeOfAppointment);
                time.setVisibility(View.VISIBLE);
                timepicker.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
