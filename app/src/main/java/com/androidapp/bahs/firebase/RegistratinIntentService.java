package com.androidapp.bahs.firebase;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Purnendu on 12/14/2016.
 */
public class RegistratinIntentService extends IntentService {

    private static final String mTAG = "RegIntentService";


    public RegistratinIntentService() {
        super(mTAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i(mTAG, "FCM Registration Token: " + token);
    }
}