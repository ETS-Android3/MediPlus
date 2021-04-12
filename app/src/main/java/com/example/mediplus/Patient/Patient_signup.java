package com.example.mediplus.Patient;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.Doctor.Doctor_login;
import com.example.mediplus.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class Patient_signup extends AppCompatActivity {

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;
    TextInputLayout fullName,phoneNumber,address,email,password;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patient_signup
        );

        //Hooks for animation
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);


        //hooks for validation
        fullName=findViewById(R.id.pfullname);
        address=findViewById(R.id.paddress);
        email=findViewById(R.id.pemail);
        password=findViewById(R.id.signup_password);
        phoneNumber=findViewById(R.id.phone_number);
        countryCodePicker=findViewById(R.id.country_code_picker);

    }

    public void callNextSigupScreen(View view) {


        if (!validateFullName() | !validatePhoneNumber() | !validateEmail() | !validatePassword()) {
            return;
        }
        String _password = password.getEditText().getText().toString().trim();
        String _email = email.getEditText().getText().toString().trim();
        String _fullName = fullName.getEditText().getText().toString().trim();
        String _address = address.getEditText().getText().toString().trim();

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        if (_getUserEnteredPhoneNumber.charAt(0) == '0') {
            _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        }
        final String _phoneNo = "+" + countryCodePicker.getSelectedCountryCode() + _getUserEnteredPhoneNumber;



        Intent intent = new Intent(getApplicationContext(), patient_signup2.class);

        intent.putExtra("fullName", _fullName);
        intent.putExtra("email",_email);
        intent.putExtra("address", _address);
        intent.putExtra("password", _password);
        intent.putExtra("phoneNo", _phoneNo);
        intent.putExtra("whatToDO", "createNewUser");

        Toast.makeText(Patient_signup.this, "Verification Completed"+_phoneNo, Toast.LENGTH_SHORT).show();

        //Add Shared Animation
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair(next, "transition_next_btn");
        pairs[2] = new Pair(login, "transition_login_btn");
        pairs[3] = new Pair(titleText, "transition_title_text");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Patient_signup.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    private boolean validateFullName () {
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail () {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validatePassword () {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
               //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=S+$)" +           //no white spaces
               // ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } /*else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 characters!");
            return false;
        }*/ else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhoneNumber () {
        String val = phoneNumber.getEditText().getText().toString().trim();
        String checkspaces = "Aw{1,20}z";
        if (val.isEmpty()) {
            phoneNumber.setError("Enter valid phone number");
            return false;
        } /*else if (!val.matches(checkspaces)) {
            phoneNumber.setError("No White spaces are allowed!");
            return false;
        }*/ else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }




    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Doctor_login.class));
        finish();
    }

}