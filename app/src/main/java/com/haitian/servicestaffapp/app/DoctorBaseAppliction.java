package com.haitian.servicestaffapp.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;


import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.utils.SPUtil;
import com.haitian.servicestaffapp.utils.SPUtil2;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

//import cn.jpush.android.api.JPushInterface;

public class DoctorBaseAppliction extends Application implements Application.ActivityLifecycleCallbacks {
    public static boolean isTestState = true;
    private static DoctorBaseAppliction application;
    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    public static boolean isUpdate = true;
    public static boolean isAlertInternet = true;
    public static boolean isAlertWifi = true;
    public static SPUtil spUtil;
    public static SPUtil2 spUtil2;
    public static OkHttpClient sOkHttpClient;
    //    public static String JMAppKey_PATIENT = "c92f06f1775717099f2cb9dd";
//    public static String JMAppKey_DOCTOR = "6b85c1a5067e01299b065b7b";

    //计时器
    private Handler mhandle = new Handler();
    private boolean isPause = false;//是否暂停
    private int currentSecond = 0;//当前秒数

    private int countActivity = 0;
    //是否进入后台
    private boolean isBackground = false;
    private SharedPreferences preferences;
    private float fontScale;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        spUtil = new SPUtil(this, "fileName");
        spUtil2 = new SPUtil2(this, "fileName2");

        // 解决 7.0以上FileUriExposedException  机械 mechanical
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        File cacheDir = new File(DoctorBaseAppliction.application.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        sOkHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES) //设置连接超时
                .readTimeout(1, TimeUnit.MINUTES) //设置读取超时
                .writeTimeout(1, TimeUnit.MINUTES) //设置写入超时
                .cache(cache)
                .build();


        //开始计时器，用来计时
//        timeRunable.run();

        //和app是否进入后台有关的回调
        registerActivityLifecycleCallbacks(this);

    }

    public static Context getContext() {
        return application.getApplicationContext();
    }

    //单例模式中获取唯一的MyApplication实例
    public static DoctorBaseAppliction getInstance() {
        if (null == application) {
            application = new DoctorBaseAppliction();
        }
        return application;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        countActivity++;
        if (countActivity == 1 && isBackground) {
            isBackground = false;
            currentSecond = 0;
            isPause = false;
//开始计时器，用来计时
//            timeRunable.run();

            LogUtil.e("前台前台");
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    /*****************计时器*******************/
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {
            currentSecond = currentSecond + 1;
            if (!isPause) {
                mhandle.postDelayed(this, 1000 * 60);
            }
        }
    };

    /*****************计时器*******************/

    @Override
    public void onActivityStopped(Activity activity) {
        countActivity--;
        LogUtil.e(isRun(activity) + "");

        if (countActivity <= 0 && !isBackground && !isRun(activity)) {
            LogUtil.e("进入后台");
            isPause = true;
            LogUtil.e("上传时间" + currentSecond + "");
            isBackground = true;
        }
    }




    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    /**
     * 判断应用是否在运行
     *
     * @param context
     * @return
     */
    public boolean isRun(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        String MY_PKG_NAME = "com.ad";
        //100表示取的最大的任务数，info.topActivity表示当前正在运行的Activity，info.baseActivity表系统后台有此进程在运行
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(MY_PKG_NAME) || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                isAppRunning = true;
                Log.i("ActivityService isRun()", info.topActivity.getPackageName() + " info.baseActivity.getPackageName()=" + info.baseActivity.getPackageName());
                break;
            }
        }

        Log.i("ActivityService isRun()", "com.ad 程序   ...isAppRunning......" + isAppRunning);
        return isAppRunning;
    }


    public static float getFontSize() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("font_size", Context.MODE_PRIVATE);
        return sharedPreferences.getFloat("fontsize", 1);
    }

    public static void setFontSize(float value) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("font_size", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("fontsize", value);
        editor.commit();
    }

}
