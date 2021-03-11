package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

public class Login_Activity extends BaseActivity {

    private MyEdtext mMobile_ed;
    private MyEdtext mPassword_ed;
    private TextView mForgetpassword_tv;
    private Button mLogin_bt;
    private TextView mLijizhuce_tv;

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
                Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mForgetpassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, ForGetPassWord_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }


}
