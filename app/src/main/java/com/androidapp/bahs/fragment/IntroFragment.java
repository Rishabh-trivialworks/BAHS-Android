package com.androidapp.bahs.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.activity.CreateAccountActivity;
import com.androidapp.bahs.activity.IntroActivity;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.interfaces.OnVideoCompleteListener;
import com.androidapp.bahs.utils.TextureVideoViewLoadHome;


/**
 * Created by Mobikasa on 9/1/2016.
 */
public class IntroFragment extends Fragment implements OnVideoCompleteListener {
    int infomation_page_position;
    private View mRootView;
    private TextView mTextmessage;
    private RefrenceWrapper refrenceWrapper;
   private BaseActivity mContext;
    private RelativeLayout bannerLayout;
    private TextureVideoViewLoadHome mCustomVideoView;
    public static final IntroFragment newInstance(int position) {
        IntroFragment fragment = new IntroFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntroFragment.this.infomation_page_position = getArguments().getInt("position");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout_id = 0;
        switch (infomation_page_position) {
            case 0:
                layout_id = R.layout.intro_screen_one;
                break;
            case 1:
                layout_id = R.layout.intro_screen_two;
                break;
            case 2:
                layout_id = R.layout.intro_screen_three;
                break;
            case 3:
                layout_id = R.layout.intro_screen_four;
                break;
        }
        mRootView = inflater.inflate(layout_id, container, false);
        refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(getActivity());
        mContext = (BaseActivity)getActivity();
      if(infomation_page_position==3){
          InitView(mRootView,true);
      }else{
          InitView(mRootView,false);
      }


        return mRootView;
    }

    private void InitView(View mRootView,boolean isVideo) {
        if(isVideo){
            bannerLayout = (RelativeLayout)mRootView.findViewById(R.id.bannerLayout);
            addVideo();
        }

    }

    private void addVideo() {
        try {

            bannerLayout.removeAllViews();
            bannerLayout.setVisibility(View.VISIBLE);
            mCustomVideoView = new TextureVideoViewLoadHome(mContext,this);
            mCustomVideoView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            bannerLayout.addView(mCustomVideoView);
        } catch (Exception e) {

        }
    }

    @Override
    public void onVideoComplete() {
        if(infomation_page_position==3&& IntroActivity.currentpos==3){
            mContext.activityCleanSwitcher(CreateAccountActivity.class);
        }
    }
}
