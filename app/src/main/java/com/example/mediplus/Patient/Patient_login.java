package com.example.mediplus.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.mediplus.Database.SessionManager;
import com.example.mediplus.Patient.Patient_signup;
import com.example.mediplus.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;

public class Patient_login extends AppCompatActivity {

    Button  b2;
    TextInputLayout password,phoneNumber;
    CountryCodePicker countryCodePicker;
    CheckBox rememberMe;
    TextInputEditText phoneNumberEditText, passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patient_login);
        b2 = findViewById(R.id.signup);

        b2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Patient_signup.class);
            startActivity(intent);
            finish();
        });

        password=findViewById(R.id.login_password);
        phoneNumber=findViewById(R.id.login_phone_number);
        countryCodePicker=findViewById(R.id.login_country_code_picker);
        rememberMe=findViewById(R.id.remember_me);
        phoneNumberEditText=findViewById(R.id.login_phone_number_editText);
        passEditText=findViewById(R.id.login_password_editText);


        SessionManager sessionManager =new SessionManager(Patient_login.this,SessionManager.SESSION_REMEMBER_ME);
        if(sessionManager.checkRememberMe()){
            HashMap<String,String> rememberMeDetails =sessionManager.getRememberMeSession();
            phoneNumberEditText.setText(rememberMeDetails.get(SessionManager.KEY_RMPHONENUMBER));
            passEditText.setText(rememberMeDetails.get(SessionManager.KEY_RMPASSWORD));

        }

    }

    public void patientLoginclick(View view) {

        String _password = password.getEditText().getText().toString().trim();
        String _PhoneNumber = phoneNumber.getEditText().getText().toString().trim();

        if (_PhoneNumber.charAt(0) == '0') {
            _PhoneNumber = _PhoneNumber.substring(1);
        }
        String _phoneNo = "+" + countryCodePicker.getSelectedCountryCode() + _PhoneNumber;

      /*  if(rememberMe.isChecked()) {

            SessionManager sessionManager =new SessionManager(Patient_login.this,SessionManager.SESSION_REMEMBER_ME);
            sessionManager.createRememberMeSession(_PhoneNumber,_password);
        }*/



        Query checkUser= FirebaseDatabase.getInstance().getReference("Patients").orderByChild("phoneNo").equalTo(_phoneNo);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = dataSnapshot.child(_phoneNo).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        //retrieving data

                        String _fullname=dataSnapshot.child(_phoneNo).child("fulName").getValue(String.class);
                        String _address=dataSnapshot.child(_phoneNo).child("address").getValue(String.class);
                        String _email=dataSnapshot.child(_phoneNo).child("email").getValue(String.class);
                        String _phoneNumber=dataSnapshot.child(_phoneNo).child("phoneNo").getValue(String.class);
                        String _password=dataSnapshot.child(_phoneNo).child("fulName").getValue(String.class);
                        String _dob=dataSnapshot.child(_phoneNo).child("date").getValue(String.class);
                        String _gender=dataSnapshot.child(_phoneNo).child("gender").getValue(String.class);

                        //session

                        SessionManager sessionManager= new SessionManager(Patient_login.this, SessionManager.SESSION_USERSESSION);
                        sessionManager.createLoginSession(_fullname, _address , _email, _phoneNumber, _dob,_gender,_password);

                        startActivity(new Intent(getApplicationContext(), PatientDash.class));

                    } else {
                        Toast.makeText(Patient_login.this, "password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Patient_login.this, "User not Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
              Toast.makeText(Patient_login.this, "error",Toast.LENGTH_SHORT).show();

            }
        });



}

    public void callForgetPassword(View view) {
    }

}



