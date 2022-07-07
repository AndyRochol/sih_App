package com.example.retrieve_img.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrieve_img.R;
import com.example.retrieve_img.databinding.ActivityMainBinding;
import com.example.retrieve_img.viewmodel.loginandsignmodel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EditText e1 , e2;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        loginandsignmodel loginandsignmode = ViewModelProviders.of(this).get(loginandsignmodel.class);
        loginandsignmode.mutableLiveData.observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){

                    FirebaseDatabase.getInstance().getReference().child(firebaseUser.getUid()).child("nation").addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                             str = snapshot.getValue().toString();
                            //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                           /* try {
                                if(str == null || !snapshot.hasChild(str)){
                                    str = "india";
                                }

                            }catch (Exception e){
                                Toast.makeText(getApplicationContext() , "please wait" + e + " ",Toast.LENGTH_SHORT).show();
                            }
                            finally {
                                Intent intent = new Intent(MainActivity.this, Explore.class);
                                intent.putExtra("country", str);
                                startActivity(intent);
                            }*/
                            Optional<String> optionalS = Optional.ofNullable(str);
                            String  val = optionalS.orElse("India");
                            Intent intent = new Intent(MainActivity.this, Explore.class);
                            intent.putExtra("country", val);
                            startActivity(intent);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    binding.userId.setText("");
                    binding.password.setText("");
                }
            }
        });


        e1 = findViewById(R.id.user_id);
        e2= findViewById(R.id.password);
       // b1 = findViewById(R.id.button2);



        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.length() != 0 && e2.length() != 0){
                loginandsignmodel.login(e1.getText().toString() , e2.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext() , "not applicable", Toast.LENGTH_SHORT).show();
                }

            }
        });



        binding.editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this , Sign_uo_act.class);
                startActivity(in);


            }
        });






        
    }
}