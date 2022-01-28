package com.example.mediplus.vaccineslottracker.services;

import android.content.Context;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mediplus.R;
import com.example.mediplus.vaccineslottracker.beans.Session;
import com.example.mediplus.vaccineslottracker.beans.Sessions;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class VaccineTrackerService extends TimerTask {

    private Context applicationContext;
    private String channelId;
    private int notificationId = 1;
    private String[] pinCodes;
    private String date;


    @Override
    public void run() {
        Arrays.stream(getPinCodes()).forEach(pinCode->fetchSlotAvailability(pinCode.trim()));
    }


    private void createNotification(Session session){

        Date currentTime = new Date();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), getChannelId())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Slot available at "+session.getPincode())
                .setContentText(session.getAvailable_capacity()+" vaccines are available at Pincode: "+session.getPincode())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Number of vaccines available: "+session.getAvailable_capacity()
                                +"\n Time: "+currentTime.toString()
                                +"\n Vaccine Date: "+session.getDate()
                                +"\n Address: "+session.getAddress()
                                +"\n Age Limit: "+session.getMin_age_limit()
                                +"\n Fees: "+session.getFee_type()
                                +"\n Center ID: "+session.getCenter_id()))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId++, builder.build());
    }

    private void fetchSlotAvailability(String pinCode){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode="+pinCode+"&date="+getDate();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast error_in_rest_call = Toast.makeText(getApplicationContext(), "Error in Rest call", Toast.LENGTH_LONG);
                error_in_rest_call.show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void handleResponse(String response) {

        Gson gson = new Gson();
        Sessions sessions = gson.fromJson(response, Sessions.class);

        List<Session> sessionList = sessions.getSessions();

        sessionList
                .stream()
                .filter(session -> session.getAvailable_capacity()>0)
                .forEach(session -> {
            createNotification(session);
        });
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


    public String[] getPinCodes() {
        return pinCodes;
    }

    public void setPinCodes(String[] pinCodes) {
        this.pinCodes = pinCodes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
