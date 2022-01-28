package com.example.mediplus.Emergency;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mediplus.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class phone extends AppCompatActivity {

    EditText name, email, passw, phno1, phno2, phno3 ;
    Button but;
    Button but2;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_emergency);
        firebaseAuth = FirebaseAuth.getInstance();
        phno1 = (EditText)findViewById(R.id.ph1);
        phno2 = (EditText)findViewById(R.id.ph2);
        phno3 = (EditText)findViewById(R.id.ph3);
        but = (Button)findViewById(R.id.button);
        but2 = (Button)findViewById(R.id.button4);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ph1 = phno1.getText().toString();
                String ph2 = phno2.getText().toString();
                String ph3 = phno3.getText().toString();





                 if(ph1.isEmpty())
                {
                    phno1.setError("Field must not be empty");
                }

                sendphno();
                /*firebaseAuth.createUserWithEmailAndPassword(email1,passw1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ph.this,"Registration completed",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(phone.this, login.class));
                            sendphno();

                        }
                        else
                        {
                            Toast.makeText(ph.this,"Registration failed, try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
            }
        });

    }
    public void sendphno()
    {
        String ph1 = phno1.getText().toString();
        String ph2 = phno2.getText().toString();
        String ph3 = phno3.getText().toString();
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myref = firebaseDatabase.getReference("Emergency");
        userphone userph = new userphone(ph1 , ph2, ph3);
        myref.child(firebaseAuth.getUid()).setValue(userph);

    }
}