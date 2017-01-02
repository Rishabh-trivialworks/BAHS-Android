package com.androidapp.bahs.utils.FacebookUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.androidapp.bahs.ContextManager;
import com.androidapp.bahs.activity.HomeActivity;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.utils.Syso;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Mobikasa on 12/29/2016.
 */

public  class FacebookManager<T>  {

    /*
    * ***************** Read Instructions*****************
    * If You are using FacebookManager then you have to follow some rules
    *  1. Initilize facebookSdk before using it ( FacebookSdk.sdkInitialize(getApplicationContext());)
    *  2. Pass the Callbackmanager in the constructor of FacebookManager
    *  3. Handle the CallbackManager in onActivityResult
    *  4. Implement FacebookLoginListener in the class
    * */


    private Context mContext;
    private CallbackManager mCallbackManager;
    public static FacebookManager instance;
    /*public static FacebookManager getInstance(Context context){
        if(instance==null){
            instance=new FacebookManager(context);
        }
        return instance;
    }
*/
    public FacebookManager(Context context,CallbackManager callbackManager){
        this.mContext=context;
        this.mCallbackManager=callbackManager;
    }

    public void doFacebookLogin(){

        LoginManager.getInstance().logInWithReadPermissions((HomeActivity)mContext, Arrays.asList("user_photos","email","public_profile"));
       // LoginManager.getInstance().logInWithPublishPermissions((HomeActivity)mContext, Arrays.asList("publish_actions"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Syso.debug("Login_success_as_facebook_------->");
                getUserInfo(loginResult);
            }

            @Override
            public void onCancel() {
                Syso.debug("Login_facebook_cancel------->");
                try{
                    Syso.debug("Login_facebook_cancel------->"+mContext.getClass().getClasses());
                }catch (Exception e){
                    e.printStackTrace();
                }
              //(mContext.getClass().getClasses()).onFacebookLoginFailure(new Exception());
            }

            @Override
            public void onError(FacebookException error) {
                Syso.debug("Login_as_facebook_error------->");
                error.printStackTrace();
               // ((HomeActivity)mContext).onFacebookLoginFailure(error);
            }
        });
    }

     private void getUserInfo(LoginResult loginResult){
         GraphRequest graphRequest=GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
             @Override
             public void onCompleted(JSONObject object, GraphResponse response) {
                 try {
                     Syso.debug("Login_success_as_facebook_------->"+response);
                     //((HomeActivity)mContext).onFacebookLoginSuccess(response);
                 }catch (Exception e){
                     e.printStackTrace();
                    // ((HomeActivity)mContext).onFacebookLoginFailure(e);
                 }
             }

         });
         Bundle parameters = new Bundle();
         parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
         graphRequest.setParameters(parameters);
         graphRequest.executeAsync();
     }
}
