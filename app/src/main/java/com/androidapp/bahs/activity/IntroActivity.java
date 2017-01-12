package com.androidapp.bahs.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.adapter.Intro_ViewPagerAdapter;
import com.androidapp.bahs.utils.device.Syso;


public class IntroActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private View mIndicator0, indicator1, mIndicator2, mIndicator3;
    private ImageView mCross;
    private Button mSignUp;
    private RefrenceWrapper refrenceWrapper;
    private Context mContext;
    public static int currentpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(mContext);
        mContext = getApplicationContext();
        inItView();
    }

    public void inItView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mIndicator0 = (View) findViewById(R.id.indicator1);
        indicator1 = (View) findViewById(R.id.indicator2);
        mIndicator2 = (View) findViewById(R.id.indicator3);
        mIndicator3 = (View) findViewById(R.id.indicator4);
        mSignUp = (Button) findViewById(R.id.activity_informatory_sign_up);
        mCross = (ImageView) findViewById(R.id.activity_informatory_txt_skip);
        mViewPager.setAdapter(new Intro_ViewPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new InformatoryPageChangeListener());
        updateIndicators(0);
        //refrenceWrapper.getFontTypeFace().setRobotoThinTypeFace(mContext, mSignUp);
        mSignUp.setOnClickListener(this);
        mCross.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_informatory_sign_up:
                activityCleanSwitcher(CreateAccountActivity.class);
                break;
            case R.id.activity_informatory_txt_skip:
                activityCleanSwitcher(CreateAccountActivity.class);
                break;
        }

    }

    private class InformatoryPageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            updateIndicators(position);
            currentpos = position;
        }

    }

    public void updateIndicators(int position) {
        switch (position) {
            case 0:
                setDotBackground(R.drawable.dot_white, R.drawable.dot_transparent, R.drawable.dot_transparent, R.drawable.dot_transparent);
                break;
            case 1:
                setDotBackground(R.drawable.dot_transparent, R.drawable.dot_white, R.drawable.dot_transparent, R.drawable.dot_transparent);
                break;
            case 2:
                setDotBackground(R.drawable.dot_transparent, R.drawable.dot_transparent, R.drawable.dot_white, R.drawable.dot_transparent);

                break;
            case 3:
                setDotBackground(R.drawable.dot_transparent, R.drawable.dot_transparent, R.drawable.dot_transparent, R.drawable.dot_white);
                break;
        }
    }

    public void setDotBackground(int id1, int id2, int id3, int id4) {
        mIndicator0.setBackgroundResource(id1);
        indicator1.setBackgroundResource(id2);
        mIndicator2.setBackgroundResource(id3);
        mIndicator3.setBackgroundResource(id4);
    }


    public abstract class x{
        public  void abc(){
            Syso.debug("Method--","method--abc-->x");
        }
    }

    public class y extends x{
        @Override
        public void abc(){
            Syso.debug("Method--","method--abc-->= y");
        }
    }
}
