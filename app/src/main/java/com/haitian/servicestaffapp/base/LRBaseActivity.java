package com.haitian.servicestaffapp.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bigkoo.pickerview.TimePickerView;
import com.haitian.servicestaffapp.R;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;

import butterknife.ButterKnife;


public abstract class LRBaseActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(provideBindView());
        initViews();
        initData();
        initListener();
        setStatusBar();


        //设置手机应用内部状态栏字体图标为白色
//        changeStatusBarTextImgColor(flase);
        //设置手机应用内部状态栏字体图标为黑色
        changeStatusBarTextImgColor(true);

        //注册sdk的event用于接收各种event事件
//        JMessageClient.registerEventReceiver(this);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }

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
                .setTextColor(R.color.text_color)//内容字体色值
                .setPositive("确定", onClickListener)
                .setNegative("取消", null)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .show();
    }


    @SuppressLint("ResourceAsColor")
    public void WxLoginDialog(String title, String message, View.OnClickListener
            onClickListener, View.OnClickListener onClickListenerbangding) {
        new CircleDialog.Builder(this)
                .setTitle(title)
                .setText(message)//内容
                .setTextColor(R.color.text_color)//内容字体色值
                .setPositive("完善信息", onClickListener)
                .setNegative("去绑定已有账号", onClickListenerbangding)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .show();
    }


    @SuppressLint("ResourceAsColor")
    public void showInputDialog(String hint, OnInputClickListener onInputClickListener) {
        new CircleDialog.Builder(this)
                //添加标题，参考普通对话框
                .setInputHint(hint)//提示
                .setTitle("请输入")
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .setInputCounterColor(R.color.text_color)//最大字符数文字的颜色值
                .autoInputShowKeyboard()//自动弹出键盘
                .setPositiveInput("确定", onInputClickListener)
                .show();
    }



    public void showWaitDialog() {
//        if (dialog.isShowing()) return;
        loadBuilder = new LoadingDailog.Builder(this)
                .setCancelable(true)
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


    protected abstract Activity provideBindView();

    protected abstract int provideLayoutId();


    protected void initViews() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    @Override
    protected void onDestroy() {
        //注销消息接收
//        JMessageClient.unRegisterEventReceiver(this);
        if (dialog2 != null) {
            dialog2.dismiss();
        }
        super.onDestroy();
    }


//    @Subscribe
//    public void onEventMainThread(Map<String, Object> map) {
//        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
//        if (type.equals(MAIN_BACK_LOGIN)) {
//            LogUtil.e("ttttttt--DoctorBase", "退出");
//            finish();
//        }
//    }

    public abstract Context context();
}
