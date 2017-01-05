package com.androidapp.bahs.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.utils.device.AlertUtils;
import com.androidapp.bahs.utils.StringUtils;
import com.androidapp.bahs.utils.constantvariable.AppMessages;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEmailEditText;
    private Button mBtnSubmit;
    private TextView mTxtError;
    private RefrenceWrapper refrenceWrapper;
    private ImageView mImgEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        onCreateBase();
    }

    public void onCreateBase() {
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);
        initUI();
        //refrenceWrapper.getFontTypeFace().setRobotoThinTypeFace(mContext, mEmailEditText, mTxtError, mBtnSubmit, mText_forgot_mess);

    }

    private void initUI() {
        mEmailEditText = (EditText) findViewById(R.id.edt_email);
        mTxtError = (TextView) findViewById(R.id.txt_error);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mImgEmail = (ImageView) findViewById(R.id.img_email);
        setListeners();
    }

    private void setListeners() {
        mBtnSubmit.setOnClickListener(this);
        setTextChangeListener(mEmailEditText);
    }

    private void setTextChangeListener(final EditText editText) {
        switch (editText.getId()) {
            case R.id.edt_email:
                call_ime_action();
                addTextChangeListener(mImgEmail, mEmailEditText, R.drawable.new_post, R.drawable.new_post_filled, 0);
                break;
        }
    }

    private void call_ime_action() {
        mEmailEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    forgotPasswordRequest();
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];
            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return super.dispatchTouchEvent(event);
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
                if (length == 0) {
                    color = getResources().getColor(R.color.icon_color);
                } else if ((length < mLength) || (mLength == 0 && (!StringUtils.isEmailValid(mEditText.getText().toString())))) {
                    color = getResources().getColor(R.color.red);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(ForgotPasswordActivity.this) == true) {
                    forgotPasswordRequest();
                }else{
                    AlertUtils.getInstance().showToast(ForgotPasswordActivity.this, AppMessages.NO_INTERNET_AVAILABLE);
                }
                break;

        }
    }

    private void forgotPasswordRequest() {
        String email = mEmailEditText.getText().toString();
        if (StringUtils.isEmpty(email)) {
            showTextView(mTxtError, AppMessages.CommonSignInSignUpMessages.NO_EMAIL);
            return;
        }
        if (!StringUtils.isEmailValid(email)) {
            showTextView(mTxtError, AppMessages.CommonSignInSignUpMessages.INVALID_EMAIL);
            return;
        }
           }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
