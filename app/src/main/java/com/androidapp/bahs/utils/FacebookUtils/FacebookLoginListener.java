package com.androidapp.bahs.utils.FacebookUtils;

import com.facebook.GraphResponse;

/**
 * Created by Mobikasa on 12/29/2016.
 */

public interface FacebookLoginListener {
    public void onFacebookLoginSuccess(GraphResponse response);
    public void onFacebookLoginFailure(Exception e);
}
