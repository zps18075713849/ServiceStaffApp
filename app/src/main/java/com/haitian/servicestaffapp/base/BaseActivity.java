package com.haitian.servicestaffapp.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bigkoo.pickerview.TimePickerView;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.youngfeng.snake.annotations.EnableDragToClose;

import butterknife.ButterKnife;


@EnableDragToClose
public abstract class BaseActivity extends AppCompatActivity {
    boolean them = false;
    protected Context mContext = this;
    protected TimePickerView pvTime;
    protected LoadingDailog.Builder loadBuilder;
    protected LoadingDailog dialog;
    private Dialog dialog2;
    protected int mWidth;
    protected int mHeight;
    protected float mDensity;
    protected int mDensityDpi;
    protected int mAvatarSize;
    protected float mRatio;

    private Handler myHandler = new Handler();
    private int TIME = 2000*60;  //每隔两分钟执行一次.
    private int a = 0;      //用来判断用户是否执行操作，1是执行，2是未操作
    private int b = 0;      //用来计时。
    private String mTime;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(provideBindView());

//        Snake.host(this);
        initViews();
        initData();
        initListener();
        setStatusBar();


//        initFontScale();

        //注册sdk的event用于接收各种event事件
//        JMessageClient.registerEventReceiver(this);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mDensity = dm.density;
        mDensityDpi = dm.densityDpi;
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
        mRatio = Math.min((float) mWidth / 720, (float) mHeight / 1280);
        mAvatarSize = (int) (50 * mDensity);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        //设置手机应用内部状态栏字体图标为白色
        changeStatusBarTextImgColor(false);
        //设置手机应用内部状态栏字体图标为黑色
//        changeStatusBarTextImgColor(true);


    }


    Handler handler = new Handler();
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                handler.postDelayed(this, TIME);
//                if (a == 1){
//                    a = 0;
//                    b+=2;
//                    mTime = b+"";
////                    Toast.makeText(mContext, "时间："+mTime, Toast.LENGTH_SHORT).show();
//                    DoctorBaseAppliction.spUtil.putString(Constants.Time,mTime);
//
//                    LogUtil.e("if时间 mtime"+ mTime);
//                    LogUtil.e("if时间 b"+ b);
//                }else {
////                    Toast.makeText(mContext, "时间"+mTime, Toast.LENGTH_SHORT).show();
//                    DoctorBaseAppliction.spUtil.putString(Constants.Time,mTime);
//                    LogUtil.e("else时间 mtime"+ mTime);
//                    LogUtil.e("else时间 b"+ b);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };



    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                a = 1;
//                LogUtil.e("ACTION_DOWN"+a+"");
                myHandler.removeCallbacks(myRunnable);
                break;
            case MotionEvent.ACTION_MOVE:
                a = 1;
//                LogUtil.e("ACTION_MOVE"+a+"");
                myHandler.removeCallbacks(myRunnable);
                break;
            case MotionEvent.ACTION_CANCEL:
                a = 2;
//                LogUtil.e("ACTION_CANCEL"+a+"");
                myHandler.postDelayed(myRunnable, 30000);
                break;
            case MotionEvent.ACTION_UP:
                a = 1;
//                LogUtil.e("ACTION_UP"+a+"");
                myHandler.postDelayed(myRunnable, 30000);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getAction()) {
            case KeyEvent.ACTION_DOWN:
                a =1 ;
//                LogUtil.e("ACTION_DOWN"+a+"");
                myHandler.removeCallbacks(myRunnable);
                break;
            case KeyEvent.ACTION_UP:
                a = 2;
//                LogUtil.e("ACTION_UP"+a+"");
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    private Runnable myRunnable = new Runnable() {

        @Override
        public void run() {
            //TODO 30s无操作
//            Toast.makeText(mContext, "挂机是无法获取活跃奖励的哦", Toast.LENGTH_SHORT).show();
        }
    };





    public void changeStatusBarTextImgColor(boolean isBlack) {
        if (isBlack) {
            //设置状态栏黑色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            //恢复状态栏白色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    protected void setStatusBar() {
    }

    @SuppressLint("ResourceAsColor")
    public void showAffirmDialog(String title, String message, View.OnClickListener
            onClickListener) {
        new CircleDialog.Builder(this)
                .setTitle(title)
                .setText(message)//内容
                .setTextColor(R.color.qianlan)//内容字体色值
                .setPositive("确定", onClickListener)
                .setNegative("取消", null)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .show();
    }


    @SuppressLint("ResourceAsColor")
    public void showInputDialog(String title, String hint, OnInputClickListener onInputClickListener) {
        new CircleDialog.Builder(this)
                //添加标题，参考普通对话框
                .setInputHint(hint)//提示
                .setTitle(title)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .setInputCounterColor(R.color.text_color)//最大字符数文字的颜色值
                .autoInputShowKeyboard()//自动弹出键盘
                .setPositiveInput("确定", onInputClickListener)
                .show();
    }




    @SuppressLint("ResourceAsColor")
    public void showInputDialog2(String text, OnInputClickListener onInputClickListener) {
        new CircleDialog.Builder(this)
                //添加标题，参考普通对话框
//                .setInputHint(hint)//提示
                .setInputText(text)
                .setTitle("修改分组")
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .setInputCounterColor(R.color.text_color)//最大字符数文字的颜色值
                .autoInputShowKeyboard()//自动弹出键盘
                .setPositiveInput("确定", onInputClickListener)
                .show();
    }


    @SuppressLint("ResourceAsColor")
    public void showAffirmDialog5(String title, String message, View.OnClickListener
            onClickListener) {
        new CircleDialog.Builder(this)
                .setTitle(title)
                .setText(message)//内容
                .setTextColor(R.color.qianlan)//内容字体色值
                .setNegative("我知道了", null)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .show();
    }

    public void showWaitDialog() {
//        if (dialog.isShowing()) return;
        loadBuilder = new LoadingDailog.Builder(this)
                .setCancelable(false)
                .setShowMessage(false)
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        dialog.show();
    }


    public void hideWaitDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }

    public void initFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = (float) 0.85;
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }


    protected abstract Activity provideBindView();

    protected abstract int provideLayoutId();


    protected void initViews() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    public abstract Context context();



    //重写字体缩放比例 api<25
    @Override
    public Resources getResources() {
        Resources res =super.getResources();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            Configuration config = res.getConfiguration();
            config.fontScale = DoctorBaseAppliction.getFontSize();//设置正常字体大小的倍数
            res.updateConfiguration(config,res.getDisplayMetrics());
        }
        return res;
    }


    //重写字体缩放比例  api>25
    @Override
    protected void attachBaseContext(Context newBase) {
        if(Build.VERSION.SDK_INT> Build.VERSION_CODES.N){
            final Resources res = newBase.getResources();
            final Configuration config = res.getConfiguration();
            config.fontScale = DoctorBaseAppliction.getFontSize();//设置正常字体大小的倍数
            final Context newContext = newBase.createConfigurationContext(config);
            super.attachBaseContext(newContext);
        }else{
            super.attachBaseContext(newBase);
        }
    }

//    private void initFontScale() {
//        Configuration configuration = getResources().getConfiguration();
//        configuration.fontScale = (float) 1.8;
//        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        metrics.scaledDensity = configuration.fontScale * metrics.density;
//        getBaseContext().getResources().updateConfiguration(configuration, metrics);
//    }






}
