package com.androidapp.bahs.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.androidapp.bahs.R;
import com.androidapp.bahs.utils.Constants;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Mobikasa on 12/27/2016.
 */

/*public class NetworkDialog extends SweetAlertDialog {
    private Context mContext;
    public NetworkDialog(Context context) {
        super(context);
        this.mContext=context;
    }

    public NetworkDialog(Context context, int alertType) {
        super(context, alertType);
        this.mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitleText(Constants.AlertConstant.NETWORK_ERROR)
                .setContentText(Constants.AlertConstant.NETWORK_ERROR_CONTENT)
                .setConfirmText("Retry") ;
    }

}*/

public class NetworkDialog extends Dialog {

    private Context mContext;
    private LayoutInflater inflater;
    public NetworkDialog(Context context) {
        super(context);
        this.mContext=context;
        this.inflater=LayoutInflater.from(mContext);
    }

    public NetworkDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext=context;
        this.inflater=LayoutInflater.from(mContext);
    }

    protected NetworkDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext=context;
        this.inflater=LayoutInflater.from(mContext);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.dialog_network_error,null);
        this.setCanceledOnTouchOutside(false);
        this.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation_2;
        this.setContentView(view);
    }



}
