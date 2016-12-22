package com.androidapp.bahs.activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @OnClick(R.id.button11)
    public void openOfferPositionActivity(){startActivity(new Intent(this,OfferPositionActivity.class));}
    @OnClick(R.id.button2)
    public void openAboutmeActivity(){
        startActivity(new Intent(this,AboutMeActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }
}
