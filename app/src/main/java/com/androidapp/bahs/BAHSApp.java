package com.androidapp.bahs;

import android.app.Application;

import com.androidapp.bahs.service.AppContext;
import com.androidapp.bahs.service.db.DatabaseHelper;
import com.facebook.FacebookSdk;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by Aarshi on 12-12-2016.
 */

@ReportsCrashes(formKey = "", // will not be used
        mailTo = "aarshi@mobikasa.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.app_crash)

public class BAHSApp extends Application {
    private DatabaseHelper mDatabaseHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().setContext(this);
        ACRA.init(this);
        mDatabaseHelper = DatabaseHelper.getInstance(BAHSApp.this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.exit(0);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
