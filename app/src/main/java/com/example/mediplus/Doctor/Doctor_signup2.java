package com.example.mediplus.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.mediplus.Database.DoctorHelperClass;
import com.example.mediplus.Patient.Patient_login;
import com.example.mediplus.R;
import com.example.mediplus.appointment.models.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
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

public class Doctor_signup2 extends AppCompatActivity {

    String codeBySystem;
    PinView pinFromUser;
    //TextView otpDescriptionText;
    String fullName, phoneNo, email, password, whatToDO, speciality;
    private FirebaseAuth fbAuth;
    private FirebaseFirestore fStore;
    private String userID;
    private static final String TAG = "Register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup2);

        pinFromUser = findViewById(R.id.otp_doc);

        //otpDescriptionText = findViewById(R.id.otp_description_text);

        //Get all the data from Intent
        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        phoneNo = getIntent().getStringExtra("phoneNo");
        whatToDO = getIntent().getStringExtra("whatToDO");
        speciality = getIntent().getStringExtra("speciality");

        fbAuth = FirebaseAuth.getInstance();
        fStore = fStore = FirebaseFirestore.getInstance();

        //otpDescriptionText.setText("Enter One Time Password Sent Onn"+phoneNo);

        sendVerificationCodeToUser(phoneNo);

    }

    private void sendVerificationCodeToUser(String phoneNo) {

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
                    Toast.makeText(Doctor_signup2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };


    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);


    fbAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete (@NonNull Task < AuthResult > task) {
            if (task.isSuccessful()) {
                Doctor addNewUser = new Doctor(fullName, email, phoneNo, password, speciality);
                FirebaseDatabase.getInstance().getReference("Doctors")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(addNewUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Doctor_signup2.this, "User created successfully", Toast.LENGTH_LONG).show();

                            userID = fbAuth.getUid();
                            DocumentReference documentReference = fStore.collection("doctors").document(FirebaseAuth.getInstance().getUid());
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
                            Toast.makeText(Doctor_signup2.this, "User creation failed", Toast.LENGTH_LONG).show();
                            // ld.dismissDialog();
                        }
                    }
                });
            }
            else {
                // ld.dismissDialog();
                Toast.makeText(Doctor_signup2.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    });
}

   /* private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {


                        //Verification completed successfully here Either
                        // store the data or do whatever desire
                        if (whatToDO.equals("updateData")) {
                            updateOldUsersData();
                        } else if (whatToDO.equals("createNewUser")) {
                            storeNewUsersData();
                        }
                        Toast.makeText(Doctor_signup2.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(Doctor_signup2.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

    private void updateOldUsersData() {
    }

    private void storeNewUsersData() {

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Doctors");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        //Create helperclass reference and store data using firebase
        DoctorHelperClass addNewUser = new DoctorHelperClass(fullName, email, phoneNo, password, speciality);
        reference.child(uid).setValue(addNewUser);

        //We will also create a Session here in next videos to keep the user logged In

        startActivity(new Intent(getApplicationContext(), Patient_login.class));
        finish();
    }


    public void signup(View view) {

        String code = pinFromUser.getText().toString();
        if (!code.isEmpty()) {
            verifyCode(code);
        }


    }


}
