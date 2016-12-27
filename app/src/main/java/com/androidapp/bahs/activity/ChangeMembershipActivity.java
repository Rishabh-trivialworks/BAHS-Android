package com.androidapp.bahs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.adapter.ChangeMembershipAdaptor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeMembershipActivity extends AppCompatActivity {
    @BindView(R.id.changeMembershipRV)
    RecyclerView membershipRV;

    private ChangeMembershipAdaptor mChangemembershipAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_membership);
        ButterKnife.bind(this);
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add("add--"+i);
        }
        mChangemembershipAdapter=new ChangeMembershipAdaptor(this,list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        membershipRV.setLayoutManager(layoutManager);
        membershipRV.setAdapter(mChangemembershipAdapter);
    }
}
