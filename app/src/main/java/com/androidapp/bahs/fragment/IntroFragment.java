package com.androidapp.bahs.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.RefrenceWrapper;


/**
 * Created by Mobikasa on 9/1/2016.
 */
public class IntroFragment extends Fragment {
    int infomation_page_position;
    private View mRootView;
    private TextView mTextmessage;
    private RefrenceWrapper refrenceWrapper;
    private Context mContext;

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
        mContext = getActivity();
        InitView(mRootView);


        return mRootView;
    }

    private void InitView(View mRootView) {
      //  mTextmessage = (TextView) mRootView.findViewById(R.id.txt_information);
        //refrenceWrapper.getFontTypeFace().setRobotoThinTypeFace(mContext, mTextmessage);
    }

}
