package com.androidapp.bahs.interfaces;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by Mobikasa on 12/22/2016.
 */

public interface  ImageUploadingListener {
    public void onSuccess(Bitmap bitmap);
    public void onFailure();

}
