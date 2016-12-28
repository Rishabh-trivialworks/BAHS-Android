package com.androidapp.bahs.activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        NetworkDialog networkDialog=new NetworkDialog(this);
        networkDialog.show();
    }
}
