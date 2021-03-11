package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
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

import org.w3c.dom.Text;

public class KeHuGuanLi_Activity extends BaseActivity {

    private ImageView mBack;
    private TextView mSousuo_tv;
    private ImageView mTitle_right_riqi;
    private SmartRefreshLayout mSmart_id;
    private RecyclerView mRecy_id;
    private TabLayout mTab_id;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ke_hu_guan_li_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mSousuo_tv = findViewById(R.id.sousuo_tv);
        mTitle_right_riqi = findViewById(R.id.title_right_riqi);

        mBack.setVisibility(View.VISIBLE);
        mSousuo_tv.setVisibility(View.VISIBLE);
        mTitle_right_riqi.setVisibility(View.VISIBLE);

        mTab_id = findViewById(R.id.tab_id);
        mSmart_id = findViewById(R.id.smart_id);
        mRecy_id = findViewById(R.id.recy_id);

        mTab_id.addTab(mTab_id.newTab().setText("新客户"));
        mTab_id.addTab(mTab_id.newTab().setText("老客户"));
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
