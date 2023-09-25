package com.example.water_deliver_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactForm extends AppCompatActivity {

    private EditText etName;
    private EditText etAddress;
    private EditText etCity;
    private EditText etState;
    private EditText etCountry;
    private EditText etZipcode;
    private EditText etEmail;
    private ImageView imgSubmitform;
    String Name;
    String Address;
    String City;
    String State;
    String Country;
    String Zipcode;
    String Email;
    String Mobi="";
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);
        etName = (EditText) findViewById(R.id.et_name);
        etAddress = (EditText) findViewById(R.id.et_address);
        etCity = (EditText) findViewById(R.id.et_city);
        etState = (EditText) findViewById(R.id.et_State);
        etCountry = (EditText) findViewById(R.id.et_country);
        etZipcode = (EditText) findViewById(R.id.et_zipcode);
        etEmail = (EditText) findViewById(R.id.et_email);
        imgSubmitform = (ImageView) findViewById(R.id.img_submitform);
        Mobi=getIntent().getStringExtra("mobileNumber");
        imgSubmitform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name=etName.getText().toString().trim();
                Address=etAddress.getText().toString().trim();
                City=etCity.getText().toString().trim();
                State=etState.getText().toString().trim();
                Country=etCountry.getText().toString().trim();
                Zipcode=etZipcode.getText().toString().trim();
                Email=etEmail.getText().toString().trim();
                if(Name.length()==0||Name.equals("")||Name==null)
                {
                    Toast.makeText(ContactForm.this, "Name Can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(Name.length()<2) {
                    Toast.makeText(ContactForm.this, "Name is too short", Toast.LENGTH_SHORT).show();
                }
                UserDetailsHolder obj=new UserDetailsHolder(Name,Address,City,State,Country,Zipcode,Email);
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("userDetails").child(Mobi).setValue(obj);
                Intent intent=new Intent(getApplicationContext(),HomeTwo.class);
                startActivity(intent);
            }
        });
    }
}