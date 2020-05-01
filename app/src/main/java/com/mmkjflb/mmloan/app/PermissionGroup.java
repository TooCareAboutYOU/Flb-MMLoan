package com.mmkjflb.mmloan.app;

import android.Manifest;

/**
 * @author zhangshuai
 */
public class PermissionGroup {

    /**
     * 登录时候 定位+设备信息 权限
     */
    public static String[] PERMISSION_LOGIN = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION};

    public static String[] PERMISSION_PHOTO = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    ////定位
    public static String[] PERMISSION_GROUP = new String[]{
            //设备信息
            Manifest.permission.READ_PHONE_STATE,
            //同通讯录联系人
            Manifest.permission.READ_CONTACTS,
            //定位
            Manifest.permission.ACCESS_FINE_LOCATION,
            //拍照
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
}
