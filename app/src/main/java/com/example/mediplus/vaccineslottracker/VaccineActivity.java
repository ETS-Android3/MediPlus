package com.example.mediplus.vaccineslottracker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import com.example.mediplus.R;
import com.example.mediplus.vaccineslottracker.services.DatePickerFragment;
import com.example.mediplus.vaccineslottracker.services.VaccineTrackerService;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;

public class VaccineActivity extends AppCompatActivity {

    private int notificationId = 1;


    TextInputLayout pinCodesTextBox;
    DatePicker datePick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_activity);
        createNotificationChannel();
    }

    /**
     * creates a notification channel
     */
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            String CHANNEL_ID = getString(R.string.channel_id);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * starts tracking the vaccine slot per min
     * @param view
     */
    public void startTracking(View view){

        pinCodesTextBox =  findViewById(R.id.pinCodes);
        //EditText dateView = (EditText) findViewById(R.id.date);

        datePick=findViewById(R.id.date_picker);

        String pinCodesString = pinCodesTextBox.getEditText().getText().toString().trim();


        int day = datePick.getDayOfMonth();
        int month = datePick.getMonth();
        int year = datePick.getYear();

        String date = day + "-" + month + "-" + year;

        if(pinCodesString != null && date != null) {
            String[] pinCodes = pinCodesString.split(",");

            startTrackingService(pinCodes, date);
        }
    }

    private void startTrackingService(String[] pinCodes, String date) {

        /*Toast serviceStarted = Toast.makeText(getApplicationContext(), "Will Notify When Slot is Available", Toast.LENGTH_LONG);
        serviceStarted.show();*/

        createServiceStartedNotification();

        VaccineTrackerService vaccineTrackerService = intiVaccineTrackingService(pinCodes, date);

        Timer vaccineTrackerTimer = getVaccineTrackerTimer();

        vaccineTrackerTimer.schedule(vaccineTrackerService, 5000,60000);

    }

    private Timer getVaccineTrackerTimer() {
        Timer vaccineTrackerTimer = new Timer("Vaccine slot tracker");
        return vaccineTrackerTimer;
    }

    private VaccineTrackerService intiVaccineTrackingService(String[] pinCodes, String date) {
        VaccineTrackerService vaccineTrackerService = new VaccineTrackerService();

        vaccineTrackerService.setPinCodes(pinCodes);
        vaccineTrackerService.setApplicationContext(getApplicationContext());
        vaccineTrackerService.setChannelId(getString(R.string.channel_id));
        vaccineTrackerService.setDate(date);
        return vaccineTrackerService;
    }

    private void createServiceStartedNotification() {
        String CHANNEL_ID = getString(R.string.channel_id);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.service_started_message))
                .setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId++, builder.build());
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();

        EditText dateView = (EditText) findViewById(R.id.date);
        ((DatePickerFragment)newFragment).setDateView(dateView);

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}