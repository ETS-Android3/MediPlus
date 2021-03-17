package com.example.mediplus.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Database.SessionManager;
import com.example.mediplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;

public class Patient_login extends AppCompatActivity {

    Button b2;
    TextInputLayout Password, Email;
    CountryCodePicker countryCodePicker;
    CheckBox rememberMe;
    TextInputEditText phoneNumberEditText, passEditText;
    private FirebaseAuth fAuth;

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

        Password = findViewById(R.id.login_password);
        Email = findViewById(R.id.login_phone_number);
       // countryCodePicker = findViewById(R.id.login_country_code_picker);
        rememberMe = findViewById(R.id.remember_me);
        phoneNumberEditText = findViewById(R.id.login_phone_number_editText);
        passEditText = findViewById(R.id.login_password_editText);



        fAuth=FirebaseAuth.getInstance();

        SessionManager sessionManager = new SessionManager(Patient_login.this, SessionManager.SESSION_REMEMBER_ME);
        if (sessionManager.checkRememberMe()) {
            HashMap<String, String> rememberMeDetails = sessionManager.getRememberMeSession();
            phoneNumberEditText.setText(rememberMeDetails.get(SessionManager.KEY_RMPHONENUMBER));
            passEditText.setText(rememberMeDetails.get(SessionManager.KEY_RMPASSWORD));

        }

    }

    public void patientLoginclick(View view) {


        String email = Email.getEditText().getText().toString().trim();
        String password = Password.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Email.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Password.setError("Password is Required.");
            return;
        }

        if (password.length() < 6) {
            Password.setError("Password Must be >= 6 Characters");
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);

        // authenticate the user

        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Patient_login.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Patient_login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), PatientDash.class));
                } else {
                    Toast.makeText(Patient_login.this, "Error ! " , Toast.LENGTH_SHORT).show();
                    //progressBar.setVisibility(View.GONE);
                }

            }
        });

    }




      /*  forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });*/





    public void callForgetPassword(View view) {
    }

}








     /*   String _password = password.getEditText().getText().toString().trim();
        String _PhoneNumber = phoneNumber.getEditText().getText().toString().trim();

        if (_PhoneNumber.charAt(0) == '0') {
            _PhoneNumber = _PhoneNumber.substring(1);
        }
        String _phoneNo = "+" + countryCodePicker.getSelectedCountryCode() + _PhoneNumber;

      /*  if(rememberMe.isChecked()) {

            SessionManager sessionManager =new SessionManager(Patient_login.this,SessionManager.SESSION_REMEMBER_ME);
            sessionManager.createRememberMeSession(_PhoneNumber,_password);
        }*/
     /*   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query checkUser = FirebaseDatabase.getInstance().getReference("Patients").orderByChild("phoneNo").equalTo(_phoneNo);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);


                    String systemPassword = dataSnapshot.child(uid).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        //retrieving data

                        String _fullname = dataSnapshot.child(uid).child("fullName").getValue(String.class);
                        String _address = dataSnapshot.child(uid).child("address").getValue(String.class);
                        String _email = dataSnapshot.child(uid).child("email").getValue(String.class);
                        String _phoneNumber = dataSnapshot.child(uid).child("phoneNo").getValue(String.class);
                        String _password = dataSnapshot.child(uid).child("password").getValue(String.class);
                        String _dob = dataSnapshot.child(uid).child("date").getValue(String.class);
                        String _gender = dataSnapshot.child(uid).child("gender").getValue(String.class);

                        //session

                        SessionManager sessionManager = new SessionManager(Patient_login.this, SessionManager.SESSION_USERSESSION);
                        sessionManager.createLoginSession(_fullname, _address, _email, _phoneNumber, _dob, _gender, _password);

                        startActivity(new Intent(getApplicationContext(), PatientDash.class));

                    } else {
                        Toast.makeText(Patient_login.this, "password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Patient_login.this, "User not Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Patient_login.this, "error", Toast.LENGTH_SHORT).show();

            }
        });*/


