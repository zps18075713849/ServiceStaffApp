package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.fragment.Home_Fragment;
import com.haitian.servicestaffapp.fragment.Setting_Fragment;
import com.haitian.servicestaffapp.utils.FlashHelper;
import com.haitian.servicestaffapp.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    private FrameLayout mMain_fragid;
    private RadioGroup mMain_radiogroup;
    private RadioButton mRb_homepage;
    private RadioButton mRb_setting;
    private TextView mRb_sousuo;
    private FragmentManager mManager;
    private Home_Fragment mHomeFragment;
    private Setting_Fragment mSettingFragment;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //EventBus注册
        EventBus.getDefault().register(this);

        mMain_fragid = findViewById(R.id.main_fragid);

        mMain_radiogroup = findViewById(R.id.main_radiogroup);
        mRb_homepage = findViewById(R.id.rb_homepage);
        mRb_sousuo = findViewById(R.id.rb_sousuo);
        mRb_setting = findViewById(R.id.rb_setting);

        //初始化碎片（fragment）
        initfragment();
        //第一个显示的界面
        ShowFirstFragment();
    }


    private void initfragment() {
        mManager = getSupportFragmentManager();

        mHomeFragment = new Home_Fragment();
        mSettingFragment = new Setting_Fragment();

        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.main_fragid, mHomeFragment);
        transaction.add(R.id.main_fragid, mSettingFragment);
        transaction.commit();
    }

    private void ShowFirstFragment() {
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.show(mHomeFragment).hide(mSettingFragment);
        transaction.commit();
    }

    @Override
    protected void initListener() {
        super.initListener();

        mMain_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_homepage: {
                        FragmentTransaction transaction = mManager.beginTransaction();
                        transaction.show(mHomeFragment).hide(mSettingFragment);
                        transaction.commit();
                        break;
                    }
                    case R.id.rb_setting: {
                        FragmentTransaction transaction = mManager.beginTransaction();
                        transaction.show(mSettingFragment).hide(mHomeFragment);
                        transaction.commit();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });


        //搜索界面
        mRb_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SouSuo_Activity.class);
                startActivity(intent);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(String str) {
        if (str.equals("字体更改")) {
            LogUtil.e("变了");
            recreate();
        } else {
            LogUtil.e("字体没变");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Context context() {
        return null;
    }
}
