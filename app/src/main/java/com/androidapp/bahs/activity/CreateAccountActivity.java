package com.androidapp.bahs.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
import com.androidapp.bahs.utils.CommonUtility;
import com.androidapp.bahs.utils.FacebookUtils.facebook.CommonInterface;
import com.androidapp.bahs.utils.FacebookUtils.facebook.ModalFbUserProfile;
import com.androidapp.bahs.utils.FacebookUtils.facebook.SocialMediaIntegration;
import com.androidapp.bahs.utils.FacebookUtils.facebook.SocialMediaInterface;
import com.androidapp.bahs.firebase.RegistratinIntentService;
import com.androidapp.bahs.fragment.LoginFragment;
import com.androidapp.bahs.fragment.SignupFragment;
import com.androidapp.bahs.service.db.AppSharedPreferences;
import com.androidapp.bahs.utils.device.AlertUtils;
import com.androidapp.bahs.utils.constantvariable.AppMessages;

import butterknife.ButterKnife;

public class CreateAccountActivity extends SocialMediaIntegration implements View.OnClickListener, CommonInterface, SocialMediaInterface {
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
        ButterKnife.bind(this);
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
                onButtonClick(true);
                break;
            case R.id.login_btnSignUp:
                onButtonClick(false);
                break;
            case R.id.btn_fb_signup:
                if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(CreateAccountActivity.this) == true) {
                    facebooklogin();
                } else {
                    AlertUtils.getInstance().showToast(CreateAccountActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
                }
                break;


        }
    }

    private void onButtonClick(boolean loginPagePush) {
        if (true){//(refrenceWrapper.getmDeviceUtilHandler().isInternetOn(CreateAccountActivity.this) == true) {
            CommonUtility.getInstance().hideSoftKeyboard(CreateAccountActivity.this);
            mFragment = refrenceWrapper.getmFragmentCallingUtilsHandler().getFragment(this, R.id.container);
            if (loginPagePush) {
                if (mFragment instanceof LoginFragment) {
                    return;
                }
                LoginSignupLinearLayout.setVisibility(View.GONE);
                refrenceWrapper.getmFragmentCallingUtilsHandler().replaceAndCommitFragmentByAddingToBackStack(R.id.container, new LoginFragment(), this, true, "", false);
            } else {
                LoginSignupLinearLayout.setVisibility(View.GONE);
                if (mFragment instanceof SignupFragment) {
                    return;
                }
                refrenceWrapper.getmFragmentCallingUtilsHandler().replaceAndCommitFragmentByAddingToBackStack(R.id.container, new SignupFragment(), this, true, "", false);
            }
        } else {
            AlertUtils.getInstance().showToast(CreateAccountActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
        }
    }

    public void gotoLoginFragment() {
        CommonUtility.getInstance().hideSoftKeyboard(CreateAccountActivity.this);
        refrenceWrapper.getmFragmentCallingUtilsHandler().commitFragment(new SignupFragment(), this, true);
        LoginSignupLinearLayout.setVisibility(View.GONE);
        refrenceWrapper.getmFragmentCallingUtilsHandler().replaceAndCommitFragmentByAddingToBackStack(R.id.container, new LoginFragment(), this, true, "", false);
    }

    @Override
    public void onBackPressed() {
        Fragment frag = refrenceWrapper.getmFragmentCallingUtilsHandler().getFragment(this, R.id.container);
        if (frag != null && frag instanceof SignupFragment) {
            refrenceWrapper.getmFragmentCallingUtilsHandler().commitFragment(new SignupFragment(), this, true);
            LoginSignupLinearLayout.setVisibility(View.VISIBLE);
        } else if (frag != null && frag instanceof LoginFragment) {
            refrenceWrapper.getmFragmentCallingUtilsHandler().commitFragment(new LoginFragment(), this, true);
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
            AlertUtils.getInstance().showToast(CreateAccountActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
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
        AlertUtils.getInstance().showToast(CreateAccountActivity.this, modelfbuser.getName());
    }
    private int SELECT_IMAGE=1000;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMAGE){
            if (resultCode== Activity.RESULT_OK){
                if(data!=null){
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        SignupFragment.signupFragment.onSuccess(bitmap);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
