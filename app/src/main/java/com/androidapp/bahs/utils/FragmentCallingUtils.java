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

    public void replaceFragment(int fragment_id, Fragment frag, FragmentActivity mActivity, boolean applyAnimation, String tag, boolean placetag, boolean hitApi) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IntentExtras.HIT_API, hitApi);
        replaceAndcommitFragment(bundle, fragment_id, frag, mActivity, applyAnimation, tag, placetag);
    }

    public void replaceFragment(int fragment_id, Fragment frag, FragmentActivity mActivity, boolean applyAnimation, String tag, boolean placetag, boolean hitApi, String croppedImagePath) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IntentExtras.HIT_API, hitApi);
        bundle.putString(Constants.IntentExtras.CROPPED_IMAGE_PATH, croppedImagePath);
        replaceAndcommitFragment(bundle, fragment_id, frag, mActivity, applyAnimation, tag, placetag);
    }

    private void replaceAndcommitFragment(Bundle bundle, int fragment_id, Fragment frag, FragmentActivity mActivity, boolean applyAnimation, String tag, boolean placetag) {
        FragmentManager fm = mActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        popAddedFragments(fm, fragment_id, mActivity);
        if (applyAnimation) {
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        frag.setArguments(bundle);
        if (placetag) {
            ft.replace(fragment_id, frag, tag);
        } else {
            ft.replace(fragment_id, frag);
        }
        ft.commit();
    }

    private void addAndcommitFragment(Bundle bundle, int fragment_id, Fragment frag, FragmentActivity mActivity, boolean applyAnimation, String tag, boolean placetag) {
        FragmentManager fm = mActivity.getSupportFragmentManager();
        lastFragment = fm.findFragmentById(fragment_id);
        FragmentTransaction ft = fm.beginTransaction();
        if (applyAnimation) {
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        frag.setArguments(bundle);
        if (placetag) {
            ft.add(fragment_id, frag, tag);
        } else {
            ft.add(fragment_id, frag);
        }
        ft.addToBackStack(null);
        ft.commit();
    }

    public void replaceFragment(int fragment_id, Fragment frag, FragmentActivity mActivity, boolean applyAnimation, String tag, boolean placetag, String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        frag.setArguments(bundle);
        if (key.equals(Constants.IntentExtras.GALLERY_IMAGE_ID)) {
            addAndcommitFragment(bundle, fragment_id, frag, mActivity, applyAnimation, tag, placetag);
        } else {
            bundle.putBoolean(Constants.IntentExtras.HIT_API, true);
            replaceAndcommitFragment(bundle, fragment_id, frag, mActivity, applyAnimation, tag, placetag);
        }
    }


    public void replaceFragments_OnBackPress(FragmentActivity fragmentActivity, int id) {
        RefrenceWrapper refrenceWrapper = RefrenceWrapper.getRefrenceWrapper(fragmentActivity);
        Fragment fragment = getFragment(fragmentActivity, id);

    }

    private void popAddedFragments(FragmentManager fm, int fragment_id, FragmentActivity activity) {
        Fragment previousFrag = fm.findFragmentById(fragment_id);
    }


    public Fragment getFragment(FragmentActivity fragmentActivity, int id) {
        FragmentManager fm = fragmentActivity.getSupportFragmentManager();
        return fm.findFragmentById(id);
    }
}
