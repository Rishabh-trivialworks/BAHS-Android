package com.androidapp.bahs.glideimageloading;

import android.content.Context;
import android.widget.ImageView;

import com.androidapp.bahs.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideImageLoader {

    private int download_img_id, no_img_id;
    private Context mContext;

    public GlideImageLoader(Context context, int no_image, int loading_image) {

        download_img_id = loading_image;
        no_img_id = no_image;
        mContext = context;
    }

    public void DisplayImage(String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public void DisplayImageWithoutFadeIn(String url, ImageView imageView) {
        Glide.with(mContext).load(url).placeholder(download_img_id).error(no_img_id).into(imageView);
    }



    public void DisplayImageWithCropAndResize(String url, ImageView imageView) {
        Glide.with(mContext).load("IMAGE URL HERE")
                .placeholder(R.drawable.ic_default_profile)
                .error(R.drawable.ic_default_profile)
                .override(200, 200)
                .centerCrop()
                .into(imageView);
   
    }
}
