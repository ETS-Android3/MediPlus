package com.example.mediplus.Patient;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mediplus.Covid.user.news.News;
import com.example.mediplus.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class PatientDash extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    FirebaseUser user;
    String uid,phoneno;
    String patientFullName;
    DatabaseReference databaseReference;
  //  ImageView profilePicture;
   // TextView fullName,cin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patient_dash);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_dashbord,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PatientDashboardFragment()).commit();
        bottomMenu();




      //  fullName = findViewById(R.id.fullName);
     //   cin = findViewById(R.id.cin);
      //  profilePicture = findViewById(R.id.profile_image);

        //SessionManager sessionManager=new SessionManager(this);
        //HashMap<String, String> userDetails =sessionManager.getUserDetailFromSession();


        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
       // Toast.makeText(PatientDash.this, uid, Toast.LENGTH_SHORT).show();




        databaseReference = FirebaseDatabase.getInstance().getReference("Patients");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patientFullName = dataSnapshot.child(uid).child("fullName").getValue(String.class);
                phoneno = dataSnapshot.child(uid).child("phoneNo").getValue(String.class);

                Toast.makeText(PatientDash.this, patientFullName, Toast.LENGTH_LONG).show();

              /*  StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference profileRef = storageReference.child("Profile pictures").child(FirebaseAuth.getInstance().getCurrentUser().getEmail() + ".jpg");
                profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilePicture);
                    }
                });*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onBackPressed() {

        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        dialog.setConfirmText("Yes");
        dialog.setCancelText("No");
        dialog.setContentText("Are you sure want to close Mediplus?");
        dialog.setTitleText("Close application");
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finishAffinity();
                System.exit(0);
            }
        });
        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.cancel();
            }
        });

        dialog.show();
    }



    private void bottomMenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.bottom_nav_dashbord:
                        fragment = new PatientDashboardFragment();
                        break;
                    case R.id.bottom_nav_Menu:
                        fragment = new PatientMenuFragment();
                        break;
                    case R.id.bottom_nav_profile:
                        fragment = new News();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });

    }

}

