package com.example.mediplus.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.mediplus.Database.PatientHelperclass;
import com.example.mediplus.Doctor.Doctor_login;
import com.example.mediplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class patient_signup2 extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    String codeBySystem;
    PinView pinFromUser;
    //TextView otpDescriptionText;
    String fullName, phoneNo, email, address, password, whatToDO,gender ,date;
    RadioGroup group;
    RadioButton selectedgender;
    DatePicker datePicker;
    private FirebaseAuth fbAuth;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userID;
    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patient_signup2);


        pinFromUser = findViewById(R.id.otp);
        group=findViewById(R.id.radio_group);
        datePicker=findViewById(R.id.age_picker);

        //otpDescriptionText = findViewById(R.id.otp_description_text);

        //Get all the data from Intent
        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
       address = getIntent().getStringExtra("address");
        password = getIntent().getStringExtra("password");
       // date = getIntent().getStringExtra("date");
       // gender = getIntent().getStringExtra("gender");
        phoneNo = getIntent().getStringExtra("phoneNo");
        whatToDO = getIntent().getStringExtra("whatToDO");



        //otpDescriptionText.setText("Enter One Time Password Sent Onn"+phoneNo);

        sendVerificationCodeToUser(phoneNo);

        fbAuth = FirebaseAuth.getInstance();
        fStore = fStore = FirebaseFirestore.getInstance();

    }

    private void sendVerificationCodeToUser(String phoneNo) {

          /*  PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNo,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                     this, // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks*/

        PhoneAuthProvider.verifyPhoneNumber(
                PhoneAuthOptions
                        .newBuilder(FirebaseAuth.getInstance())
                        .setActivity(this)
                        .setPhoneNumber(phoneNo)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setCallbacks(mCallbacks)
                        .build());
        }




    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        pinFromUser.setText(code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    Toast.makeText(patient_signup2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };


    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);


    /*private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {


                        //Verification completed successfully here Either

                        if (whatToDO.equals("updateData")) {
                            updateOldUsersData();
                        } else if (whatToDO.equals("createNewUser")) {
                            storeNewUsersData();
                        }
                        Toast.makeText(patient_signup2.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(patient_signup2.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


     */

        fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    PatientHelperclass addNewUser = new PatientHelperclass(fullName, address, email, phoneNo, password, date, gender);
                    FirebaseDatabase.getInstance().getReference("Patients")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(addNewUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(patient_signup2.this, "User created successfully", Toast.LENGTH_LONG).show();

                                userID = fbAuth.getUid();
                                DocumentReference documentReference = fStore.collection("users").document(FirebaseAuth.getInstance().getUid());
                                Map<String, Object> user = new HashMap<>();
                                user.put("id", userID);
                                user.put("fullName", fullName);
                                user.put("email", email);
                                //insert user in the database
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user Profile is created for" + userID);
                                        startActivity(new Intent(getApplicationContext(), Doctor_login.class));
                                    }
                                });
                                // ld.dismissDialog();
                            } else {
                                Toast.makeText(patient_signup2.this, "User creation failed", Toast.LENGTH_LONG).show();
                                // ld.dismissDialog();
                            }
                        }
                    });
                } else {
                    // ld.dismissDialog();
                    Toast.makeText(patient_signup2.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    private void registerUser(final String email, String password, final String fullName, final String phone, final String state) {
        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //send verification link
                    FirebaseUser fuser = fAuth.getCurrentUser();
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(patient_signup2.this, "Verification email has been sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                        }
                    });

                    Toast.makeText(patient_signup2.this, "User created", Toast.LENGTH_SHORT).show();
                    // insert user in a document

                    userID = fuser.getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("id", userID);
                    user.put("fullName", fullName);
                    user.put("email", email);
                    user.put("phone", phone);
                    user.put("state", state);
                    //insert user in the database
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: user Profile is created for" + userID);
                            startActivity(new Intent(getApplicationContext(), Doctor_login.class));
                        }
                    });

                }

            }
        });
    }



                private void updateOldUsersData() {
            }

            private void storeNewUsersData() {

                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("Patients");

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                //Create helperclass reference and store data using firebase

                PatientHelperclass addNewUser = new PatientHelperclass(fullName, address, email, phoneNo, password, date, gender);
                reference.child(uid).setValue(addNewUser);

                //We will also create a Session here in next videos to keep the user logged In

                startActivity(new Intent(getApplicationContext(), Patient_login.class));
                finish();
            }


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            public void verifyAndsign(View view) {

                selectedgender = findViewById(group.getCheckedRadioButtonId());
                gender = selectedgender.getText().toString();

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                date = day + "/" + month + "/" + year;

                String code = pinFromUser.getText().toString();
                if (!code.isEmpty()) {
                    verifyCode(code);
                }


            }
        }
