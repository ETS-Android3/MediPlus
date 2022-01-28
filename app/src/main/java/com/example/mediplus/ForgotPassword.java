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

import com.example.mediplus.Doctor.Doctor_login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

public class ForgotPassword extends AppCompatActivity {
    private TextInputLayout emailEditText;
    private Button submitButton;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.forgot_pass_email_editText);
        submitButton = findViewById(R.id.forgot_pass_submit_button);

        auth = FirebaseAuth.getInstance();

        SimpleArcDialog mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(new ArcConfiguration(this));
        mDialog.hide();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Email is Required.");
                    return;
                }
                mDialog.show();
               // progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgotPassword.this, Doctor_login.class);
                                    startActivity(intent);
                                } else {
                                    mDialog.hide();
                                    emailEditText.setError("enter valid Email");
                                    return;
                                }
                                mDialog.hide();
                            }
                        });
            }
        });
    }

    public void backHome(View view) {
        Intent intent = new Intent(ForgotPassword.this, Doctor_login.class);
        startActivity(intent);
    }
}