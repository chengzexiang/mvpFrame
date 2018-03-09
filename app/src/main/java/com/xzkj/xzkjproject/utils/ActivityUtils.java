package com.xzkj.xzkjproject.utils;

import android.app.Activity;
import android.content.Intent;

import com.xzkj.xzkjproject.view.HomePageActivity;

public class ActivityUtils {

    /**
     * 首页
     *
     * @param mActivity
     */
    public static void startHomePageActivity(Activity mActivity) {
        mActivity.startActivity(new Intent(mActivity, HomePageActivity.class));
    }
}
