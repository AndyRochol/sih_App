package com.example.retrieve_img.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.retrieve_img.viewmodel.AppRepository;
import com.google.firebase.auth.FirebaseUser;

public  class loginandsignmodel extends AndroidViewModel {
    public static AppRepository appRepository;
  //  private final AppRepository appRepository;
    public MutableLiveData<FirebaseUser> mutableLiveData;
    MutableLiveData<Boolean> loggedstatus;

    public loginandsignmodel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);
        mutableLiveData = appRepository.MutableLiveData();
        loggedstatus = appRepository.logstatus;
    }

    public static void  login(String email, String password){
        appRepository.log(email, password);

    }

    public static void register(String email , String password , String country , String username ){
        appRepository.register(email,password , country , username);
    }


}
