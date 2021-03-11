package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class RenWu_Activity extends BaseActivity {
    private ImageView mTitle_back;
    private TextView mSousuo_tv;
    private TabLayout mTab_id;
    private SmartRefreshLayout mSmart_id;
    private RecyclerView mRecy_id;
    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ren_wu_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTitle_back = findViewById(R.id.title_back);
        mSousuo_tv = findViewById(R.id.sousuo_tv2);

        mTitle_back.setVisibility(View.VISIBLE);
        mSousuo_tv.setVisibility(View.VISIBLE);

        mSmart_id = findViewById(R.id.smart_id);
        mRecy_id = findViewById(R.id.recy_id);

        mTab_id = findViewById(R.id.tab_id);
        mTab_id.addTab(mTab_id.newTab().setText("任务"));
        mTab_id.addTab(mTab_id.newTab().setText("已完成"));
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
