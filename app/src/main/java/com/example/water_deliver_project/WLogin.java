package com.example.water_deliver_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class WLogin extends AppCompatActivity {

    private EditText etLoginMobile;
    private EditText etLoginPassword;
    private ImageView imgForgetpassword;
    private ImageView btnLoginbutton;
    private ImageView imgSignup;
    LinearLayout linearLayout;
    String MobileNumber;
    String Password;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlogin);


        etLoginMobile = (EditText) findViewById(R.id.et_login_mobile);
        etLoginPassword = (EditText) findViewById(R.id.et_login_password);
        imgForgetpassword = (ImageView) findViewById(R.id.img_forgetpassword);
        btnLoginbutton = (ImageView) findViewById(R.id.btn_loginbutton);
        imgSignup = (ImageView) findViewById(R.id.img_signup);
        linearLayout=(LinearLayout) findViewById(R.id.ll_signup);
        imgForgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),sendotp.class);
                startActivity(intent);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Signupform.class);
                startActivity(intent);
            }
        });
        btnLoginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileNumber=etLoginMobile.getText().toString().trim();
                Password=etLoginPassword.getText().toString().trim();
                if(MobileNumber.equals(""))
                {
                    Toast.makeText(WLogin.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(Password.equals(""))
                    {
                        Toast.makeText(WLogin.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("credential").child(MobileNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(WLogin.this, ""+task.getException(), Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    if(MobileNumber.equals(task.getResult().getKey()) && Password.equals(task.getResult().child("password").getValue()))
                                    {
                                        Intent intent=new Intent(WLogin.this,HomeTwo.class);
                                        startActivity(intent);
                                        Toast.makeText(WLogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(WLogin.this, "Invalid Login Credential", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}