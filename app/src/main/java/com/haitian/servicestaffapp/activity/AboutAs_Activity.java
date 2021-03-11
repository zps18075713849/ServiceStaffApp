package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

public class AboutAs_Activity extends BaseActivity {

    private TextView mTitle_tv;
    private ImageView mBack;
    private TextView mQuanguomianfeidianhua_tv;
    private TextView mKefu_tv;
    private MyEdtext mYijian_ed;
    private Button mRight_btn;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_about_as_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTitle_tv = findViewById(R.id.title_tv);
        mBack = findViewById(R.id.title_back);

        mTitle_tv.setVisibility(View.VISIBLE);
        mBack.setVisibility(View.VISIBLE);
        mTitle_tv.setText("联系我们");

        //全国免费电话
        mQuanguomianfeidianhua_tv = findViewById(R.id.quanguomianfeidianhua_tv);
        //客服电话
        mKefu_tv = findViewById(R.id.kefurexiandianhua_tv);
        //意见反馈
        mYijian_ed = findViewById(R.id.yijian_ed);
        //提交
        mRight_btn = findViewById(R.id.right_btn);

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
