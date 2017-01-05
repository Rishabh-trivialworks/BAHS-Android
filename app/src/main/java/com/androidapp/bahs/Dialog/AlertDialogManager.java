package com.androidapp.bahs.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.bahs.R;

import butterknife.ButterKnife;

/**
 * Created by Mobikasa on 12/27/2016.
 */

/*public class AlertDialogManager extends SweetAlertDialog {
    private Context mContext;
    public AlertDialogManager(Context context) {
        super(context);
        this.mContext=context;
    }

    public AlertDialogManager(Context context, int alertType) {
        super(context, alertType);
        this.mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitleText(ServiceConstants.AlertConstant.NETWORK_ERROR)
                .setContentText(ServiceConstants.AlertConstant.NETWORK_ERROR_CONTENT)
                .setConfirmText("Retry") ;
    }

}*/

public class AlertDialogManager extends Dialog {
    /*
    * Used Show dialog first then
    * */
    private Context mContext;

    private LayoutInflater inflater;
    private LinearLayout linearLayout;
    private LinearLayout okCancelLL;

    private ImageView headerIcon;

    private TextView titleTV;
    private TextView desTV;

    private Button retryBTN;
    private Button cancelBTN;
    private Button okBTN;


    private int headerImageType;
    public static int HEADER_ICON_NONE=0;
    public static int HEADER_ICON_WARNING=1;

    private boolean retryButtonStatus=false;
    private boolean okCancelBtnStatus=false;

    private String titleString="Error";
    private String desString="Please try later...";
    private String retryButtonString="Retry";
    private String okButtonString="Ok";
    private String cancelButtonString="Cancel";

    private int titleColor=R.color.black;
    private int desColor=R.color.black;
    private int retryButtonColor=R.color.black;
    private int retryButtonBCKColor=R.color.blue_btn_bg_color;
    private int okButtonColor=R.color.black;
    private int okButtonBCKColor=R.color.blue_btn_bg_color;
    private int cancelButtonColor=R.color.black;
    private int cancelButtonBCKColor=R.color.blue_btn_bg_color;


   /* public AlertDialogManager(Context context,int headerImgType) {
        super(context);
        this.mContext=context;
        this.inflater=LayoutInflater.from(mContext);
        this.headerImageType=headerImgType;
    }*/

    public AlertDialogManager(Context context, int themeResId, int headerImgType) {
        super(context, themeResId);
        this.mContext=context;
        this.inflater=LayoutInflater.from(mContext);
        this.headerImageType=headerImgType;
    }


    @Override
    protected void onStart() {
        super.onStart();
       // this.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation_2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.dialog_network_error,null);

        linearLayout=(LinearLayout)view.findViewById(R.id.headerLL);
        okCancelLL=(LinearLayout)view.findViewById(R.id.okCancelLL);

        headerIcon=(ImageView)view.findViewById(R.id.headerIcon);

        titleTV=(TextView)view.findViewById(R.id.titleTV);
        desTV=(TextView)view.findViewById(R.id.desTV);

        retryBTN=(Button)view.findViewById(R.id.confirmBTN);
        cancelBTN=(Button)view.findViewById(R.id.cancelBTN);
        okBTN=(Button)view.findViewById(R.id.okBTN);

        ButterKnife.bind(view);
        setControlVisibility();
        setControlStyle();
        this.setCanceledOnTouchOutside(false);
        this.setContentView(view);
        titleTV.setText(titleString);
        desTV.setText(desString);
        setZoomInOutAnimation(linearLayout);
        setHeaderIcon(headerImageType,headerIcon);
    }

    private void setControlVisibility(){
        if(retryButtonStatus){
            retryBTN.setVisibility(View.VISIBLE);
            retryBTN.setText(retryButtonString);
        }else{
            retryBTN.setVisibility(View.GONE);
        }


        if(okCancelBtnStatus){
            okCancelLL.setVisibility(View.VISIBLE);
            okBTN.setText(okButtonString);
            cancelBTN.setText(cancelButtonString);
        }else{
            okCancelLL.setVisibility(View.GONE);
        }
    }


    /*
    * set control style
    * */

