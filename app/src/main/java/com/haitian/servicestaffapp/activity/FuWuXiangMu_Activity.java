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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;

import org.w3c.dom.Text;

public class FuWuXiangMu_Activity extends BaseActivity {

    private ImageView mTitle_back;
    private TextView mSousuo_tv2;
    private TextView mRight_tv;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_fu_wu_xiang_mu_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTitle_back = findViewById(R.id.title_back);
        mSousuo_tv2 = findViewById(R.id.sousuo_tv2);
        mRight_tv = findViewById(R.id.title_right_tv);
        mTitle_back.setVisibility(View.VISIBLE);
        mSousuo_tv2.setVisibility(View.VISIBLE);
        mRight_tv.setVisibility(View.VISIBLE);
        mRight_tv.setText("服务项目");

        RelativeLayout xiangmu_relative = findViewById(R.id.xiangmu_relative);
        Button delete_btn = findViewById(R.id.delete_btn);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
            }
        });

        xiangmu_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FuWuXiangMu_Activity.this,UpdateFuWuXiangMu_Activity.class));
            }
        });
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
