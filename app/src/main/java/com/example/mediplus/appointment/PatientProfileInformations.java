package com.example.mediplus.appointment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mediplus.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientProfileInformations extends AppCompatActivity {
    TextView fullName, cin, email, phoneNumber, maritalStatus, birthDate,address;
    CircleImageView circleImageView;
    FirebaseUser user;
    String uid;
    String Uri;
    DatabaseReference databaseReference;
    String firstNameRetrieved,cinRetrieved, emailRetrieved, phoneNumberRetrieved, maritalStatusRetrieved, birthdateRetrieved, bloodgroupretrieved, addressRetrived ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_informations);

        fullName = findViewById(R.id.fullName);
        cin = findViewById(R.id.cin);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        maritalStatus = findViewById(R.id.maritalStatus);
        birthDate = findViewById(R.id.birthDate);
      //  bloodgroup=findViewById(R.id.blood_group);
        address=findViewById(R.id.setaddr);
        circleImageView = findViewById(R.id.profile_image);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Patients");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firstNameRetrieved =  dataSnapshot.child(uid).child("fullName").getValue(String.class);
                cinRetrieved = dataSnapshot.child(uid).child("password").getValue(String.class);
                emailRetrieved = dataSnapshot.child(uid).child("email").getValue(String.class);
                phoneNumberRetrieved = dataSnapshot.child(uid).child("phoneNo").getValue(String.class);
                birthdateRetrieved = dataSnapshot.child(uid).child("date").getValue(String.class);
                maritalStatusRetrieved = dataSnapshot.child(uid).child("gender").getValue(String.class);
                addressRetrived = dataSnapshot.child(uid).child("username").getValue(String.class);

                fullName.setText(firstNameRetrieved);
                cin.setText(cinRetrieved);
                email.setText(emailRetrieved);
                phoneNumber.setText(phoneNumberRetrieved);
                birthDate.setText(birthdateRetrieved);
                maritalStatus.setText(maritalStatusRetrieved);
                address.setText(addressRetrived);

                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference profileRef = storageReference.child("Profile_pictures").child(emailRetrieved + ".jpg");
                profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri = uri.toString();
                        Picasso.get().load(uri).into(circleImageView);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void startRevealActivity(View v) {
        //calculates the center of the View v you are passing
        int revealX = (int) (v.getX() + v.getWidth() / 2);
        int revealY = (int) (v.getY() + v.getHeight() / 2);

        //create an intent, that launches the second activity and pass the x and y coordinates
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        intent.putExtra("fullName", firstNameRetrieved);
        intent.putExtra("password", cinRetrieved);
        intent.putExtra("email", emailRetrieved);
        intent.putExtra("phoneNo", phoneNumberRetrieved);
        intent.putExtra("date", birthdateRetrieved);
        intent.putExtra("gender", maritalStatusRetrieved);
        intent.putExtra("imageUri", Uri);

        //just start the activity as an shared transition, but set the options bundle to null
        ActivityCompat.startActivity(this, intent, null);

        //to prevent strange behaviours override the pending transitions
        overridePendingTransition(0, 0);
    }

    public void editProfile(View view) {
        startRevealActivity(view);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PatientProfileInformations.this, MenuActivity.class);
        MenuActivity.setToken(1);
        startActivity(intent);
    }
}
