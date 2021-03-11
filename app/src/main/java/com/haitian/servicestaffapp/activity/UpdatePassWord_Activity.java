package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

public class UpdatePassWord_Activity extends BaseActivity {

    private ImageView mBack;
    private TextView mTitle_tv;
    private MyEdtext mOld_password_myed;
    private MyEdtext mNew_password_myed;
    private MyEdtext mNew_password_myed2;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_update_pass_word_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mTitle_tv = findViewById(R.id.title_tv);

        mBack.setVisibility(View.VISIBLE);
        mTitle_tv.setText("修改密码");
        mTitle_tv.setVisibility(View.VISIBLE);

        mOld_password_myed = findViewById(R.id.old_password_myed);
        mNew_password_myed = findViewById(R.id.new_password_myed);
        mNew_password_myed2 = findViewById(R.id.new_password_myed2);

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
