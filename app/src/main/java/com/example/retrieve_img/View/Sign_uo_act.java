package com.example.retrieve_img.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrieve_img.R;
import com.example.retrieve_img.viewmodel.loginandsignmodel;
import com.google.firebase.auth.FirebaseUser;

public class Sign_uo_act extends AppCompatActivity {
    Button  sign;
    EditText em , pass , nam , natio , em5;
    TextView app , forg , dont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_uo_act);



        loginandsignmodel loginandsignmode = ViewModelProviders.of(this).get(loginandsignmodel.class);
        loginandsignmode.mutableLiveData.observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null)
                    Toast.makeText(getApplicationContext() , "user created", Toast.LENGTH_SHORT).show();
            }
        });


        //em5= findViewById(R.id.editText5);
        sign = findViewById(R.id.sign_up_btn);
        em = findViewById(R.id.sign_ema);
        pass = findViewById(R.id.sign_pass);
        app = findViewById(R.id.textView);
        dont = findViewById(R.id.editText5);
        natio = findViewById(R.id.nationality);
        nam = findViewById(R.id.fuul_name);

       // String ema = em.getText().toString();
        //String pas = pass.getText().toString();
        //String naam = nam.getText().toString();
      //  String nation = natio.getText().toString();


        dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sign_uo_act.this , MainActivity.class);
                startActivity(i);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ema = em.getText().toString();
                String pas = pass.getText().toString();
                String naam = nam.getText().toString();
                String nation = natio.getText().toString();
                if(ema.length() != 0 && pas.length() != 0 ){
                //Toast.makeText(getApplicationContext(), "i love u" , Toast.LENGTH_SHORT).show();
                loginandsignmodel.register(ema,pas , nation , naam);
                //loginandsignmodel.saveproile(ema , naam , nation);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Fll up the required details" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}