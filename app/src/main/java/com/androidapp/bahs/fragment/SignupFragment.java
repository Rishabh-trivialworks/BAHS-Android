package com.androidapp.bahs.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.activity.CreateAccountActivity;
import com.androidapp.bahs.interfaces.ImageUploadingListener;
import com.androidapp.bahs.service.ds.response.RegisterDetail;
import com.androidapp.bahs.utils.device.AlertUtils;
import com.androidapp.bahs.utils.StringUtils;
import com.androidapp.bahs.utils.constantvariable.AppMessages;
import com.androidapp.bahs.utils.CommonUtility;
import com.androidapp.bahs.utils.ServiceCallsUtils;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignupFragment extends Fragment implements View.OnClickListener,ImageUploadingListener {
    private TextView mTxtError, mTxtCheckBox, mTextsignup,mLogintxt;
    private EditText mFirstNameEditText, mLastNameEditText, mEmailEditText, mPasswordEditText;
    private Button mBtnRegister;
    private ImageView mImgFirstName, mImgLastName, mImgEmail, mImgPassword, mImg_Check_Terms;
    private CreateAccountActivity mCreateAccountActivity;
    private RefrenceWrapper refrenceWrapper;
    private LinearLayout mLinearLayoutInsideScrollView;
    private View mRootView;
    private RelativeLayout mUploadImageRL;
    private boolean mTermsAndCondition=true;
    public static SignupFragment signupFragment;
    private int SELECT_IMAGE=1000;

    @BindView(R.id.img_profile)
    ImageView profileImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_signup, container, false);
        mCreateAccountActivity = (CreateAccountActivity) getActivity();
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(getActivity());
        ButterKnife.bind(this,mRootView);
        initUI(mRootView);
        setLoginTxtColor();
        acceptTermsAndCondition(mTermsAndCondition);
        signupFragment=this;
        //refrenceWrapper.getFontTypeFace().setRobotoThinTypeFace(getActivity(), mFirstNameEditText, mLastNameEditText, mEmailEditText, mPasswordEditText, mBtnRegister, mTxtError, mTxtCheckBox, mTextsignup,mLogintxt);
        return mRootView;
    }

    private void initUI(View rootView) {
        mTextsignup = (TextView) rootView.findViewById(R.id.text_signup);
        mTextsignup.setTextColor(getResources().getColor(R.color.quiz_dialog_header));
        mTxtError = (TextView) rootView.findViewById(R.id.txt_error);
        mTxtCheckBox = (TextView) rootView.findViewById(R.id.txt_checkbox);
        mFirstNameEditText = (EditText) rootView.findViewById(R.id.edt_firstname);
        mLastNameEditText = (EditText) rootView.findViewById(R.id.edt_lastname);
        mEmailEditText = (EditText) rootView.findViewById(R.id.edt_email);
        mPasswordEditText = (EditText) rootView.findViewById(R.id.edt_password);
        mImg_Check_Terms = (ImageView) rootView.findViewById(R.id.cb_signup);
        mImgFirstName = (ImageView) rootView.findViewById(R.id.img_firstname);
        mImgLastName = (ImageView) rootView.findViewById(R.id.img_lastname);
        mImgEmail = (ImageView) rootView.findViewById(R.id.img_email);
        mImgPassword = (ImageView) rootView.findViewById(R.id.img_password);
        mBtnRegister = (Button) rootView.findViewById(R.id.btn_register);
        mLogintxt = (TextView) rootView.findViewById(R.id.login_txt);
        mUploadImageRL=(RelativeLayout)rootView.findViewById(R.id.uploadImageRL);
//        mTxt_or_join = (TextView) rootView.findViewById(R.id.txt_join);
        //mBtnFacebook = (Button) rootView.findViewById(R.id.btn_fb_signup);
        mLinearLayoutInsideScrollView = (LinearLayout) rootView.findViewById(R.id.linearlayoutInsideScrollView);
        setListeners();
    }
    private void setLoginTxtColor() {
        SpannableString myString = new SpannableString(getResources().getString(R.string.new_login_text));
        myString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.app_green)), 25, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mLogintxt.setText(myString);
    }
    private void setTermsAndConditions() {
        SpannableString myString = new SpannableString(getResources().getString(R.string.signup_t_c));

        myString.setSpan(new StyleSpan(Typeface.BOLD), 35, 64, 0);

        myString.setSpan(new StyleSpan(Typeface.BOLD), 69, 92, 0);
        mTxtCheckBox.setText(myString);
        mTxtCheckBox.setMovementMethod(LinkMovementMethod.getInstance());
        mTxtCheckBox.setHighlightColor(Color.TRANSPARENT);
    }
    private void setListeners() {
        mBtnRegister.setOnClickListener(this);
        mUploadImageRL.setOnClickListener(this);
        setTextChangeListener(mFirstNameEditText);
        setTextChangeListener(mLastNameEditText);
        setTextChangeListener(mEmailEditText);
        setTextChangeListener(mPasswordEditText);
        setFocusChangeListener(mFirstNameEditText);
        setFocusChangeListener(mLastNameEditText);
        setFocusChangeListener(mEmailEditText);
        setFocusChangeListener(mPasswordEditText);

        // mBtnFacebook.setOnClickListener(this);
        mImg_Check_Terms.setOnClickListener(this);
        mLinearLayoutInsideScrollView.setOnClickListener(this);
        mImg_Check_Terms.setImageResource(R.drawable.checkbox_s);
        mLogintxt.setOnClickListener(this);
    }
    private void setTextChangeListener(final EditText editText) {
        switch (editText.getId()) {
            case R.id.edt_firstname:
                addTextChangeListener(mImgFirstName, mFirstNameEditText, R.drawable.name, R.drawable.name_filled, 3);
                break;
            case R.id.edt_lastname:
                addTextChangeListener(mImgLastName, mLastNameEditText, R.drawable.name, R.drawable.name_filled, 3);
                break;
            case R.id.edt_email:
                addTextChangeListener(mImgEmail, mEmailEditText, R.drawable.new_post, R.drawable.new_post_filled, 0);
                break;
            case R.id.edt_password:
                call_ime_action();
                addTextChangeListener(mImgPassword, mPasswordEditText, R.drawable.lock, R.drawable.lock_filled, 6);
                break;

        }
    }
    private void setFocusChangeListener(final EditText editText) {
        switch (editText.getId()) {
            case R.id.edt_firstname:
                addFocusChangeListener(mImgFirstName, mFirstNameEditText, R.drawable.name, R.drawable.name_filled, 3);
                break;
            case R.id.edt_lastname:
                addFocusChangeListener(mImgLastName, mLastNameEditText, R.drawable.name, R.drawable.name_filled, 3);
                break;
            case R.id.edt_email:
                addFocusChangeListener(mImgEmail, mEmailEditText, R.drawable.new_post, R.drawable.new_post_filled, 0);
                break;
            case R.id.edt_password:
                call_ime_action();
                addFocusChangeListener(mImgPassword, mPasswordEditText, R.drawable.lock, R.drawable.lock_filled, 6);
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
                    doSignUp();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                CommonUtility.getInstance().hideClickEvent(getActivity(), mLinearLayoutInsideScrollView);
                doSignUp();
                break;
            case R.id.linearlayoutInsideScrollView:
                CommonUtility.getInstance().hideClickEvent(getActivity(), mLinearLayoutInsideScrollView);
                break;
            case R.id.login_txt:
                ((CreateAccountActivity) getActivity()).gotoLoginFragment();
                break;
            case R.id.cb_signup:
                if(mTermsAndCondition){
                    mTermsAndCondition=false;
                    acceptTermsAndCondition(mTermsAndCondition);
                }
                else{
                    mTermsAndCondition=true;
                    acceptTermsAndCondition(mTermsAndCondition);
                }
                break;
            case R.id.uploadImageRL:
                openImageUpLoadingDialog();
                break;
        }
    }
    private void doSignUp() {

        ServiceCallsUtils serviceCallsUtils=new ServiceCallsUtils();
        serviceCallsUtils.doRegistration(getActivity(),"","","","","","","");
        //serviceCallsUtils.doTesting(getActivity());
  //      getDataFromArraryList();
        String firstName = mFirstNameEditText.getText().toString();
        String lastName = mLastNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        if (StringUtils.isEmpty(firstName)) {
            mCreateAccountActivity.showTextView(mTxtError, AppMessages.SignUpMessages.NO_FIRST_NAME);
            return;
        }
        if (StringUtils.isEmpty(lastName)) {
            mCreateAccountActivity.showTextView(mTxtError, AppMessages.SignUpMessages.NO_LAST_NAME);
            return;
        }
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
        if (!mTermsAndCondition) {
            mCreateAccountActivity.showTextView(mTxtError, AppMessages.SignUpMessages.N0_CHECK_BOX_TERMS_CONDITION);
            return;
        }

        if (refrenceWrapper.getmDeviceUtilHandler().isInternetOn(getActivity()) == true) {
               // service call logic goes here

        } else {
            AlertUtils.getInstance().showToast(getActivity(),AppMessages.NO_INTERNET_AVAILABLE);
            return;
        }


    }
    private void acceptTermsAndCondition(boolean update) {
        if (update) {
            mImg_Check_Terms.setImageResource(R.drawable.checkbox_s);
            }else{
            mImg_Check_Terms.setImageResource(R.drawable.checkbox);
        }
        }


    private void getDataFromArraryList(){

        ArrayList<String> names=new ArrayList<>();
        names.add("Eve");
        names.add("Anna");
        names.add("Tonny");
        names.add("Steve");



        ArrayList<RegisterDetail> regdetail=new ArrayList<>();

        for(int i=0;i<5;i++){
            RegisterDetail registerModel=new RegisterDetail("test"+i,"lTest","testdsds2@yupmIL.COM","qwerty1","Android","32ndsfsd34y234nglgjdf746","653454hsdffdy234nglgjdf746");
            regdetail.add(registerModel);
        }

        for(String name:names){
            System.out.println(name);
        }

        for(RegisterDetail name:regdetail){
            System.out.println(name.getFirst_name());
        }

        System.out.println("Using Iterator");
        Iterator iterator = regdetail.iterator();
        while (iterator.hasNext()) {
            RegisterDetail registerDetail=(RegisterDetail) iterator.next();
            System.out.println(registerDetail.getFirst_name());
        }
    }

    private void openImageUpLoadingDialog(){
        RefrenceWrapper.getRefrenceWrapper(getActivity()).getImageUploadingHandler().show(getFragmentManager(),"tag");
    }



    @Override
    public void onSuccess(Bitmap bitmap) {
        profileImage.setImageBitmap(bitmap);
    }

    @Override
    public void onFailure() {

    }
}
