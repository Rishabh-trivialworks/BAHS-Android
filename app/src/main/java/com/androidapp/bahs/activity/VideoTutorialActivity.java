package com.androidapp.bahs.activity;


import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.interfaces.OnVideoCompleteListener;
import com.androidapp.bahs.utils.TextureVideoViewLoadHome;

import java.io.IOException;

/**
 * Created by Mobikasa Night on 12/16/2016.
 */
public class VideoTutorialActivity extends BaseActivity implements OnVideoCompleteListener {


    private RelativeLayout bannerLayout;
    private TextureVideoViewLoadHome mCustomVideoView;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_vedio_tutorial);
        initView();
    }

    private void initView() {
        bannerLayout = (RelativeLayout)findViewById(R.id.bannerLayout);
        addVideo();
    }


    private void addVideo() {
        try {

            bannerLayout.removeAllViews();
            bannerLayout.setVisibility(View.VISIBLE);
            mCustomVideoView = new TextureVideoViewLoadHome(this,this);
            mCustomVideoView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            bannerLayout.addView(mCustomVideoView);
        } catch (Exception e) {

        }
    }


    @Override
    public void onVideoComplete() {
        activityCleanSwitcher(CreateAccountActivity.class);
    }
}
