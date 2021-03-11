package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

public class Register_Activity extends BaseActivity {

    private Button mNext_btn;
    private MyEdtext mMobile_ed;
    private MyEdtext mPassword_ed;
    private MyEdtext mMes_code_ed;
    private Button mGetduanxin_btn;
    private TextView mUseragreement_tv;
    private CheckBox mCheck_id;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //下一步
        mNext_btn = findViewById(R.id.next_btn);
        //手机号
        mMobile_ed = findViewById(R.id.mobile_ed);
        //密码
        mPassword_ed = findViewById(R.id.password_ed);
        //验证码
        mMes_code_ed = findViewById(R.id.mes_code_ed);
        //获取验证码
        mGetduanxin_btn = findViewById(R.id.getduanxin_btn);
        //服务用户协议
        mUseragreement_tv = findViewById(R.id.useragreement_tv);
        //是否阅读
        mCheck_id = findViewById(R.id.check_id);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mNext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_Activity.this, Register_MessageInfo_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
