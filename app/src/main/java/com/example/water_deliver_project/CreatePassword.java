package com.example.water_deliver_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePassword extends AppCompatActivity {
    private EditText etCreatepassword;
    private EditText etConfirmpassword;
    private ImageView imgSubmitpassword;

    String createpassword="";
    String confirmpassword="";
    String mobnum="";
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        etCreatepassword = (EditText) findViewById(R.id.et_createpassword);
        etConfirmpassword = (EditText) findViewById(R.id.et_confirmpassword);
        imgSubmitpassword = (ImageView) findViewById(R.id.img_submitpassword);
        mobnum=getIntent().getStringExtra("mobnum");
        imgSubmitpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savepassword();
            }
        });
    }


    private void savepassword() {
        createpassword=etCreatepassword.getText().toString().trim();
        confirmpassword=etConfirmpassword.getText().toString().trim();
        if(createpassword.equals(""))
        {
            Toast.makeText(this, "Create Password Field IS Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            if(createpassword.length()!=6)
            {
                Toast.makeText(this, "Passowrd Must Be Six Digit Numeric Value", Toast.LENGTH_SHORT).show();
            }
            else{
                if(!createpassword.equals(confirmpassword))
                {
                    Toast.makeText(this, "Passowrd not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("credential").child(mobnum).child("password").setValue(createpassword);
                    Intent intent=new Intent(getApplicationContext(),HomeTwo.class);
                    intent.putExtra("mobileNumber",mobnum);
                    startActivity(intent);
                }
            }
        }
    }
}