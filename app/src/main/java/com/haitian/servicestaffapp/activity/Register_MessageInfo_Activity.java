package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.utils.HcUtils;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Register_MessageInfo_Activity extends BaseActivity {


    private ImageView mBack_img;
    private MyEdtext mName_ed;
    private MyEdtext mMobile_ed;
    private Button mNext_btn;
    private TextView mFuwutype_tv;
    private TextView mFuwusuoshu_tv;
    private List<String> mCardItem;
    private OptionsPickerView pvOptions;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register__message_info_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack_img = findViewById(R.id.back_img);
        mName_ed = findViewById(R.id.name_ed);
        mMobile_ed = findViewById(R.id.mobile_ed);

        mFuwutype_tv = findViewById(R.id.fuwutype_tv);
        mFuwusuoshu_tv = findViewById(R.id.fuwusuoshu_tv);


        mNext_btn = findViewById(R.id.next_btn);
    }

    @Override
    protected void initData() {
        super.initData();
        mCardItem = new ArrayList<>();

    }

    @Override
    protected void initListener() {
        super.initListener();

        mBack_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_MessageInfo_Activity.this, Register_Upload_CardId_Activity.class);
                startActivity(intent);
            }
        });

        mFuwutype_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HcUtils.hideKeyboard(Register_MessageInfo_Activity.this);
                initOptionPickerSex(mFuwutype_tv);
                requestYiHun();
            }
        });

        mFuwusuoshu_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HcUtils.hideKeyboard(Register_MessageInfo_Activity.this);
                initOptionPickerSex(mFuwusuoshu_tv);
                requestYiHun();
            }
        });

    }

    //婚姻状况
    private void requestYiHun() {
        mCardItem.clear();
        mCardItem.add("已婚");
        mCardItem.add("未婚");
        pvOptions.setPicker(mCardItem);
        pvOptions.show();
    }

    private void initOptionPickerSex(final TextView view) {//条件选择器初始化
        //设置滚轮文字大小
        //默认选中项
        //设置两横线之间的间隔倍数
        //设置外部遮罩颜色
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = mCardItem.get(options1);
                view.setText(tx);
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCancelColor(getResources().getColor(R.color.liji_c_blue))
                .setSubmitColor(getResources().getColor(R.color.liji_c_blue))
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }

    @Override
    public Context context() {
        return null;
    }
}
