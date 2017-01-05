package com.androidapp.bahs.utils;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.service.CustomCallBacks;
import com.androidapp.bahs.service.bean.UserModel;
import com.androidapp.bahs.service.db.AppSharedPreferences;
import com.androidapp.bahs.service.db.DatabaseHelper;
import com.androidapp.bahs.service.ds.response.LoginModel;
import com.androidapp.bahs.service.ds.response.RegisterModel;
import com.androidapp.bahs.utils.device.AlertUtils;
import com.androidapp.bahs.utils.device.Syso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cpu505 on 17/9/15.
 */
public class ServiceCallsUtils {
    private RefrenceWrapper refrenceWrapper;
    public void callRegisterService(final FragmentActivity mFragmentActivity, RequestBody description, MultipartBody.Part file, final RelativeLayout view) {
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mFragmentActivity);
        Call<RegisterModel> call = refrenceWrapper.getService().register("test", "Singh", "RamanMadaan", "ramanmadaan_staging@yopmail.com", "9871234567", "333333", "112222222-QkvkRoR_rGA-r", "f9389c43bb5c38f2", "android", "+01", "1222", description, file);
        call.enqueue(new CustomCallBacks<RegisterModel>(mFragmentActivity, true) {
            @Override
            public void onSucess(Call<RegisterModel> call, Response<RegisterModel> response) {
                AlertUtils.getInstance().showSnackBar(mFragmentActivity, "User Name=" + response.toString(), view);
            }

            @Override
            public void onFailure(Throwable arg0) {
                AlertUtils.getInstance().showSnackBar(mFragmentActivity, "User Name=" + arg0.getMessage(), view);
            }
        });

    }

    private void resetAppData(FragmentActivity mFragmentActivity) {
        /*DatabaseHelper.getInstance(mFragmentActivity.getApplication()).resetAllTables(DatabaseHelper.getDatabase());
        AppSharedPreferences.getInstance().clearAllData();*/
    }

    private void showDeactivatedAccountPopup(FragmentActivity mFragmentActivity, LoginModel response) {
        AlertUtils.getInstance().showToast(mFragmentActivity, "User Name=" + response.getUser().getFirstName().toString());
    }

    public void callSignIn(final FragmentActivity mFragmentActivity, String email, String password, final View view) {
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mFragmentActivity);
       Call<LoginModel> call=  refrenceWrapper.getService().logIn(password, email, "691b2cb2-21ce-428a-99f3-050dc4eb9efd", "f9389c43bb5c38f2", "android");

        call.enqueue(new CustomCallBacks<LoginModel>(mFragmentActivity,true) {
            @Override
            public void onSucess(Call<LoginModel> call, Response<LoginModel> response) {
                AlertUtils.getInstance().showSnackBar(mFragmentActivity, "User Name=" + response.body().getUser().toString(), view);
            }

            @Override
            public void onFailure(Throwable arg0) {
                AlertUtils.getInstance().showSnackBar(mFragmentActivity, arg0.getMessage(), view);
            }
        });


    }

    public  String toJson(Object object) {

        Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> classType) {

        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, classType);
    }

    public void doTesting(final FragmentActivity mFragmentActivity){
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mFragmentActivity);
        Call<Object> call=refrenceWrapper.getService().postTesting("339");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    public void doRegistration(final FragmentActivity mFragmentActivity,String firstName,String lastName, String email,String password,String deviceType,String deviceID,String deviceToken){
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mFragmentActivity);
        UserModel userModel=new UserModel("test","lTest","testdsds2@yupmIL.COM","qwerty1","Android","32ndsfsd34y234nglgjdf746","653454hsdffdy234nglgjdf746","","","","");
        Call<UserModel> call=refrenceWrapper.getService().signUpNew(userModel); //signUp("test","lTest","testdsds2@yupmIL.COM","qwerty1","Android","32ndsfsd34y234nglgjdf746","653454hsdffdy234nglgjdf746");
        call.enqueue(new CustomCallBacks<UserModel>(mFragmentActivity,true) {
            @Override
            public void onSucess(Call<UserModel> call, Response<UserModel> response) {
                // AlertUtils.showSnackBar(mFragmentActivity, "User Name=" + response.body().getUser().toString());
                Syso.debug("Response----","response---success-"+response.body()+"---"+response.headers());

            }
            @Override
            public void onFailure(Throwable arg0) {
                //AlertUtils.showSnackBar(mFragmentActivity, arg0.getMessage(), view);
                Syso.debug("Response----","response---failure-"+arg0);
            }
        });

    }



}
