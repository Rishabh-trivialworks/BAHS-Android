package com.androidapp.bahs.activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.androidapp.bahs.Dialog.NetworkDialog;
import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.service.bean.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class HomeActivity extends BaseActivity {
    @BindView(R.id.button1)
    Button buttin1;
    @BindView(R.id.button11)
    Button button11;
    @BindView(R.id.button2)
    Button button2;
    @OnClick(R.id.button1)
    public void openListActivity(){
        startActivity(new Intent(this,ListJobActivity.class));
    }

    @BindView(R.id.button3)
    Button button3;
    @OnClick(R.id.button3)
    public void openFindStaffingActivity(){
        startActivity(new Intent(this,FindStaffingActivity.class));
    }

    @OnClick(R.id.button11)
    public void openOfferPositionActivity(){startActivity(new Intent(this,OfferPositionActivity.class));}
    @OnClick(R.id.button2)
    public void openAboutmeActivity(){
        startActivity(new Intent(this,AboutMeActivity.class));}
    @BindView(R.id.button6)
    Button buttin6;
    @OnClick(R.id.button6)
    public void openAfterJobActivity(){
        startActivity(new Intent(this,AfterJobActivity.class));
    }
    @BindView(R.id.button7)
    Button buttin7;
    @OnClick(R.id.button7)
    public void openAfterJobFeedbackActivity(){
        startActivity(new Intent(this,AfterJobFeedbackActivity.class));
    }
    @BindView(R.id.button8)
    Button buttin8;
    @OnClick(R.id.button8)
    public void openScheduleTrailActivity(){
        startActivity(new Intent(this,ScheduleTrailActivity.class));
    }
    @BindView(R.id.button9)
    Button buttin9;
    @OnClick(R.id.button9)
    public void openAfterJobScheduleTrailActivity(){
        startActivity(new Intent(this,AfterJobScheduleTrailActivity.class));
    }
    @BindView(R.id.button12)
    Button buttin12;
    @OnClick(R.id.button12)
    public void openChangeMembershipActivity(){
        startActivity(new Intent(this,ChangeMembershipActivity.class));
    }
    @BindView(R.id.button13)
    Button buttin13;
    @OnClick(R.id.button13)
    public void openProfilepActivity(){
        startActivity(new Intent(this,ProfileActivity.class));}
    @BindView(R.id.button16)
    Button button16;
    @OnClick(R.id.button16)
    public void openPaymentActivity(){
        startActivity(new Intent(this,PaymentActivity.class));
    }
    @BindView(R.id.button17)
    Button button17;
    @OnClick(R.id.button17)
    public void openPaymentConfirmationActivity(){
        startActivity(new Intent(this,PaymentConfirmationActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        NetworkDialog networkDialog=new NetworkDialog(this,R.style.NetworkDialogTheme,NetworkDialog.HEADER_ICON_WARNING);
        networkDialog.setDialogTitle("Connection Error")
                .setDialogDescription("Internet connncetion is not working")
                .setRetryButtonText("Retry")
                .showRetryButton(true)
                .showDialog();
       // networkDialog.getRetryButton().
    }

}
