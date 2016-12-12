package com.androidapp.bahs.service;

import com.androidapp.bahs.service.ds.response.ListModel;
import com.androidapp.bahs.service.ds.response.LoginModel;
import com.androidapp.bahs.service.ds.response.LoginRequest;
import com.androidapp.bahs.service.ds.response.LoginResponse;
import com.androidapp.bahs.service.ds.response.RegisterModel;
import com.androidapp.bahs.service.utils.Constants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Project : Mobikasa Retrofit Lib
 * Author : Balwinder Singh Madaan
 * Creation Date : 26-feb-2016
 * Description :"for managing url calls like GET,POST.",
 * Creating RestAPI Interface to Send HTTP Request using Retrofit and We have to create an interface to handle our requests. So create a new RestAPI interface that will handle all HTTP Request.
 */
public interface RestAPI {
    //login api
    @FormUrlEncoded
    @POST(Constants.ServiceConstants.LOGIN)
    Call<LoginModel> logIn(@Field("password") String password, @Field("email") String Email, @Field("device_token") String device_token, @Field("device_id") String device_id, @Field("device_type") String device_type);

    @GET(Constants.WebConstants.LISTING)
    void getcategories(CustomCallBacks<ListModel> customCallBacks);

    @Multipart
    @POST(Constants.ServiceConstants.REGISTER)
    Call<RegisterModel> register(@Part("first_name") String first_name, @Part("last_name") String last_name, @Part("appusername") String appusername, @Part("email") String email, @Part("phone") String phone, @Part("password") String password, @Part("device_token") String device_token, @Part("device_id") String device_id, @Part("device_type") String device_type, @Part("country_code") String country_code, @Part("referral_code") String referral_code, @Part("profile_image") RequestBody description, @Part MultipartBody.Part file

    );

    //Example getJson
    @POST(Constants.ServiceConstants.REGISTER)
    Callback<LoginResponse> log_in(@Body LoginRequest login_request, CustomCallBacks<LoginResponse> customCallBacks);
}

