package com.androidapp.bahs.glideimageloading;

import android.content.Context;
import android.gesture.GestureLibraries;
import android.widget.ImageView;

import com.androidapp.bahs.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideImageLoader {

    public static GlideImageLoader instance;
    public static GlideImageLoader getInstance(){
        if(instance==null){
            instance=new GlideImageLoader();
        }
        return instance;
    }

    public void DisplayImage(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.warning)
                .dontAnimate()
                .centerCrop()
                .error(R.drawable.ic_default_profile)
                .into(imageView);
    }

    public void DisplayImageWithoutFadeIn(Context mContext, int loading_image,int no_image,String url, ImageView imageView) {
        Glide.with(mContext).load(url).placeholder(loading_image).error(no_image).into(imageView);
    }



    public void DisplayImageWithCropAndResize(Context mContext,String url, ImageView imageView) {
        Glide.with(mContext).load("IMAGE URL HERE")
                .placeholder(R.drawable.ic_default_profile)
                .error(R.drawable.ic_default_profile)
                .override(200, 200)
                .centerCrop()
                .into(imageView);
   
    }
}
