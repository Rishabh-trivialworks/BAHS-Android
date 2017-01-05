package com.androidapp.bahs.utils.device;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.androidapp.bahs.service.AppContext;
import com.androidapp.bahs.service.db.AppSharedPreferences;

import java.util.Random;


public class DeviceUtils {

    public boolean isAirplaneModeOn(Context context) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            return Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public boolean isInternetOn(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isScreenRotationOn(Context ctx) {
        if (Settings.System.getInt(ctx.getContentResolver(),
                Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
            return true;

        } else {
            return false;
        }
    }

    public void hideSoftKeyboard(Activity context) {

        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = context.getCurrentFocus();
        if (inputManager != null && view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            view.clearFocus();
        }
    }

    public void showSoftKeyboard(Activity context) {

        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = context.getCurrentFocus();
        if (inputManager != null && view != null) {
            inputManager.showSoftInputFromInputMethod(view.getWindowToken(), InputMethodManager.SHOW_FORCED);
        }
    }

    public void vibrateDevice(Context context) {

        vibrateDevice(context, 250);
    }

    public void vibrateDevice(Context context, long duration) {

        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(duration);
    }

    public int getAPIVersion() {
        return Build.VERSION.SDK_INT;
    }

    public String getAndroidID(Context ctx) {

        String android_id = Secure.getString(ctx.getContentResolver(), Secure.ANDROID_ID);
        return android_id;
    }

    public String getDeviceType() {
        String device_type = "Android";
        return device_type;
    }

    public String getDeviceToken() {
        return AppSharedPreferences.getInstance().getGcmId();
    }

    /**
     * This will return the screen width .
     */
    public int getScreenWidth(Activity context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        return mDisplayMetrics.widthPixels;
    }

    public int getScreenHeight(Activity context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        return mDisplayMetrics.heightPixels;
    }

    public int getFullImageWidthHeight(Activity context) {
        int width = getScreenWidth(context);
        int height = getScreenHeight(context);
        if (width < height) {
        } else {
            width = height;
        }
        return width;
    }

    /**
     * Get the screen's density scale. Convert the dps to pixels, based on density scale
     */
    public int pxTodpConvert(Context activity, int pixels) {
        float scale = activity.getResources().getDisplayMetrics().density;
        float dips = pixels * scale + 0.5f;
        return (int) dips;
    }

    public int dpTopxConvert(Context activity, int dips) {
        float scale = activity.getResources().getDisplayMetrics().density;
        float pixels = dips / scale;
        return (int) pixels;
    }

    public String getIMEINO() {

        Context context = AppContext.getInstance().getContext();
        String phoneId = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            phoneId = telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (phoneId == null || phoneId.equals("")) {
            phoneId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        }

        if (phoneId == null || phoneId.equals("")) {
            phoneId = AppSharedPreferences.getInstance().getCustomIMEINO(context);

            if (phoneId.equals("NotFound"))
                phoneId = getRandomCustomIMEINO(context);
        }
        LogUtils.error("phone id is ", phoneId + "==");
        return phoneId;

    }

    public String getRandomCustomIMEINO(Context context) {
        String totalIMEINO = "";
        Random random = new Random();

        for (int i = 0; i < 14; i++) {
            totalIMEINO += random.nextInt(10);
            LogUtils.error("totalIMEINO", totalIMEINO + "===");
        }
        AppSharedPreferences.getInstance().setCustomIMEINO(totalIMEINO, context);
        return totalIMEINO;
    }
}
