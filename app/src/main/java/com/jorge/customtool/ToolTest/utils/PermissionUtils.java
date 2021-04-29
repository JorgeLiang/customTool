package com.jorge.customtool.ToolTest.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {

    public static String TAG = "PermissionUtils";
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final int CODE_READ_WRITE_EXTERNAL_STORAGE = 7;

    public static boolean checkPermission(Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkReadWritePermission(Context mContext) {
        if (PermissionUtils.checkPermission(mContext, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            Log.d(TAG,"have read write permission");
            return true;
        }else {
            Log.d(TAG,"no read write permission");
            ActivityCompat.requestPermissions((Activity) mContext,
                    new String[]{PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE}, PermissionUtils.CODE_READ_WRITE_EXTERNAL_STORAGE);
            return false;
        }
    }

}
