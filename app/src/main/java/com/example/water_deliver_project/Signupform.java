package com.example.water_deliver_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signupform extends AppCompatActivity {

    private LinearLayout llback;
    private ImageView btnBack;
    private EditText etMobnumber;
    ImageView Continue;
    private EditText etUsrname;
    private EditText etUsrpassword;
    private EditText etUsrconfirmpassword;
    private LinearLayout llSignup;
    String  MobileNumber="";
    String Name="";
    String Password="";
    String ConfirmPassword="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);

        llback = (LinearLayout) findViewById(R.id.llback);
        btnBack = (ImageView) findViewById(R.id.btn_back);
        etMobnumber = (EditText) findViewById(R.id.et_mobnumber);
        etUsrname = (EditText) findViewById(R.id.et_usrname);
        etUsrpassword = (EditText) findViewById(R.id.et_usrpassword);
        etUsrconfirmpassword = (EditText) findViewById(R.id.et_usrconfirmpassword);
        llSignup = (LinearLayout) findViewById(R.id.ll_signup);
        Continue= (ImageView) findViewById(R.id.btn_continue);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signupform.this.finish();
            }
        });


        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileNumber=etMobnumber.getText().toString().trim();
                Name=etUsrname.getText().toString().trim();
                Password=etUsrpassword.getText().toString().trim();
                ConfirmPassword=etUsrconfirmpassword.getText().toString().toString();
                mysignup();
            }
        });
    }

    private void mysignup() {

        if(MobileNumber.equals("") || MobileNumber.length()==0)
        {
            Toast.makeText(this, "Mobile number field is Empty", Toast.LENGTH_SHORT).show();
        }
        else if(!isvalidmobile())
        {
            Toast.makeText(this, "Wrong Mobile Number", Toast.LENGTH_SHORT).show();
        }
        else {
            if(Name.equals("") || Name.length()==0)
            {
                Toast.makeText(this, "Name field is empty", Toast.LENGTH_SHORT).show();
            }
            else if(Name.length()<2)
            {
                Toast.makeText(this, "Name is too short", Toast.LENGTH_SHORT).show();
            }
            else {
                if(Password.equals("")||Password.length()==0)
                {
                    Toast.makeText(this, "Password field is empty", Toast.LENGTH_SHORT).show();
                }
                else if(Password.length()<6 || Password.length()>15)
                {
                    Toast.makeText(this, "Password must be 6 to 15 character only", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(ConfirmPassword.equals("")||ConfirmPassword.length()==0)
                    {
                        Toast.makeText(this, "Confirm Passworp field is empty", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(!Password.equals(ConfirmPassword))
                        {
                            Toast.makeText(this, "Entered Password is not same", Toast.LENGTH_SHORT).show();
                        }
                        else {
//                            Toast.makeText(this, ""+MobileNumber+" "+Name, Toast.LENGTH_SHORT).show();
                            sendVerificationCode(MobileNumber);
                        }
                    }
                }
            }
        }
    }

    private void sendVerificationCode(String mobileNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + mobileNumber, 60, TimeUnit.SECONDS, Signupform.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(Signupform.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Intent intent=new Intent(getApplicationContext(),SubmitOTP.class);
                intent.putExtra("mob",mobileNumber);
                intent.putExtra("usrname",Name);
                intent.putExtra("passowrd",Password);
                intent.putExtra("newusr","newuser");
                intent.putExtra("verificationid",s);
                startActivity(intent);
            }
        });
    }

    private boolean isvalidmobile() {
        Pattern pattern=Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher match=pattern.matcher(MobileNumber);
        return (match.find() && match.group().equals(MobileNumber));
    }
}