package com.androidapp.bahs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.TextView;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mobikasa Night on 12/23/2016.
 */

public class ScheduleTrailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_scheduletrail);
        ButterKnife.bind(this);
    }
}
