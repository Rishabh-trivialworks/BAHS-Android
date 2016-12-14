package com.androidapp.bahs.activity;
import android.content.Intent;
import android.os.Bundle;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.firebase.RegistratinIntentService;
import com.google.firebase.iid.FirebaseInstanceId;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       initializeFirebaseToken();
    }

    private void initializeFirebaseToken() {
        Intent intent = new Intent(this, RegistratinIntentService.class);
        startService(intent);
    }
}
