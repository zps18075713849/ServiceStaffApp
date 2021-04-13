package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.fragment.gongdan.NewGongDan_Fragment;
import com.haitian.servicestaffapp.fragment.gongdan.YiWanChengGongDan_Fragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class GongDan_Activity extends BaseActivity {
    private ImageView mTitle_back;
    private TextView mSousuo_tv;
    private TabLayout mTab_id;
    private SmartRefreshLayout mSmart_id;
    private RecyclerView mRecy_id;
    private FrameLayout mGongdan_frame;
    private FragmentManager mManager;
    private NewGongDan_Fragment mNewGongDan_fragment;
    private YiWanChengGongDan_Fragment mYiWanChengGongDan_fragment;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_gong_dan_;
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

        mGongdan_frame = findViewById(R.id.gongdan_frame);

        mTab_id = findViewById(R.id.tab_id);
        mTab_id.addTab(mTab_id.newTab().setText("新工单"));
        mTab_id.addTab(mTab_id.newTab().setText("已完成"));

        initFragment();
        initFirstFragment();
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

        mTab_id.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0){
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.show(mNewGongDan_fragment).hide(mYiWanChengGongDan_fragment).commit();
                }else if (position == 1){
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.show(mYiWanChengGongDan_fragment).hide(mNewGongDan_fragment).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFirstFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.show(mNewGongDan_fragment).hide(mYiWanChengGongDan_fragment).commit();
    }

    private void initFragment() {
        mManager = getSupportFragmentManager();
        mNewGongDan_fragment = new NewGongDan_Fragment();
        mYiWanChengGongDan_fragment = new YiWanChengGongDan_Fragment();

        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.gongdan_frame,mNewGongDan_fragment);
        transaction.add(R.id.gongdan_frame,mYiWanChengGongDan_fragment);
        transaction.commit();
    }


    @Override
    public Context context() {
        return null;
    }
}
