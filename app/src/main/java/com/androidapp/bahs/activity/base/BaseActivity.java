package com.androidapp.bahs.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.androidapp.bahs.R;
import com.crittercism.app.Crittercism;

/**
 * Created by Aarshi on 12-12-2016.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected AppCompatActivity mContext;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mContext = this;
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Crittercism.initialize(getApplicationContext(), getResources().getString(R.string.crittercism));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.base_layout);
        FrameLayout layout = (FrameLayout) findViewById(R.id.container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View innerView = inflater.inflate(layoutResID, null);
        layout.addView(innerView);
    }


    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get an Analytics tracker to report app starts & uncaught exceptions etc.
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Stop the analytics tracking
    }
}
