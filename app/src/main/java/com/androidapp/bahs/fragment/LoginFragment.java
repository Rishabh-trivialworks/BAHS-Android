package com.androidapp.bahs.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.activity.CreateAccountActivity;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.service.db.AppSharedPreferences;
import com.androidapp.bahs.service.utils.AlertUtils;
import com.androidapp.bahs.service.utils.CommonUtility;
import com.androidapp.bahs.service.utils.StringUtils;
import com.androidapp.bahs.utils.AppMessages;

public class LoginFragment extends Fragment implements View.OnClickListener {

  private RefrenceWrapper refrenceWrapper;
    private EditText mEmailEditText, mPasswordEditText;
    private Button mBtnSignIn;
    private TextView mTxtforgot, mTxtError, mText_signin;
    private CreateAccountActivity mCreateAccountActivity;
    private ImageView mImgEmail, mImgPassword;
    private LinearLayout mLinearLayoutInsideScrollView;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_login, container, false);
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(getActivity());
        mCreateAccountActivity = (CreateAccountActivity) getActivity();
        initUI(mRootView);

        this.setRetainInstance(true);
        return mRootView;
    }
    private void initUI(View rootView) {
        mText_signin = (TextView) rootView.findViewById(R.id.text_signin);
        mText_signin.setTextColor(getResources().getColor(R.color.quiz_dialog_header));
        mEmailEditText = (EditText) rootView.findViewById(R.id.edt_email);
        mPasswordEditText = (EditText) rootView.findViewById(R.id.edt_password);
        mTxtforgot = (TextView) rootView.findViewById(R.id.tvforgot);
        mTxtError = (TextView) rootView.findViewById(R.id.txt_error);
        mBtnSignIn = (Button) rootView.findViewById(R.id.btn_sign_in);
        mImgEmail = (ImageView) rootView.findViewById(R.id.img_email);
        mImgPassword = (ImageView) rootView.findViewById(R.id.img_password);
        mLinearLayoutInsideScrollView = (LinearLayout) rootView.findViewById(R.id.linearlayoutInsideScrollView);
        mLinearLayoutInsideScrollView.setVisibility(View.VISIBLE);
        mTxtforgot.setPaintFlags(mTxtforgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        setListeners();
    }
    private void setListeners() {
        mBtnSignIn.setOnClickListener(this);
        mTxtforgot.setOnClickListener(this);
        setTextChangeListener(mEmailEditText);
        setTextChangeListener(mPasswordEditText);
        setFocusChangeListener(mEmailEditText);
        setFocusChangeListener(mPasswordEditText);
        mLinearLayoutInsideScrollView.setOnClickListener(this);

    }
    private void setFocusChangeListener(final EditText editText) {
        switch (editText.getId()) {
            case R.id.edt_email:
                addFocusChangeListener(mImgEmail, mEmailEditText, R.drawable.new_post, R.drawable.new_post_filled, 0);
                break;
            case R.id.edt_password:
                addFocusChangeListener(mImgPassword, mPasswordEditText, R.drawable.lock, R.drawable.lock_filled, 6);
                break;
        }
    }
    private void setTextChangeListener(final EditText editText) {
        switch (editText.getId()) {
            case R.id.edt_email:
                addTextChangeListener(mImgEmail, mEmailEditText, R.drawable.new_post, R.drawable.new_post_filled, 0);
                break;
            case R.id.edt_password:
                call_ime_action();
                addTextChangeListener(mImgPassword, mPasswordEditText, R.drawable.lock, R.drawable.lock_filled, 6);
                break;
        }
    }
    private void addTextChangeListener(final ImageView mImageView, final EditText mEditText, final int drawable1, final int drawable3, final int mLength) {

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = mEditText.getText().length();
                int color = 0;
//                if (length == 0) {
//                    color = ContextCompat.getColor(getActivity(), R.color.icon_color);
//                } else
                if ((length < mLength) || (mLength == 0 && (!StringUtils.isEmailValid(mEditText.getText().toString())))) {
                    color = ContextCompat.getColor(getActivity(), R.color.red);
                } else {
                    mImageView.setImageResource(drawable3);
                    mImageView.setColorFilter(0);
                }
                if (color != 0) {
                    mImageView.setImageResource(drawable1);
                    mImageView.setColorFilter(color);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void addFocusChangeListener(final ImageView mImageView, final EditText mEditText, final int drawable1, final int drawable3, final int mLength) {
        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(mEditText.getText().length() == 0) {
                    if (hasFocus) {
                        mImageView.setColorFilter(ContextCompat.getColor(getActivity(), R.color.red));
                    } else {
                        mImageView.setColorFilter(ContextCompat.getColor(getActivity(), R.color.icon_color));
                    }
                }
            }
        });
    }
    private void call_ime_action() {
        mPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    doSignIn();
                }
                return false;
            }
        });
    }
    private void doSignIn() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (StringUtils.isEmpty(email)) {
            mCreateAccountActivity.showTextView(mTxtError, AppMessages.CommonSignInSignUpMessages.NO_EMAIL);
            return;
        }
        if (StringUtils.isEmpty(password)) {
            mCreateAccountActivity.showTextView(mTxtError, AppMessages.CommonSignInSignUpMessages.NO_PASSWORD);
            return;
        }
        if (!StringUtils.isEmailValid(email)) {
            mCreateAccountActivity.showTextView(mTxtError, AppMessages.CommonSignInSignUpMessages.INVALID_EMAIL);
            return;
        }
        if (!StringUtils.isPasswordLengthValid(password, 6)) {
            mCreateAccountActivity.showTextView(mTxtError, AppMessages.CommonSignInSignUpMessages.MIN_PASSWORD_CHECK);
            return;
        }

        if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(getActivity()) == true) {
            // service call logic goes here
        } else {
            AlertUtils.showToast(getActivity(),"No Internet Access");
            return;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                CommonUtility.hideClickEvent(getActivity(), mLinearLayoutInsideScrollView);
                doSignIn();
                break;
            case R.id.tvforgot:
                //forgot paasword screen open from here
                break;

            case R.id.linearlayoutInsideScrollView:
                CommonUtility.hideClickEvent(getActivity(), mLinearLayoutInsideScrollView);
                break;
        }
    }
}
