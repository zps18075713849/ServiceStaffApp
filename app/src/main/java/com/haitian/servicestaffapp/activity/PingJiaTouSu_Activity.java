package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class PingJiaTouSu_Activity extends BaseActivity {

    private ImageView mBack;
    private TextView mSousuo_tv;
    private TextView mTitle_right_tv;
    private TabLayout mTab_id;
    private SmartRefreshLayout mSmart_id;
    private RecyclerView mRecy_id;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ping_jia_tou_su_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mSousuo_tv = findViewById(R.id.sousuo_tv);
        mTitle_right_tv = findViewById(R.id.title_right_tv);

        mBack.setVisibility(View.VISIBLE);
        mSousuo_tv.setVisibility(View.VISIBLE);
        mTitle_right_tv.setVisibility(View.VISIBLE);
        mTitle_right_tv.setText("投诉");

        mTab_id = findViewById(R.id.tab_id);
        mSmart_id = findViewById(R.id.smart_id);
        mRecy_id = findViewById(R.id.recy_id);

        mTab_id.addTab(mTab_id.newTab().setText("未回复评价"));
        mTab_id.addTab(mTab_id.newTab().setText("已回复评价"));

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
    }

    @Override
    public Context context() {
        return null;
    }
}
