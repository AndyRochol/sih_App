package com.example.retrieve_img.viewmodel;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.retrieve_img.R;
import com.example.retrieve_img.model.sign_updetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;

public class AppRepository {
    public Application application;
    public FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    public MutableLiveData<FirebaseUser> mutableLiveData;
    MutableLiveData<Boolean> logstatus;
    FirebaseUser user;



    public AppRepository(Application application) {
        this.application = application;

        mutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();

    //    if(auth.getCurrentUser() != null){
      //      logstatus.postValue(true);
        //}
    }



    public void register(String email , String password , String country , String username) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = task.getResult().getUser().getUid();
                if(id != null){
                    //saveprofile(email , );
                    sign_updetails sign_updetails = new sign_updetails(email  ,country ,  username);
                    FirebaseDatabase.getInstance().getReference().child(id).setValue(sign_updetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(application, "registeration is not completed", LENGTH_SHORT).show();
                        } else {
                            mutableLiveData.postValue(auth.getCurrentUser());
                        }
                    }
                });
                }else{
                    Toast.makeText(application , "Try again" , LENGTH_SHORT).show();

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(application , "Not registered" , Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void log(String email, String paasword) {
                //Toast.makeText(this,"i love youu",Toast.LENGTH_SHORT).show();
                auth.signInWithEmailAndPassword(email, paasword).addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mutableLiveData.postValue(auth.getCurrentUser());

                        } else {
                            Toast.makeText(application, "user is not exist", LENGTH_SHORT).show();
                        }
                    }
                });
            }

    public MutableLiveData<FirebaseUser> MutableLiveData() {
        return mutableLiveData;
    }
    public MutableLiveData<Boolean> getLogstatus() {
        return logstatus;
    }



}


