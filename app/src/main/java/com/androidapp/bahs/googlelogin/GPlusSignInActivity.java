package com.androidapp.bahs.googlelogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.service.bean.User;
import com.androidapp.bahs.service.utils.AlertUtils;
import com.androidapp.bahs.utils.CommonUtility;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


/**
 * Created by Mobikasa Night on 28/12/2016.
 */
public class GPlusSignInActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener{
    public static final String KEY_USER = "KEY_USER";
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        CommonUtility.noTitleBar(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Customizing G+ button

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        gPlusLogin();
    }

   /*From this method intent to google services for getting detail for user*/
    private void gPlusLogin() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resutlCode, Intent data) {
        super.onActivityResult(requestCode, resutlCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            doGPlusSignIn(result);
        }
    }

    /*Here we parse the data we get from google signin and set to the USER model class*/
    private void doGPlusSignIn(GoogleSignInResult data) {

        if (data.isSuccess()) {
            GoogleSignInAccount acct = data.getSignInAccount();
            String personName = acct.getDisplayName();
            User user = new User();
            user.setEmail(acct.getEmail());
            user.setComplete_name(personName);
            user.setId(acct.getId());
            if(acct.getPhotoUrl()!=null)
            user.setImage_path(acct.getPhotoUrl().toString());
            user.setFirst_name(acct.getGivenName());
            user.setLast_name(acct.getFamilyName());

                Intent intent = new Intent();
                intent.putExtra(KEY_USER, user);
                setResult(RESULT_OK, intent);
                finish();
        }else{
            AlertUtils.showToast(mContext, R.string.alert_welcome_error_google_data);
            finish();
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
