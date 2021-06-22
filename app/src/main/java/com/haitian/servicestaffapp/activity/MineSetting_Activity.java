package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;

import org.w3c.dom.Text;

public class MineSetting_Activity extends BaseActivity {

    private TextView mTitle_tv;
    private ImageView mBack;
    private Button mExit_bt;
    private TextView mFuwuxieyi_tv;
    private TextView mYinsi_tv;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_mine_setting_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTitle_tv = findViewById(R.id.title_tv);
        mBack = findViewById(R.id.title_back);
        mExit_bt = findViewById(R.id.exit_bt);

        mFuwuxieyi_tv = findViewById(R.id.fuwuxieyi_tv);
        mYinsi_tv = findViewById(R.id.yinsi_tv);

        mTitle_tv.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.VISIBLE);
        mTitle_tv.setText("我的设置");
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mExit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorBaseAppliction.spUtil.clear();
                Intent intent = new Intent(MineSetting_Activity.this, Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        //服务协议
        mFuwuxieyi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineSetting_Activity.this, XieYi_Web.class);
                intent.putExtra("totalbarName","服务协议");
                intent.putExtra("webUrl","http://111.17.215.37/yanglao/app/serviceAgreement.html");
                startActivity(intent);
            }
        });

        //隐私协议
        mYinsi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineSetting_Activity.this, XieYi_Web.class);
                intent.putExtra("totalbarName","隐私协议");
                intent.putExtra("webUrl","http://111.17.215.37/yanglao/app/privacyPolicy.html");
                startActivity(intent);
            }
        });

    }

    @Override
    public Context context() {
        return null;
    }
}
