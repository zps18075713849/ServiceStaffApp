package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

public class UpdateZiTiSize_Activity extends BaseActivity {

    private RelativeLayout mXiao_relative;
    private ImageView mXiao_dui;
    private RelativeLayout mBiaozhun_relative;
    private ImageView mBiaozhun_dui;
    private RelativeLayout mDa_relative;
    private ImageView mDa_dui;
    private RelativeLayout mTeda_relative;
    private ImageView mTeda_dui;
    private RelativeLayout mChaoda_relative;
    private ImageView mChaoda_dui;
    private Button mRight_btn;
    private ImageView mBack;
    private TextView mName;
    private float zitidaxiao;
    private float xiao = (float) 0.85;
    private float biaozhun = (float) 1;
    private float da = (float) 1.15;
    private float teda = (float) 1.2;
    private float chaoda = (float) 1.3;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_update_zi_ti_size_;
    }

    @Override
    protected void initViews() {
        super.initViews();

        //注冊eventbus
        EventBus.getDefault().register(this);

        mXiao_relative = findViewById(R.id.xiao_relative);
        mXiao_dui = findViewById(R.id.xiao_dui);
        mBiaozhun_relative = findViewById(R.id.biaozhun_relative);
        mBiaozhun_dui = findViewById(R.id.biaozhun_dui);
        mDa_relative = findViewById(R.id.da_relative);
        mDa_dui = findViewById(R.id.da_dui);
        mTeda_relative = findViewById(R.id.teda_relative);
        mTeda_dui = findViewById(R.id.teda_dui);
        mChaoda_relative = findViewById(R.id.chaoda_relative);
        mChaoda_dui = findViewById(R.id.chaoda_dui);

        mRight_btn = findViewById(R.id.right_btn);

        mBack = findViewById(R.id.title_back);
        mName = findViewById(R.id.title_tv);

        mBack.setVisibility(View.VISIBLE);
        mName.setVisibility(View.VISIBLE);
        mName.setText("字体设置");

        zitidaxiao = Float.parseFloat(DoctorBaseAppliction.spUtil2.getString(Constants.ZITISIZE, "1"));

        LogUtil.e(zitidaxiao + "");


        if (zitidaxiao == xiao) {
            mXiao_dui.setVisibility(View.VISIBLE);
            mBiaozhun_dui.setVisibility(View.GONE);
            mDa_dui.setVisibility(View.GONE);
            mTeda_dui.setVisibility(View.GONE);
            mChaoda_dui.setVisibility(View.GONE);
        } else if (zitidaxiao == biaozhun) {
            mXiao_dui.setVisibility(View.GONE);
            mBiaozhun_dui.setVisibility(View.VISIBLE);
            mDa_dui.setVisibility(View.GONE);
            mTeda_dui.setVisibility(View.GONE);
            mChaoda_dui.setVisibility(View.GONE);
        } else if (zitidaxiao == da) {
            mXiao_dui.setVisibility(View.GONE);
            mBiaozhun_dui.setVisibility(View.GONE);
            mDa_dui.setVisibility(View.VISIBLE);
            mTeda_dui.setVisibility(View.GONE);
            mChaoda_dui.setVisibility(View.GONE);
        } else if (zitidaxiao == teda) {
            mXiao_dui.setVisibility(View.GONE);
            mBiaozhun_dui.setVisibility(View.GONE);
            mDa_dui.setVisibility(View.GONE);
            mTeda_dui.setVisibility(View.VISIBLE);
            mChaoda_dui.setVisibility(View.GONE);
        } else if (zitidaxiao == chaoda) {
            mXiao_dui.setVisibility(View.GONE);
            mBiaozhun_dui.setVisibility(View.GONE);
            mDa_dui.setVisibility(View.GONE);
            mTeda_dui.setVisibility(View.GONE);
            mChaoda_dui.setVisibility(View.VISIBLE);
        }

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

        mXiao_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zitidaxiao = (float) 0.85;

                mXiao_dui.setVisibility(View.VISIBLE);
                mBiaozhun_dui.setVisibility(View.GONE);
                mDa_dui.setVisibility(View.GONE);
                mTeda_dui.setVisibility(View.GONE);
                mChaoda_dui.setVisibility(View.GONE);

            }
        });


        mBiaozhun_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zitidaxiao = (float) 1;

                mXiao_dui.setVisibility(View.GONE);
                mBiaozhun_dui.setVisibility(View.VISIBLE);
                mDa_dui.setVisibility(View.GONE);
                mTeda_dui.setVisibility(View.GONE);
                mChaoda_dui.setVisibility(View.GONE);

            }
        });

        mDa_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zitidaxiao = (float) 1.15;

                mXiao_dui.setVisibility(View.GONE);
                mBiaozhun_dui.setVisibility(View.GONE);
                mDa_dui.setVisibility(View.VISIBLE);
                mTeda_dui.setVisibility(View.GONE);
                mChaoda_dui.setVisibility(View.GONE);

            }
        });

        mTeda_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zitidaxiao = (float) 1.2;

                mXiao_dui.setVisibility(View.GONE);
                mBiaozhun_dui.setVisibility(View.GONE);
                mDa_dui.setVisibility(View.GONE);
                mTeda_dui.setVisibility(View.VISIBLE);
                mChaoda_dui.setVisibility(View.GONE);

            }
        });

        mChaoda_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zitidaxiao = (float) 1.3;

                mXiao_dui.setVisibility(View.GONE);
                mBiaozhun_dui.setVisibility(View.GONE);
                mDa_dui.setVisibility(View.GONE);
                mTeda_dui.setVisibility(View.GONE);
                mChaoda_dui.setVisibility(View.VISIBLE);

            }
        });

        mRight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float value = (float) zitidaxiao;
                DoctorBaseAppliction.setFontSize(value);
                DoctorBaseAppliction.spUtil2.putString(Constants.ZITISIZE, zitidaxiao + "");

                EventBus.getDefault().post("字体更改");

                finish();

//                recreate();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String event) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus取消注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Context context() {
        return null;
    }
}
