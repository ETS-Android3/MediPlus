package com.example.mediplus.Doctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.DocPat;
import com.example.mediplus.Patient.PatientDash;
import com.example.mediplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;

public class Doctor_login extends AppCompatActivity {

    Button b2;
    TextInputLayout password,phoneNumber;
    CheckBox rememberMe;
    private FirebaseAuth mAuth;
    TextInputEditText phoneNumberEditText, passEditText;
    SharedPreferences sp;
    DatabaseReference ref;
    SimpleArcLoader simpleArcLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_doctor_login);


        b2=findViewById(R.id.doc_signup);
        password=findViewById(R.id.login_password);
        phoneNumber=findViewById(R.id.login_phone_number);
        //simpleArcLoader =findViewById(R.id.loginloader);

        mAuth = FirebaseAuth.getInstance();
        b2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DocPat.class);
            startActivity(intent);
            finish();
        });

    }


    public void DoctorLogIn(View view) {


        String tempLogin = phoneNumber.getEditText().getText().toString().trim();
        String tempPassword = password.getEditText().getText().toString().trim();
        if (
                TextUtils.isEmpty(tempLogin)
                        || TextUtils.isEmpty(tempPassword)
        ) {
            Toast.makeText(Doctor_login.this, "Login or Password are empty", Toast.LENGTH_SHORT).show();
        } else {
           // simpleArcLoader.start();
           //final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
           // pDialog.getProgressHelper().setBarColor(Color.parseColor("#da0384"));
           // pDialog.setTitleText("Loading");
           // pDialog.setCancelable(false);
           // pDialog.show();

            SimpleArcDialog mDialog = new SimpleArcDialog(this);
            mDialog.setConfiguration(new ArcConfiguration(this));
            mDialog.show();
            mAuth.signInWithEmailAndPassword(tempLogin, tempPassword).addOnCompleteListener(Doctor_login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        ref = FirebaseDatabase.getInstance().getReference("Doctors");
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String email = dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").getValue(String.class);
                                if (email == null) {
                                   // pDialog.hide();
                                    /*if (rememberMe.isChecked()) {
                                        sp.edit().putBoolean("loggedPatient", true).apply();
                                    } else
                                        sp.edit().putBoolean("loggedPatient", false).apply();*/
                                    Intent intent = new Intent(Doctor_login.this, PatientDash.class);
                                    Doctor_login.this.startActivity(intent);
                                } else {
                                  //  pDialog.hide();
                                   /* if (rememberMe.isChecked()) {
                                        sp.edit().putBoolean("loggedDoctor", true).apply();
                                    } else
                                        sp.edit().putBoolean("loggedDoctor", false).apply();*/
                                    Intent intent = new Intent(Doctor_login.this, DoctorDashboard.class);
                                    Doctor_login.this.startActivity(intent);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    } else {
                       // errorMessage.setVisibility(View.VISIBLE);
                       // pDialog.hide();
                    }
                }
            });
        }
    }









      /*  String _password = password.getEditText().getText().toString().trim();
        String _PhoneNumber = phoneNumber.getEditText().getText().toString().trim();

        if (_PhoneNumber.charAt(0) == '0') {
            _PhoneNumber = _PhoneNumber.substring(1);
        }
        String _phoneNo = "+" + countryCodePicker.getSelectedCountryCode() + _PhoneNumber;

        if(rememberMe.isChecked()) {

            SessionManager sessionManager =new SessionManager(Patient_login.this,SessionManager.SESSION_REMEMBER_ME);
            sessionManager.createRememberMeSession(_PhoneNumber,_password);
        }FirebaseUser currentUser = fAuth.getCurrentUser();


        Query checkUser= FirebaseDatabase.getInstance().getReference("Doctors").orderByChild("phoneNo").equalTo(_phoneNo);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);


                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    assert user != null;
                    String uid = user.getUid();

                    String systemPassword = dataSnapshot.child(uid).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        //retrieving data*/

                       /* String _fullname=dataSnapshot.child(_phoneNo).child("fulName").getValue(String.class);
                        String _address=dataSnapshot.child(_phoneNo).child("address").getValue(String.class);
                        String _email=dataSnapshot.child(_phoneNo).child("email").getValue(String.class);
                        String _phoneNumber=dataSnapshot.child(_phoneNo).child("phoneNo").getValue(String.class);
                        String _password=dataSnapshot.child(_phoneNo).child("fulName").getValue(String.class);
                        String _dob=dataSnapshot.child(_phoneNo).child("date").getValue(String.class);
                        String _gender=dataSnapshot.child(_phoneNo).child("gender").getValue(String.class);*/

                        //session

                       // SessionManager sessionManager= new SessionManager(Doctor_login.this, SessionManager.SESSION_USERSESSION);
                       // sessionManager.createLoginSession(_fullname, _address , _email, _phoneNumber, _dob,_gender,_password);

                   /*     startActivity(new Intent(getApplicationContext(), DoctorDashboard.class));

                    } else {
                        Toast.makeText(Doctor_login.this, "password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Doctor_login.this, "User not Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Doctor_login.this, "error",Toast.LENGTH_SHORT).show();

            }
        });
    }*/

    public void callForgetPassword(View view) {

    }
}