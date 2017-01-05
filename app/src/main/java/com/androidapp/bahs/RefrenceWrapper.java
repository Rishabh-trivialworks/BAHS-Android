package com.androidapp.bahs;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidapp.bahs.service.RestAPI;
import com.androidapp.bahs.service.utils.ServiceConstants;
import com.androidapp.bahs.utils.device.DeviceUtils;
import com.androidapp.bahs.utils.StringUtils;
import com.androidapp.bahs.utils.date.DateTimeUtils;
import com.androidapp.bahs.utils.FragmentCallingUtils;
import com.androidapp.bahs.utils.ImageUploadingDialog;
import com.androidapp.bahs.utils.PictureUtils;
import com.androidapp.bahs.utils.ServiceCallsUtils;
import com.androidapp.bahs.utils.TableUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RefrenceWrapper {

    public static RefrenceWrapper refrenceWrapper;
    public Typeface typeface_normal, typeface_semibold, typeface_medium;
    private Context context;
    private StringUtils mStringUtilHandler;
    private DeviceUtils mDeviceUtilHandler;
    private PictureUtils mPictureUtilHandler;
    private TableUtils mTableUtilHandler;
    private DateTimeUtils mDateTimeUtils;
    private ImageUploadingDialog mImageUploadingDialog;
    private FragmentCallingUtils mFragmentCallingUtilsHandler;
    private ServiceCallsUtils mServiceCallHandler;
    private RestAPI service;

    public RefrenceWrapper(Context activity) {
        this.context = activity;
    }

    public static RefrenceWrapper getRefrenceWrapper(Context context) {
        if (refrenceWrapper == null) {
            refrenceWrapper = new RefrenceWrapper(context);
        }
        return refrenceWrapper;
    }

    public void destroyInstance() {
        if (refrenceWrapper != null) {
            refrenceWrapper = null;
        }
    }

    public RestAPI getService() {
        return (service == null) ? setService() : service;
    }

    private RestAPI setService() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(ServiceConstants.WebConstants.BASE_URL_CONSTANT).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(new OkHttpClient.Builder().build()).build();
        service = retrofit.create(RestAPI.class);
        return service;

    }

    public FragmentCallingUtils getmFragmentCallingUtilsHandler() {
        if (mFragmentCallingUtilsHandler == null) {
            mFragmentCallingUtilsHandler = new FragmentCallingUtils();
        }
        return mFragmentCallingUtilsHandler;
    }

    public ServiceCallsUtils getmServiceCallHandler() {
        if (mServiceCallHandler == null) {
            mServiceCallHandler = new ServiceCallsUtils();
        }
        return mServiceCallHandler;
    }


    public Typeface getTypeface() {
        if (typeface_normal == null) {
            typeface_normal = Typeface.createFromAsset(context.getAssets(), "fonts/Geo-Rg.otf");
        }
        return typeface_normal;
    }

    public void setTypeFace(int normalTypepaceTags[], int boldTypeFaceTags[], int mediumTypeFaces[], ViewGroup v) {
        for (int i = 0; i < normalTypepaceTags.length; i++) {
            View childView = v.findViewWithTag("" + normalTypepaceTags[i]);
            setTypeface(getTypeface(), childView);
        }
        for (int i = 0; i < boldTypeFaceTags.length; i++) {
            View childView = v.findViewWithTag("" + boldTypeFaceTags[i]);
            setTypeface(getTypefaceBold(), childView);
        }

        for (int i = 0; i < mediumTypeFaces.length; i++) {
            View childView = v.findViewWithTag("" + mediumTypeFaces[i]);
            setTypeface(getTypefaceMedium(), childView);
        }
    }

    private void setTypeface(Typeface typeFace, View childView) {
        if (childView instanceof TextView) {
            ((TextView) childView).setTypeface(typeFace);
        } else if (childView instanceof EditText) {
            ((EditText) childView).setTypeface(typeFace);
        } else if (childView instanceof Button) {
            ((Button) childView).setTypeface(typeFace);
        }
    }

    public Typeface getTypefaceBold() {
        if (typeface_semibold == null) {
            typeface_semibold = Typeface.createFromAsset(context.getAssets(), "fonts/Geo-Sb.otf");
        }
        return typeface_semibold;
    }

    public Typeface getTypefaceMedium() {
        if (typeface_medium == null) {
            typeface_medium = Typeface.createFromAsset(context.getAssets(), "fonts/Geo-Md.otf");
        }
        return typeface_medium;
    }

    public StringUtils getmStringUtilHandler() {
        if (mStringUtilHandler == null) {
            mStringUtilHandler = new StringUtils();
        }
        return mStringUtilHandler;
    }


    public DeviceUtils getmDeviceUtilHandler() {
        if (mDeviceUtilHandler == null) {
            mDeviceUtilHandler = new DeviceUtils();
        }
        return mDeviceUtilHandler;
    }


    public PictureUtils getmPictureUtilHandler() {
        if (mPictureUtilHandler == null) {
            mPictureUtilHandler = new PictureUtils(context);
        }
        return mPictureUtilHandler;
    }

    public TableUtils getTableUtilHandler() {
        if (mTableUtilHandler == null) {
            mTableUtilHandler = new TableUtils();
        }
        return mTableUtilHandler;
    }

    public DateTimeUtils getDateTimeUtilHandler(){
        if(mDateTimeUtils==null){
            mDateTimeUtils=new DateTimeUtils();
        }
        return mDateTimeUtils;
    }


    public ImageUploadingDialog getImageUploadingHandler(){
        if(mImageUploadingDialog==null){
            mImageUploadingDialog=new ImageUploadingDialog();
        }
        return mImageUploadingDialog;
    }


    public String getApplicationPackage(Context ctx) {
        String version = "";
        try {
            PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            version = pInfo.packageName;
        } catch (Exception e) {
            version = "performix.com.performix";
        }
        return version;
    }


}