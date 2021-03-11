package com.haitian.servicestaffapp.utils.permisson;

/**
 * Created by chong.han on 2018/10/29.
 */

public interface PermissionInterface {
    /**
     * 请求权限成功回调
     */
    void requestPermissionsSuccess(int callBackCode);

    /**
     * 请求权限失败回调
     */
    void requestPermissionsFail(int callBackCode);
}
