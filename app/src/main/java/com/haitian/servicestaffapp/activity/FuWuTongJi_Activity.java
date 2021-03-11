package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.base.BaseActivity2;
import com.haitian.servicestaffapp.utils.DateUtils;
import com.haitian.servicestaffapp.utils.HcUtils;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import static com.haitian.servicestaffapp.utils.DateUtils.FORMAT_5;
import static com.haitian.servicestaffapp.utils.DateUtils.getDayTime;
import static com.haitian.servicestaffapp.utils.DateUtils.getMonthTime;
import static com.haitian.servicestaffapp.utils.DateUtils.getYearTime;


public class FuWuTongJi_Activity extends BaseActivity2 {

    private ImageView mBack;
    private TextView mTitle_tv;
    private TextView mStarttime_tv;
    private TextView mEndtime_tv;
    private RadioGroup mRadiogroup;
    private RadioButton mQuanbu_rb;
    private RadioButton mShangzhou_rb;
    private RadioButton mShangyue_rb;
    private RadioButton mShangjidu_rb;
    private RadioButton mShangbannian_rb;
    private TextView mGongdan_count;
    private TextView mGongdan_tv;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
//        Resources res = super.getResources();
//        Configuration config = new Configuration();
//        config.setToDefaults();
//        res.updateConfiguration(config, res.getDisplayMetrics());
        return R.layout.activity_fu_wu_tong_ji_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mTitle_tv = findViewById(R.id.title_tv);

        mBack.setVisibility(View.VISIBLE);
        mTitle_tv.setText("服务统计");
        mTitle_tv.setVisibility(View.VISIBLE);

        mStarttime_tv = findViewById(R.id.starttime_tv);
        mEndtime_tv = findViewById(R.id.endtime_tv);

        mRadiogroup = findViewById(R.id.radiogroup);
        //全部
        mQuanbu_rb = findViewById(R.id.quanbu_rb);
        //上周
        mShangzhou_rb = findViewById(R.id.shangzhou_rb);
        //上月
        mShangyue_rb = findViewById(R.id.shangyue_rb);
        //上季度
        mShangjidu_rb = findViewById(R.id.shangjidu_rb);
        //上半年
        mShangbannian_rb = findViewById(R.id.shangbannian_rb);


        mGongdan_count = findViewById(R.id.gongdan_count);
        mGongdan_tv = findViewById(R.id.gongdan_tv);
//        mGongdan_count.setTextSize(28);
//        mGongdan_tv.setTextSize(28);

        initTimePicker();
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

        mStarttime_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HcUtils.hideKeyboard(FuWuTongJi_Activity.this);
                pvTime.show(mStarttime_tv);
            }
        });

        mEndtime_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HcUtils.hideKeyboard(FuWuTongJi_Activity.this);
                pvTime.show(mEndtime_tv);
            }
        });


        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.quanbu_rb: {
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.white));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        break;
                    }
                    case R.id.shangzhou_rb: {
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        break;
                    }

                    case R.id.shangyue_rb:{
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        break;
                    }

                    case R.id.shangjidu_rb:{
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        break;
                    }

                    case R.id.shangbannian_rb:{
                        mQuanbu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));
                        mQuanbu_rb.setTextColor(getResources().getColor(R.color.hui_1));

                        mShangzhou_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangzhou_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangyue_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangyue_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangjidu_rb.setTextColor(getResources().getColor(R.color.hui_1));
                        mShangjidu_rb.setBackground(getResources().getDrawable(R.drawable.white_bg));

                        mShangbannian_rb.setTextColor(getResources().getColor(R.color.white));
                        mShangbannian_rb.setBackground(getResources().getDrawable(R.drawable.blue_bg));
                        break;
                    }
                }
            }
        });


    }


    /**
     * 初始化时间弹框
     */
    private void initTimePicker() {
        String yearTime = getYearTime(new Date(System.currentTimeMillis()));
        String monthTime = getMonthTime(new Date(System.currentTimeMillis()));
        String dayTime = getDayTime(new Date(System.currentTimeMillis()));
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(Integer.parseInt(yearTime), Integer.parseInt(monthTime) - 1, Integer
                .parseInt(dayTime));
        pvTime = new TimePickerView.Builder(this, new TimePickerView
                .OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                String found_date = DateUtils.dateToString(date, FORMAT_5);
                ((TextView) v).setText(found_date);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setCancelColor(getResources().getColor(R.color.main_color))
                .setSubmitColor(getResources().getColor(R.color.main_color))
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    @Override
    public Context context() {
        return null;
    }
}
