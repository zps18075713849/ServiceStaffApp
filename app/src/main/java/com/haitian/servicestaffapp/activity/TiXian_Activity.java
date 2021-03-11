package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;

import org.w3c.dom.Text;

public class TiXian_Activity extends BaseActivity {

    private TextView mYue_tv;
    private EditText mPrice_edit;
    private Button mAll_bt;
    private EditText mMobile_edit;
    private Button mNowtixian_btn;
    private ImageView mTitle_back;
    private TextView mTitle_tv;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ti_xian_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //余额
        mYue_tv = findViewById(R.id.yue_tv);
        //金额
        mPrice_edit = findViewById(R.id.price_edit);
        //全部
        mAll_bt = findViewById(R.id.all_bt);
        //手机号
        mMobile_edit = findViewById(R.id.mobile_edit);
        //提现
        mNowtixian_btn = findViewById(R.id.nowtixian_btn);
        //退出
        mTitle_back = findViewById(R.id.title_back);
        //totalname
        mTitle_tv = findViewById(R.id.title_tv);
        mTitle_tv.setText("我的余额");
        mTitle_tv.setVisibility(View.VISIBLE);
        mTitle_back.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTitle_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
