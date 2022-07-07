package com.example.retrieve_img.viewmodel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrieve_img.View.Explore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class placeListviewmodel extends AndroidViewModel {
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener mAuthListener;
    String nationality;
    MutableLiveData<String> liveData;

    public placeListviewmodel(@NonNull Application application) {
        super(application);

        // FirebaseAuth firebaseAuth;
        liveData = new MutableLiveData<>();

        firebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            //assert user != null;
            String id = user.getUid();
            try {
                nationality = database.getReference().child(id).child("nation").toString().toLowerCase();
                liveData.postValue(nationality);

            } catch (Exception e) {
                nationality = "india";
                liveData.postValue(nationality);
            }

        };

        // Intent intent = new Intent(getApplication(), Explore.class);
        //intent.putExtra("data", nationality);

    }


    public  MutableLiveData<String> getLiveData(){
        return liveData;
    }



}
