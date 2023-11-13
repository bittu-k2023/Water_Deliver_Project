package com.example.water_deliver_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SubmitOTP extends AppCompatActivity {

    private AppCompatEditText etSubmitOTP;
    private ImageView imgSubmitmyotp;
    private ImageView imgBackbutton;
    private TextView tvShownumber;
    private TextView tvotptimer;
    private ImageView resendotp;
    private FirebaseAuth mAuth;
    String num;
    String usr_otp;
    String vid;
    String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_otp);

        mAuth=FirebaseAuth.getInstance();
        etSubmitOTP = (AppCompatEditText) findViewById(R.id.et_submitOTP);
        imgSubmitmyotp = (ImageView) findViewById(R.id.img_submitmyotp);
        imgBackbutton = (ImageView) findViewById(R.id.img_backbutton);
        tvShownumber = (TextView) findViewById(R.id.tv_shownumber);
        resendotp = (ImageView) findViewById(R.id.resendotp);
        resendotp.setVisibility(View.INVISIBLE);
        tvotptimer = (TextView) findViewById(R.id.tvotptimer);
        Intent intent=getIntent();
        num=intent.getStringExtra("mob");
        tvShownumber.setText("+91 "+num);
        Intent intent1=getIntent();
        if(intent1.getStringExtra("newusr")!=null)
        {
            User=intent1.getStringExtra("newusr");
        }
        else {
            User="olduser";
        }
        imgback();
        timecountval();
        submitmyotp();
    }
    private void submitmyotp() {

        imgSubmitmyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr_otp=etSubmitOTP.getText().toString().trim();
                vid=getIntent().getStringExtra("verificationid");

                if(usr_otp.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "user otp : "+usr_otp, Toast.LENGTH_SHORT).show();
                }
                else {

                    if(usr_otp.length()<6)
                    {
                        Toast.makeText(SubmitOTP.this, "Otp length is too short", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(vid, usr_otp);
                        signInWithCredential(credential);
                    }
                }
            }
        });
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    if(User.equals("newuser"))
                    {
                        Intent intent1=new Intent(SubmitOTP.this, HomeTwo.class);
                        startActivity(intent1);
                    }
                    else {
                        Intent intent=new Intent(getApplicationContext(),CreatePassword.class);
                        intent.putExtra("mobnum",num);
                        startActivity(intent);
                    }

                }else {
                    Toast.makeText(SubmitOTP.this, "resend_otp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void timecountval() {

        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tvotptimer.setText("Resend code in : "+millisUntilFinished/1000+"s");
            }
            @Override
            public void onFinish() {
                resendnewotp();
            }
        }.start();
    }

    private void resendnewotp() {
        tvotptimer.setVisibility(View.INVISIBLE);
        resendotp.setVisibility(View.VISIBLE);

        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recallbtnotp();
            }
        });

    }
    private void recallbtnotp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + num, 60, TimeUnit.SECONDS, SubmitOTP.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(SubmitOTP.this, "Re-Try :Error", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                vid=s;
            }
        });
        submitmyotp();
        tvotptimer.setVisibility(View.VISIBLE);
        timecountval();
        resendotp.setVisibility(View.INVISIBLE);
    }

    private void imgback() {
        imgBackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),WLogin.class);
                startActivity(intent);
            }
        });
    }
}