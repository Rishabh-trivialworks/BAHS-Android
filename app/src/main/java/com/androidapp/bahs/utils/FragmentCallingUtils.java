package com.androidapp.bahs.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.androidapp.bahs.RefrenceWrapper;
import com.androidapp.bahs.service.db.AppSharedPreferences;
import com.androidapp.bahs.service.utils.Constants;

/**
 * Created by cpu505 on 17/9/15.
 */
public class FragmentCallingUtils {

    private Fragment lastFragment;

    public int getCurrentActiveTab() {
        return AppSharedPreferences.getInstance().getCurrentActiveBottomTab();
    }

    public void commitFragment(Fragment frag, FragmentActivity mActivity, boolean applyAnimation) {
        FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (applyAnimation) {
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        ft.remove(frag);
        ft.commit();
        fm.popBackStack();
    }

    public void replaceAndCommitFragmentByAddingToBackStack(int fragment_id, Fragment frag, FragmentActivity mActivity, boolean applyAnimation, String tag, boolean placetag) {
        FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (placetag) {
            ft.replace(fragment_id, frag, tag);
        } else {
            ft.replace(fragment_id, frag);
        }
        ft.addToBackStack(null);
        if (applyAnimation) {
            ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        ft.commit();
    }

    public Fragment getFragment(FragmentActivity fragmentActivity, int id) {
        FragmentManager fm = fragmentActivity.getSupportFragmentManager();
        return fm.findFragmentById(id);
    }
}
