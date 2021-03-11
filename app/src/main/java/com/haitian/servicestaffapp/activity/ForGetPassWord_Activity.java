package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

public class ForGetPassWord_Activity extends BaseActivity {

    private MyEdtext mNewpassword_ed;
    private MyEdtext mNewpassword_ed2;
    private MyEdtext mMobile_ed;
    private MyEdtext mMes_code_ed;
    private Button mGetduanxin_btn;
    private Button mRight_btn;
    private TextView mLijizhuce_tv;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_for_get_pass_word_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //新密码输入框
        mNewpassword_ed = findViewById(R.id.newpassword_ed);
        //确认密码
        mNewpassword_ed2 = findViewById(R.id.newpassword_ed2);
        //手机号
        mMobile_ed = findViewById(R.id.mobile_ed);
        //验证码输入框
        mMes_code_ed = findViewById(R.id.mes_code_ed);
        //获取验证码按钮
        mGetduanxin_btn = findViewById(R.id.getduanxin_btn);
        //提交按钮
        mRight_btn = findViewById(R.id.right_btn);
        //联系管理员
        mLijizhuce_tv = findViewById(R.id.lijizhuce_tv);

    }

    @Override
    public Context context() {
        return null;
    }
}
