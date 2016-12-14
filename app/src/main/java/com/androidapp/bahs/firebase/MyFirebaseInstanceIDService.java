package com.androidapp.bahs.firebase;

import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {

        Intent intent = new Intent(this, RegistratinIntentService.class);
        startService(intent);
    }

}