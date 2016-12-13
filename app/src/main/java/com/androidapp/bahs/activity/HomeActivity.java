package com.androidapp.bahs.activity;
import android.os.Bundle;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.google.firebase.iid.FirebaseInstanceId;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       initializeFirebaseToken();
    }

    private void initializeFirebaseToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.print("FCM Registratio: " + token);
    }
}
