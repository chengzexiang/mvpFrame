package com.xzkj.xzkjproject;

import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;
import com.zhy.autolayout.config.AutoLayoutConifg;

public class XZApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        OkHttpUtils.init(this);
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
