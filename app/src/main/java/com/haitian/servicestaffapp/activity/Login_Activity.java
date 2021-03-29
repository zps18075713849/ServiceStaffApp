package com.haitian.servicestaffapp.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.Login_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login_Activity extends BaseActivity {

    private MyEdtext mMobile_ed;
    private MyEdtext mPassword_ed;
    private TextView mForgetpassword_tv;
    private Button mLogin_bt;
    private TextView mLijizhuce_tv;
    private TextView mCode_login_tv;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mMobile_ed = findViewById(R.id.mobile_ed);
        //密码
        mPassword_ed = findViewById(R.id.password_ed);
        //忘记密码
        mForgetpassword_tv = findViewById(R.id.forgetpassword_tv);
        //登录按钮
        mLogin_bt = findViewById(R.id.login_bt);
        //注册
        mLijizhuce_tv = findViewById(R.id.lijizhuce_tv);
        //验证码登录
        mCode_login_tv = findViewById(R.id.code_login_tv);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mLijizhuce_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
            }
        });

        mLogin_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });

        mForgetpassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, ForGetPassWord_Activity.class);
                startActivity(intent);
            }
        });

        mCode_login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, CodeLogin_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String mobile = mMobile_ed.getText().toString().trim();
        String password = mPassword_ed.getText().toString().trim();

        if (mobile.equals("") || mobile.length() != 11) {
            Toast.makeText(mContext, "请检查手机号格式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(mContext, "请填写您的密码", Toast.LENGTH_SHORT).show();
            return;
        }

        requestLogin(mobile, password);

    }

    private void requestLogin(String mobile, String password) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", mobile);
        map.put("user_pwd", password);
        OkHttpUtil.getInteace().doPost(Constants.LOGIN, map, Login_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("登录失败:" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("登录成功：" + json);

                Gson gson = new Gson();
                final Login_Bean login_bean = gson.fromJson(json, Login_Bean.class);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (login_bean.getCode() == 20041) {
                            Toast.makeText(mContext, login_bean.getMessage(), Toast.LENGTH_SHORT).show();
                            SputilData(login_bean);
                            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(mContext, login_bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //本地保存数据
    private void SputilData(Login_Bean login_bean) {
        try {
            DoctorBaseAppliction.spUtil.putString(Constants.USERID,login_bean.getData().getUser_id()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_NAME,login_bean.getData().getUser_name()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_NICKNAME,login_bean.getData().getUser_nickname()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_CARD_NAME,login_bean.getData().getUser_card_name()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_PWD,login_bean.getData().getUser_pwd()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_AFFILIATE,login_bean.getData().getUser_affiliate()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_ROLE_ID,login_bean.getData().getUser_role_id()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_DEPT,login_bean.getData().getUser_dept()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_MESSAGEID,login_bean.getData().getMessageId()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_MESSAGE,login_bean.getData().getMessgae()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_TYPE,login_bean.getData().getUser_type()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_DEPARTMENT,login_bean.getData().getUser_department()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_ZHENGJIAN,login_bean.getData().getUser_zhengjian()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_NEWUSER_PWD,login_bean.getData().getNewuser_pwd()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.SHOUCHIZHAOP,login_bean.getData().getShouchizhaop()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.HUOJIANGZHENGSHU,login_bean.getData().getHuojiangzhengshu()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.SHOUCHI,login_bean.getData().getShouchi()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.HUOJIANGZHENGS,login_bean.getData().getHuojiangzhengs()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Context context() {
        return null;
    }

    //    退出应用
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void exitAPP() {
        ActivityManager activityManager = (ActivityManager) this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        for (ActivityManager.AppTask appTask : appTaskList) {
            appTask.finishAndRemoveTask();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitAPP();
            DoctorBaseAppliction.spUtil.clear();
        }
        return false;
    }
}
