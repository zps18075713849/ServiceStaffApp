package com.haitian.servicestaffapp.utils.permisson;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by chong.han on 2018/10/29.
 */

public class PermissionHelper {
    private Activity mActivity;
    private PermissionInterface mPermissionInterface;
    private String permission;
    private int callBackCode;

    public PermissionHelper(@NonNull Activity activity, @NonNull PermissionInterface
            permissionInterface) {
        mActivity = activity;
        mPermissionInterface = permissionInterface;
    }

    /**
     * 判断是否有某个权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Android
            // 6.0判断，6.0以下跳过。在清单文件注册即可，不用动态请求，这里直接视为有权限
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager
                    .PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请求权限
     *
     * @param permission   权限名字
     * @param callBackCode 回调code
     */
    public void requestPermissions(String permission, int callBackCode) {
        this.permission = permission;
        this.callBackCode = callBackCode;
        if (hasPermission(mActivity, permission)) {
            mPermissionInterface.requestPermissionsSuccess(callBackCode);
        } else {
            ActivityCompat.requestPermissions(mActivity, new String[]{permission}, callBackCode);
        }

    }

    /**
     * 在Activity中的onRequestPermissionsResult中调用,用来接收结果判断
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void requestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults) {
        if (requestCode == callBackCode) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    mPermissionInterface.requestPermissionsSuccess(callBackCode);
                } else {
                    mPermissionInterface.requestPermissionsFail(callBackCode);
                }
            }
        }
    }
}

