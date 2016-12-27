package com.androidapp.bahs.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;
import com.androidapp.bahs.adapter.FindStaffingAdaptor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mobikasa on 12/26/2016.
 */

public class FindStaffingActivity extends BaseActivity {

    @BindView(R.id.findStaffRV)
    RecyclerView staffingRV;

    private FindStaffingAdaptor findStaffingAdaptor;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.find_staffing_activity);
        ButterKnife.bind(this);
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("add--"+i);
        }

        findStaffingAdaptor=new FindStaffingAdaptor(this,list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        staffingRV.setLayoutManager(layoutManager);
        staffingRV.setAdapter(findStaffingAdaptor);
    }

}
