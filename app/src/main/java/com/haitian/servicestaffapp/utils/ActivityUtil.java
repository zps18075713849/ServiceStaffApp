package com.haitian.servicestaffapp.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;

public class ActivityUtil {
    public static String getRunningActivityName(){
        ActivityManager manager = (ActivityManager) DoctorBaseAppliction.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        String activityName = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        String name = activityName.substring(activityName.lastIndexOf(".")+1, activityName.length());
        return name;
    }

    /**
     * 获取当前显示的Fragment名称
     * @return
    <span style="white-space:pre">	</span> */
    public static String getCurrentFragmentName(BaseFragment fragment){
        String fragName = fragment.getClass().toString();
        fragName = fragName.substring(fragName.lastIndexOf(".")+1, fragName.length());
        return fragName;
    }
}
