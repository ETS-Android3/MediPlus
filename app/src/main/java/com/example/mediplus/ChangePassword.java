package com.example.mediplus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Patient.PatientDash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

public class ChangePassword extends AppCompatActivity {

    private TextInputLayout emailEditTxt;
    private Button submitBtton;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_change_password);

        emailEditTxt = findViewById(R.id.change_pass_emailEditText);
        submitBtton = findViewById(R.id.change_pass_submit_button);

        auth = FirebaseAuth.getInstance();

        SimpleArcDialog mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(new ArcConfiguration(this));
        mDialog.hide();

        submitBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditTxt.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailEditTxt.setError("Email is Required.");
                    return;
                }
                mDialog.show();
                // progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ChangePassword.this, "We have sent you instructions to Change your password!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ChangePassword.this, PatientDash.class);
                                    startActivity(intent);
                                } else {
                                    mDialog.hide();
                                    emailEditTxt.setError("Enter valid Email");
                                    return;
                                }
                                mDialog.hide();
                            }
                        });
            }
        });
    }

    public void backHome(View view) {
        Intent intent = new Intent(ChangePassword.this, PatientDash.class);
        startActivity(intent);
    }
}