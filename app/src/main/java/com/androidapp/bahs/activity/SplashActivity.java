package com.androidapp.bahs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.firebase.RegistratinIntentService;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initializeFirebaseToken();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                activityCleanSwitcher(IntroActivity.class);

            }
        }, 2000);
    }
    private void initializeFirebaseToken() {
        Intent intent = new Intent(this, RegistratinIntentService.class);
        startService(intent);
    }
}