    private void setControlStyle(){
        titleTV.setTextColor(mContext.getResources().getColor(titleColor));
        desTV.setTextColor(mContext.getResources().getColor(desColor));
        retryBTN.setTextColor(mContext.getResources().getColor(retryButtonColor));
        retryBTN.setBackgroundColor(mContext.getResources().getColor(retryButtonBCKColor));
        okBTN.setTextColor(mContext.getResources().getColor(okButtonColor));
        okBTN.setBackgroundColor(mContext.getResources().getColor(okButtonBCKColor));
        cancelBTN.setTextColor(mContext.getResources().getColor(cancelButtonColor));
        cancelBTN.setBackgroundColor(mContext.getResources().getColor(cancelButtonBCKColor));
    }


   /* * This Animation Used for zoomIn and ZoomOut Effect in Network Dialog
    **/

    private void setZoomInOutAnimation(final LinearLayout linearLayout){
        Animation animation= AnimationUtils.loadAnimation(mContext,R.anim.zoom_in);
        final Animation zoomOutAnimation= AnimationUtils.loadAnimation(mContext,R.anim.zoom_out);
        linearLayout.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.startAnimation(zoomOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /*
    * This method used when user want to show header icon
    * HEADER_ICON_NONE for hinding
    * HEADER_ICON_WARNING for showing
    * */
    private void setHeaderIcon(int headerIconType,ImageView imageView){
        switch (headerIconType){
            case 0:
                hideHeaderIcon();
                break;
            case 1:
                showWarningIcon();
                break;
        }
    }

    /*
    * Used for Hinding header icon
    * */
    private void hideHeaderIcon(){
        headerIcon.setVisibility(View.GONE);
    }

    /*
    * Used for show header icon
    * */
    private void showWarningIcon(){
        headerIcon.setVisibility(View.VISIBLE);
    }

    /*
    * This method set the dialog title with string
    * */
    public AlertDialogManager setDialogTitle(String title){
        titleString=title;
        return this;
    }

    /*
    * This method set the dialog des with string
    * */
    public AlertDialogManager setDialogDescription(String des){
        desString=des;
        return this;
    }



    /*
     *This method set the RetryButton text with string
     *  */
    public AlertDialogManager setRetryButtonText(String text){
        retryButtonString=text;
        return this;
    }



    /*
    * This method handle header icon
    * */
    public AlertDialogManager setHeaderIcon(int img){
        headerIcon.setImageResource(img);
        return this;
    }

    /*
    * Show dialog
    *  */
    public AlertDialogManager showDialog(){
        this.show();
        return this;
    }


    /*
  * Dismiss dialog
  *  */
    public AlertDialogManager dismissDialog(){
        this.dismiss();
        return this;
    }

    /*
   * managed visibility of retry button
   * */
    public AlertDialogManager showRetryButton(boolean status){
        retryButtonStatus=status;
        return this;
    }

    /*
    * Managed ok and cancel button
    * */

    public AlertDialogManager showOkCancelButton(boolean status){
        okCancelBtnStatus=status;
        return  this;
    }

    public AlertDialogManager setOkButtonText(String text){
        okButtonString=text;
        return  this;
    }

    public AlertDialogManager setCancelButton(String text){
        cancelButtonString=text;
        return  this;
    }

    /*
 * These methods return  button object
 * We can perform differnt different action by using this method
 *
 * */
    public Button getRetryButton(){
        return retryBTN;
    }

    public Button getOkButton(){
        return okBTN;
    }

    public Button getCancelButton(){
        return cancelBTN;
    }

    /*
    * Managed Style
    * */
    public AlertDialogManager setTitleTextColor(int color){
        titleColor=color;
        return this;
    }

    public AlertDialogManager setDescriptionTextColor(int color){
        desColor=color;
        return this;
    }

    public AlertDialogManager setRetryButtonTextColor(int color){
        retryButtonColor=color;
        return this;
    }

    public AlertDialogManager setRetryButtonBackgroundColor(int color){
        retryButtonBCKColor=color;
        return this;
    }

    public AlertDialogManager setOkButtonTextColor(int color){
        okButtonColor=color;
        return this;
    }

    public AlertDialogManager setOkButtonBackgroundColor(int color){
        okButtonBCKColor=color;
        return this;
    }


    public AlertDialogManager setCancelButtonTextColor(int color){
        cancelButtonColor=color;
        return this;
    }

    public AlertDialogManager setCancelButtonBackgroundColor(int color){
        cancelButtonBCKColor=color;
        return this;
    }

}
