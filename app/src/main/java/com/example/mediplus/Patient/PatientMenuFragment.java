package com.example.mediplus.Patient;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.mediplus.ChangePassword;
import com.example.mediplus.Doctor.Doctor_login;
import com.example.mediplus.R;
import com.example.mediplus.appointment.MyDoctorsActivity;
import com.example.mediplus.appointment.PatientProfileInformations;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PatientMenuFragment extends Fragment {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    TextView logoutTextView, changePasswordTextView, profileTextView,vaccinate,donat,gg;

    public PatientMenuFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_patient_menu, container, false);

        TextView termsAndPolicyTextView = view.findViewById(R.id.terms_and_policy_textView);
        TextView helpAndSupportTextView = view.findViewById(R.id.help_and_support_textView);
        TextView shareTextView = view.findViewById(R.id.share_textView);
        TextView rateTextView = view.findViewById(R.id.rate_textView);
        profileTextView = view.findViewById(R.id.profile_textView);
        changePasswordTextView = view.findViewById(R.id.change_password_textView);
        logoutTextView = view.findViewById(R.id.logout_textView);
        vaccinate = view.findViewById(R.id.vaccine);
        donat = view.findViewById(R.id.donate);
        gg= view.findViewById(R.id.gogreen);

        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(getActivity(), Doctor_login.class));
                }
            }
        };

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        vaccinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyDoctorsActivity.class);
                startActivity(intent);
            }
        });


        donat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DonateActivity.class);
                startActivity(intent);
            }
        });

        gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GGreen.class);
                startActivity(intent);
            }
        });


        profileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PatientProfileInformations.class);
                startActivity(intent);
            }
        });

       changePasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });

       logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Info");
                builder.setMessage("Do you want to logout ??");
                builder.setPositiveButton("Take me away!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        signOut();
                        startActivity(new Intent(getActivity(), Doctor_login.class));
                        Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }



    private void signOut() {
        auth.signOut();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}