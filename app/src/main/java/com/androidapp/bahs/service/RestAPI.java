package com.androidapp.bahs.service;

import com.androidapp.bahs.service.bean.UserModel;
import com.androidapp.bahs.service.ds.response.ListModel;
import com.androidapp.bahs.service.ds.response.LoginModel;
import com.androidapp.bahs.service.ds.response.LoginRequest;
import com.androidapp.bahs.service.ds.response.LoginResponse;
import com.androidapp.bahs.service.ds.response.RegisterDetail;
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
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Project : Mobikasa Retrofit Lib
 * Description :"for managing url calls like GET,POST.",
 * Creating RestAPI Interface to Send HTTP Request using Retrofit and We have to create an interface to handle our requests. So create a new RestAPI interface that will handle all HTTP Request.
 */
public interface RestAPI {

    /*
    * Guideline
    * 1. If Api takes json :-
    * @POST(URL)
      Call<MODEL> signUpNew(@Body MODEL model );
    *
    * 2. If Api takes key pair :-
    * @FormUrlEncoded
      @POST(URL)
      Call<MODEL> logIn(@Field("key") String key1, @Field("key") String key2, @Field("key") String key3);
    *
    * 3. @Multipart
        @POST(URL)
        Call<MODEL> register(@Part("key") String key1, @Part("key") String key2);

    * 4. Get Request
    *   @GET("URL")
        Call<MODEL> getUser(@Path("key") String key);
    *
    * 5. if we need to add Header in api
    * @Headers({
        "Accept: application/vnd.github.v3.full+json",
        "User-Agent: Retrofit-Sample-App"
         })
    *
    * */



    /*
    * This is registration Api
    * */
    @POST(Constants.ServiceConstants.TEST_REGISTER)
    Call<UserModel> signUpNew(@Body UserModel userModel);

    /*
    * This is Login Api
    * */
    @FormUrlEncoded
    @POST(Constants.ServiceConstants.LOGIN)
    Call<LoginModel> logIn(@Field("password") String password, @Field("email") String Email, @Field("device_token") String device_token, @Field("device_id") String device_id, @Field("device_type") String device_type);


    @Multipart
    @POST(Constants.ServiceConstants.REGISTER)
    Call<RegisterModel> register(@Part("first_name") String first_name, @Part("last_name") String last_name, @Part("appusername") String appusername, @Part("email") String email, @Part("phone") String phone, @Part("password") String password, @Part("device_token") String device_token, @Part("device_id") String device_id, @Part("device_type") String device_type, @Part("country_code") String country_code, @Part("referral_code") String referral_code, @Part("profile_image") RequestBody description, @Part MultipartBody.Part file
    );

    //Example getJson
    @POST(Constants.ServiceConstants.REGISTER)
    Callback<LoginResponse> log_in(@Body LoginRequest login_request, CustomCallBacks<LoginResponse> customCallBacks);

    @FormUrlEncoded
    @POST(Constants.ServiceConstants.TEST_REGISTER)
    Call<Object> signUp(@Field("first_name") String first_name,
                        @Field("last_name") String last_name,
                        @Field("email") String email,
                        @Field("password") String password,
                        @Field("device_type") String device_type,
                        @Field("device_id") String device_id,
                        @Field("device_token") String device_token);

    @Headers({
            "Accept-Encoding:gzip, deflate",
            "Accept-Language:en;q=1",
            "AccessToken:Q2zvy2BKVXdmnaT9tqetDqpi6S4B2iFJ",
            "AppVersion:1.0(20)",
            "Content-Length:22",

    })
    @FormUrlEncoded
    @POST(Constants.ServiceConstants.TEST_REGISTER)
    Call<Object> postTesting(@Field("with_user_id") String password);

}

