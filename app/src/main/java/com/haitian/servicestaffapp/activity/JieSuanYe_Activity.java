package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.base.BaseActivity2;
import com.haitian.servicestaffapp.fragment.jiesuanye.ChuZhang_Fragment;
import com.haitian.servicestaffapp.fragment.jiesuanye.JinZhang_Fragment;
import com.haitian.servicestaffapp.fragment.jiesuanye.ZhangHu_Fragment;

import org.w3c.dom.Text;

public class JieSuanYe_Activity extends BaseActivity2 {

    private ImageView mBack;
    private TextView mSousuo2;
    private TabLayout mTab_id;
    private FrameLayout mFrame_id;
    private FragmentManager mManager;
    private ChuZhang_Fragment mChuZhang_fragment;
    private JinZhang_Fragment mJinZhang_fragment;
    private ZhangHu_Fragment mZhangHu_fragment;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_jie_suan_ye_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mSousuo2 = findViewById(R.id.sousuo_tv2);

        mBack.setVisibility(View.VISIBLE);
        mSousuo2.setVisibility(View.VISIBLE);

        mTab_id = findViewById(R.id.tab_id);
        mTab_id.addTab(mTab_id.newTab().setText("账户"));
        mTab_id.addTab(mTab_id.newTab().setText("进账"));
        mTab_id.addTab(mTab_id.newTab().setText("出账"));

        mFrame_id = findViewById(R.id.frame_id);



        //初始化碎片
        initFragment();

        //第一个展示界面
        ShowFirstFragment();

    }

    //展示第一个碎片
    private void ShowFirstFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.show(mZhangHu_fragment).hide(mJinZhang_fragment).hide(mChuZhang_fragment).commit();
    }

    //碎片初始化
    private void initFragment() {
        mManager = getSupportFragmentManager();

        mChuZhang_fragment = new ChuZhang_Fragment();
        mJinZhang_fragment = new JinZhang_Fragment();
        mZhangHu_fragment = new ZhangHu_Fragment();

        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.frame_id,mChuZhang_fragment);
        transaction.add(R.id.frame_id,mJinZhang_fragment);
        transaction.add(R.id.frame_id,mZhangHu_fragment);
        transaction.commit();
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

        mTab_id.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0){
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.show(mZhangHu_fragment).hide(mJinZhang_fragment).hide(mChuZhang_fragment).commit();
                }else if (position == 1){
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.show(mJinZhang_fragment).hide(mZhangHu_fragment).hide(mChuZhang_fragment).commit();
                }else if (position == 2){
                    FragmentTransaction transaction = mManager.beginTransaction();
                    transaction.show(mChuZhang_fragment).hide(mJinZhang_fragment).hide(mZhangHu_fragment).commit();
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

    @Override
    public Context context() {
        return null;
    }
}
