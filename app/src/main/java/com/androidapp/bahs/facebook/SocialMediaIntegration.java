package com.androidapp.bahs.facebook;

import android.content.Intent;
import android.os.Bundle;

import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.service.utils.AlertUtils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Balwinder Singh Madaan on 23/12/15.
 */
//
public abstract class SocialMediaIntegration extends BaseActivity {

    private CallbackManager callbackManager;
    private AccessToken access, accessToken;
    private SocialMediaInterface social_interface;
    private RefrenceWrapper refrenceWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookInitialize();
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);
        social_interface = (SocialMediaInterface) SocialMediaIntegration.this;

    }

    protected void facebookInitialize() {
        FacebookSdk.sdkInitialize(SocialMediaIntegration.this);
        callbackManager = CallbackManager.Factory.create();

    }

    public void login_facebook_getProfileInformation(final CommonInterface commonInterface) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_friends"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                access = loginResult.getAccessToken();
                //  AppSharedPreferences.getInstance().setFacebookAccessToken(loginResult.getAccessToken().getToken());
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        ModalFbUserProfile user = new Gson().fromJson(object.toString(), ModalFbUserProfile.class);
                        social_interface.facbook_info(user, access.toString());
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,link,picture");
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
                AlertUtils.showToast(mContext, "" + exception);
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}