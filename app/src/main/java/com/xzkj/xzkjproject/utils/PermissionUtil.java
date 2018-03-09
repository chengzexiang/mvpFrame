package com.xzkj.xzkjproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限处理工具
 *
 * @author MriDe
 */
@SuppressWarnings("deprecation")
public class PermissionUtil {

    /**
     * 打开设置权限
     */
    public static final int REQUEST_PERMISSION_SETTING = 0x002;
    /**
     * 相机权限请求码
     */
    public static final int REQUEST_CAMERA_CODE = 101;
    /**
     * 定位请求码
     */
    public static final int REQUEST_LOCATION_CODE = 100;
    /**
     * 权限组请求码
     */
    public static final int REQUEST_GROUP_CODE = 200;
    /**
     * 申请定位权限
     */
    public static final int COARSE_LOCATION_CODE = 201;

    /**
     * 用来判断，App是否是首次启动： ***由于每次调用shouldShowRequestPermissionRationale得到的结果因情况而变，
     * 因此必须判断一下App是否首次启动 ，才能控制好出现Dialog和SnackBar的时机
     */
    public static boolean isAppFirstRun(Activity activity) {
        SharedPreferences sp = activity.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (sp.getBoolean("first_run", true)) {
            editor.putBoolean("first_run", false);
            editor.commit();
            return true;
        } else {
            editor.putBoolean("first_run", false);
            editor.commit();
            return false;
        }
    }

    public static void requestPermission(Activity activity,
                                         String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static boolean isFirstDenited(Activity activity, String permission) {

        return ActivityCompat.shouldShowRequestPermissionRationale(activity,
                permission);
    }

    /**
     * 检查是否有权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermission(Context context, String permission) {
        int grantCode = ActivityCompat.checkSelfPermission(context, permission);
        if (grantCode == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    /**
     * 是否需要权限
     *
     * @return
     */
    public static boolean isRequestPermission() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * 打开设置
     *
     * @param activity
     * @param requestCode
     */
    public static void openSeeting(Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, requestCode);

    }

    /**
     * 检查相机可用
     *
     * @return
     */
    public static boolean camerayIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }

    /**
     * 检查权限
     */
    public static void checkPermission(Activity mActivity,String[] permissionArray) {
        List<String> denyArray = new ArrayList<>();
        for (String aPermissionArray : permissionArray) {
            if (!PermissionUtil.hasPermission(mActivity, aPermissionArray)) {
                denyArray.add(aPermissionArray);
            }
        }
        int size = denyArray.size();
        if (size > 0) {
            String[] array = new String[size];
            for (int i = 0; i < size; i++) {
                array[i] = denyArray.get(i);
            }
            PermissionUtil.requestPermission(mActivity, array,
                    PermissionUtil.REQUEST_GROUP_CODE);
        }
    }
}
