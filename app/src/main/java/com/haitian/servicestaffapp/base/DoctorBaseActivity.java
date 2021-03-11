package com.haitian.servicestaffapp.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bigkoo.pickerview.TimePickerView;
import com.haitian.servicestaffapp.R;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.youngfeng.snake.Snake;
import com.youngfeng.snake.annotations.EnableDragToClose;


/**
 * Created by chong.han on 2018/8/16.
 */
@EnableDragToClose
public abstract class DoctorBaseActivity extends AppCompatActivity {
    protected Context mContext = this;
    protected LoadingDailog.Builder loadBuilder;
    protected LoadingDailog dialog;
    protected TimePickerView pvTime;
    private Dialog dialog2;
    protected int mWidth;
    protected int mHeight;
    protected float mDensity;
    protected int mDensityDpi;
    protected int mAvatarSize;
    protected float mRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Snake.host(this);

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

        //设置手机应用内部状态栏字体图标为白色
//        changeStatusBarTextImgColor(flase);
        //设置手机应用内部状态栏字体图标为黑色
        changeStatusBarTextImgColor(true);
    }

    public void changeStatusBarTextImgColor(boolean isBlack) {
        if (isBlack) {
            //设置状态栏黑色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            //恢复状态栏白色字体
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    public abstract void initData();

    public abstract void initListener();

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

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            dialog.cancel();
        }
    }

//    public void onEventMainThread(LoginStateChangeEvent event) {
//        final LoginStateChangeEvent.Reason reason = event.getReason();
//        UserInfo myInfo = event.getMyInfo();
//        if (myInfo != null) {
//            String path;
//            File avatar = myInfo.getAvatarFile();
//            if (avatar != null && avatar.exists()) {
//                path = avatar.getAbsolutePath();
//            } else {
//                path = FileHelper.getUserAvatarPath(myInfo.getUserName());
//            }
//            SharePreferenceManager.setCachedUsername(myInfo.getUserName());
//            SharePreferenceManager.setCachedAvatarPath(path);
//            JMessageClient.logout();
//            LogUtil.e("----------极光退出");
//        }
//        switch (reason) {
//            case user_logout:
//                View.OnClickListener listener = new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        switch (v.getId()) {
//                            case R.id.jmui_cancel_btn:
//                                LogUtil.e("-----------重新登录----dfsdf-----000111");
//                                DoctorBaseAppliction.spUtil.clear();
////                                JMessageClient.logout();
//                                Intent intent = new Intent(DoctorBaseActivity.this, LoginActivity.class);
//                                startActivity(intent);
//                                finish();
//                                break;
//                            case R.id.jmui_commit_btn:
//                                LogUtil.e("-----------重新登录6666666666666"+SharePreferenceManager.getCachedUsername());
//                                LogUtil.e("-----------重新登录6666666666666"+SharePreferenceManager.getCachedPsw());
//                                if(SharePreferenceManager.getCachedPsw()==null || SharePreferenceManager.getCachedPsw().equals("")){
//                                    SharePreferenceManager.setCachedPsw("123456");
//                                }
//                                JMessageClient.login(SharePreferenceManager.getCachedUsername(),
//                                        SharePreferenceManager.getCachedPsw(), new BasicCallback() {
//                                            @Override
//                                            public void gotResult(int responseCode, String
//                                                    responseMessage) {
//                                                if (responseCode == 0) {
//                                                    Intent intent = new Intent(DoctorBaseActivity.this,
//                                                            MainActivity222.class);
//                                                    startActivity(intent);
//                                                }
//                                            }
//                                        });
//                                break;
//                        }
//                    }
//                };
//                dialog2 = DialogCreator.createLogoutStatusDialog(DoctorBaseActivity.this,
//                        "您的账号在其他设备上登陆", listener);
//                dialog2.getWindow().setLayout((int) (0.8 * mWidth), WindowManager.LayoutParams
//                        .WRAP_CONTENT);
//                dialog2.setCancelable(false);
//                dialog2.show();
//                break;
//            case user_password_change:
//                LogUtil.e("-----------重新登录----hfdhfd---dddd--000");
//                Intent intent = new Intent(DoctorBaseActivity.this, LoginActivity.class);
//                startActivity(intent);
//                LogUtil.e("-----------重新登录++++++ghfgh+++2222++000");
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        JMessageClient.unRegisterEventReceiver(this);
//        if (EventBus.getDefault().isRegistered(this)){ EventBus.getDefault().unregister(this);}
//        if (dialog != null) {
//            dialog.cancel();
//        }
//    }

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

    @SuppressLint("ResourceAsColor")
    public void showTagInputDialog(String hint, String text, OnInputClickListener onInputClickListener) {
        new CircleDialog.Builder(this)
                //添加标题，参考普通对话框
                .setInputHint(hint)//提示
                .setTitle("请输入")
                .setText(text)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .setInputCounterColor(R.color.text_color)//最大字符数文字的颜色值
                .autoInputShowKeyboard()//自动弹出键盘
                .setPositiveInput("确定", onInputClickListener)
                .show();
    }

//    @Subscribe
//    public void onEventMainThread(Map<String, Object> map) {
//        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
//        if (type.equals(MAIN_BACK_LOGIN)) {
//            LogUtil.e("ttttttt--DoctorBase", "退出");
//            finish();
//            exit(0);
//        }
//    }


}
