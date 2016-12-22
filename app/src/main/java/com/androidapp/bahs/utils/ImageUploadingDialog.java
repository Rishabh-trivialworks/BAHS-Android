package com.androidapp.bahs.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.CreateAccountActivity;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.fragment.SignupFragment;
import com.androidapp.bahs.interfaces.ImageUploadingListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mobikasa on 12/22/2016.
 */

public class ImageUploadingDialog extends DialogFragment {
    Dialog dialog;
    private int SELECT_IMAGE=1000;

    AppCompatActivity mContext;
    @BindView(R.id.galleryRL) RelativeLayout galleryRL;
    @OnClick(R.id.galleryRL)
    public void openGallery(){
        openImageGallery();
        this.dismiss();

    }

    ImageUploadingListener dialogCallback;

    public ImageUploadingDialog setCallBack(ImageUploadingListener dialogCallback){
        this.dialogCallback = dialogCallback;
        return this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.image_uploading_dialog, container);
        ButterKnife.bind(this,view);
        return view;
    }

    public RelativeLayout getGalleryButton(){
        return galleryRL;
    }


    public void openImageGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        getActivity().startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMAGE){
            if (resultCode==Activity.RESULT_OK){
                if(data!=null){
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    }catch (Exception e){

                    }
                }
            }
        }
    }*/
}
