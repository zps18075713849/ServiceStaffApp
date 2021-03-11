package com.haitian.servicestaffapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CommonlyUtils {
    private static long lastClickTime;
    private final static int SPACE_TIME = 900;

    /**
     * 隐藏输入框
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager
                        .HIDE_NOT_ALWAYS);
            }
        }
    }
    /*
    * 禁止重复点击
    * */
    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }




}
