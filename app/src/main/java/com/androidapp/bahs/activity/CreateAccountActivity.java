package com.androidapp.bahs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.facebook.CommonInterface;
import com.androidapp.bahs.facebook.ModalFbUserProfile;
import com.androidapp.bahs.facebook.SocialMediaIntegration;
import com.androidapp.bahs.facebook.SocialMediaInterface;
import com.androidapp.bahs.firebase.RegistratinIntentService;
import com.androidapp.bahs.fragment.LoginFragment;
import com.androidapp.bahs.fragment.SignupFragment;
import com.androidapp.bahs.service.db.AppSharedPreferences;
import com.androidapp.bahs.service.utils.AlertUtils;
import com.androidapp.bahs.service.utils.CommonUtility;
import com.androidapp.bahs.utils.AppMessages;

import java.util.Timer;
import java.util.TimerTask;

public class CreateAccountActivity extends SocialMediaIntegration implements View.OnClickListener,CommonInterface,SocialMediaInterface{
    private Button mFbLogin, mBtnSignup;
    private RefrenceWrapper refrenceWrapper;
    private Fragment mFragment;
    private LinearLayout LoginSignupLinearLayout;
    private TextView mLogintxt;
    private FrameLayout mFrameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        onCreateBase();
    }
    public void onCreateBase() {
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);
        initUI();
        setLoginTxtColor();
        //refrenceWrapper.getFontTypeFace().setRobotoThinTypeFace(mContext, mBtnSignup, mGLogin, mFbLogin, mLogintxt, mTextContinue, mTextor);


    }
    private void setLoginTxtColor() {
        SpannableString myString = new SpannableString(getResources().getString(R.string.new_login_text));
        myString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.app_green)), 25, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mLogintxt.setText(myString);
    }
    private void initUI() {

        LoginSignupLinearLayout = (LinearLayout) findViewById(R.id.linearlayout_login_signup);
        mFrameLayout = (FrameLayout) findViewById(R.id.container);
        mFbLogin = (Button) findViewById(R.id.btn_fb_signup);
        mBtnSignup = (Button) findViewById(R.id.login_btnSignUp);

        mLogintxt = (TextView) findViewById(R.id.login_txt);
        setListeners();
    }
    private void setListeners() {
        mLogintxt.setOnClickListener(this);
        mBtnSignup.setOnClickListener(this);
        mFbLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_txt:
                if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(CreateAccountActivity.this) == true) {
                    CommonUtility.hideSoftKeyboard(CreateAccountActivity.this);
                    mFragment = getSupportFragmentManager().findFragmentById(R.id.container);
                    if (mFragment instanceof LoginFragment) {
                        return;
                    }
                    LoginSignupLinearLayout.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment())
                            .addToBackStack(null).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                }else{
                    AlertUtils.showToast(CreateAccountActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
                }
                break;
            case R.id.login_btnSignUp:
                if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(CreateAccountActivity.this) == true) {
                    CommonUtility.hideSoftKeyboard(CreateAccountActivity.this);
                    mFragment = getSupportFragmentManager().findFragmentById(R.id.container);
                    LoginSignupLinearLayout.setVisibility(View.GONE);
                    if (mFragment instanceof SignupFragment) {
                        return;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SignupFragment())
                            .addToBackStack(null).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                }else{
                    AlertUtils.showToast(CreateAccountActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
                }

                break;

            case R.id.btn_fb_signup:
                if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(CreateAccountActivity.this) == true) {
                    facebooklogin();
                }else{
                    AlertUtils.showToast(CreateAccountActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
                }
                break;


        }
    }

    public void GotoLoginFragment() {
        CommonUtility.hideSoftKeyboard(CreateAccountActivity.this);
        SignupFragment signupFragment = new SignupFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.remove(signupFragment);
        trans.commit();
        getSupportFragmentManager().popBackStack();
        LoginSignupLinearLayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment())
                .addToBackStack(null).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
    @Override
    public void onBackPressed() {
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.container);
        if (frag != null && frag instanceof SignupFragment) {
            SignupFragment signupFragment = new SignupFragment();
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.remove(signupFragment);
            trans.commit();
            getSupportFragmentManager().popBackStack();
            LoginSignupLinearLayout.setVisibility(View.VISIBLE);
        } else if (frag != null && frag instanceof LoginFragment) {
            LoginFragment signupFragment = new LoginFragment();
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.remove(signupFragment);
            trans.commit();
            getSupportFragmentManager().popBackStack();
            LoginSignupLinearLayout.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }


    }

    private void facebooklogin() {
        if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(this) == true) {
            String tokenValue = AppSharedPreferences.getInstance().getAccessToken();
            if (tokenValue != null && tokenValue.length() > 0) {

            } else {
                Intent intent = new Intent(this, RegistratinIntentService.class);
                startService(intent);
                return;
            }
            ((CreateAccountActivity) this).login_facebook_getProfileInformation((CommonInterface) this);

        } else {
            AlertUtils.showToast(CreateAccountActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
        }
    }

    @Override
    public void incorrect_Credentials(String message) {

    }

    @Override
    public void failourWifiOff() {

    }

    @Override
    public void facbook_info(ModalFbUserProfile modelfbuser, String fb_access_token) {
        AlertUtils.showToast(CreateAccountActivity.this, modelfbuser.getName());
    }
}
