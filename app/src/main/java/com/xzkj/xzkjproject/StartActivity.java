package com.xzkj.xzkjproject;

import android.graphics.Color;
import android.os.Bundle;

import com.xzkj.xzkjproject.base.BaseActivity;
import com.xzkj.xzkjproject.utils.ActivityUtils;
import com.xzkj.xzkjproject.utils.statusbar.Eyes;

public class StartActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eyes.setStatusBarLightMode(this, Color.WHITE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        findViewById(R.id.to_home).setOnClickListener(v -> {
            ActivityUtils.startHomePageActivity(this);
        });
    }

    @Override
    public void loadData() {

    }


}
