package com.example.mediplus.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediplus.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class Doctor_signup extends AppCompatActivity {

    ImageView backBtn;
    Button next, login;
    TextView titleText;
    TextInputLayout fullName,phoneNumber,hos_code,email,password;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        fullName=findViewById(R.id.dfullname);
        hos_code=findViewById(R.id.dcode);
        email=findViewById(R.id.demail);
        password=findViewById(R.id.dpassword);
        phoneNumber=findViewById(R.id.dphone_number);
        countryCodePicker=findViewById(R.id.dcountry_code_picker);
    }


    public void callNextSigup(View view) {


        if (!validateFullName() | !validatePhoneNumber() | !validateEmail() | !validatePassword()) {
            return;
        }
        String _password = password.getEditText().getText().toString().trim();
        String _email = email.getEditText().getText().toString().trim();
        String _fullName = fullName.getEditText().getText().toString().trim();
        String _code = hos_code.getEditText().getText().toString().trim();

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        if (_getUserEnteredPhoneNumber.charAt(0) == '0') {
            _getUserEnteredPhoneNumber = _getUserEnteredPhoneNumber.substring(1);
        }
        final String _phoneNo = "+" + countryCodePicker.getSelectedCountryCode() + _getUserEnteredPhoneNumber;

       if(_code.equals("100")) {

            Intent intent = new Intent(getApplicationContext(), Doctor_signup2.class);

            intent.putExtra("fullName", _fullName);
            intent.putExtra("email", _email);
            intent.putExtra("password", _password);
            intent.putExtra("phoneNo", _phoneNo);
            intent.putExtra("whatToDO", "createNewUser");

            startActivity(intent);

            Toast.makeText(Doctor_signup.this, "Verification Code Sent to" + _phoneNo, Toast.LENGTH_LONG).show();
       }
        else{
           Toast.makeText(Doctor_signup.this, "Mismatch in code" , Toast.LENGTH_SHORT).show();

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

    public void callLogin(View view) {


    }
}
