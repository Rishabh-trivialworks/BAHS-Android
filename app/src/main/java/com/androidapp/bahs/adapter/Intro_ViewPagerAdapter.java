package com.androidapp.bahs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.androidapp.bahs.fragment.IntroFragment;


public class Intro_ViewPagerAdapter extends FragmentPagerAdapter {

    private int INFORMATION_PAGES_COUNT = 4;

    public Intro_ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return IntroFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return INFORMATION_PAGES_COUNT;
    }


}
