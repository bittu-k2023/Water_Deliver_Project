package com.example.water_deliver_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class sendotp extends AppCompatActivity {

    private ImageView imgBackSotp;
    private EditText etSendotp;
    private ImageView btnSendotpp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendotp);

        imgBackSotp = (ImageView) findViewById(R.id.img_back_sotp);
        etSendotp = (EditText) findViewById(R.id.et_sendotp);
        btnSendotpp = (ImageView) findViewById(R.id.btn_sendotpp);
        imgBackSotp.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),WLogin.class);
            startActivity(intent);
        });
        btnSendotpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num=etSendotp.getText().toString().trim();
                if(num.equals(""))
                {
                    Toast.makeText(sendotp.this, "Phone Number can't be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(num.length()<10)
                    {
                        Toast.makeText(sendotp.this, "Enter correct phone number", Toast.LENGTH_SHORT).show();
                    }
                    sendverification(num);
                }
            }
        });
    }

    private void sendverification(String num) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + num, 60, TimeUnit.SECONDS, sendotp.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(sendotp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Intent intent=new Intent(getApplicationContext(),SubmitOTP.class);
                String mobile=etSendotp.getText().toString();
                intent.putExtra("mob",mobile);
                intent.putExtra("verificationid",s);
                startActivity(intent);
            }
        });

    }
}