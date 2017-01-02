package com.androidapp.bahs;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Mobikasa on 12/29/2016.
 */
public class ContextManager {
    private static ContextManager ourInstance = new ContextManager();

    public static ContextManager getInstance() {
        return ourInstance;
    }

    private ContextManager() {
    }

    private Activity mContext;

    public Activity getmContext() {
        return mContext;
    }

    public void setmContext(Activity mContext) {
        this.mContext = mContext;
    }
}
