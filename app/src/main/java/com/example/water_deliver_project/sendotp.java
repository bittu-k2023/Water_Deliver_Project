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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sendotp extends AppCompatActivity {

    private ImageView imgBackSotp;
    private EditText etSendotp;
    private ImageView btnSendotpp;
    String mobnum;


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
                mobnum=etSendotp.getText().toString().trim();
                if(mobnum.equals(""))
                {
                    Toast.makeText(sendotp.this, "Mobile Number is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!isvalidmobile())
                    {
                        Toast.makeText(sendotp.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sendverification(mobnum);
                    }
                }
            }
        });
    }

    private boolean isvalidmobile() {
        Pattern pattern=Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher match=pattern.matcher(mobnum);
        return (match.find() && match.group().equals(mobnum));
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