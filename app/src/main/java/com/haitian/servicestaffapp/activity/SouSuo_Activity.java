package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import org.w3c.dom.Text;

public class SouSuo_Activity extends BaseActivity {

    private ImageView mBack;
    private TextView mSousuo;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_sou_suo_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mSousuo = findViewById(R.id.sousuo_tv2);

        mBack.setVisibility(View.VISIBLE);
        mSousuo.setVisibility(View.VISIBLE);
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

        mSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        //这里处理逻辑
//                        name = keyword;
//                        pageNo = 1;
//                        mList.clear();
//                        requestDoctorList();

//                        Intent intent = new Intent(MainActivity.this, DoctorList_Activity.class);
//                        intent.putExtra("keyword",keyword);
//                        startActivity(intent);
                    }
                });
                searchFragment.showFragment(getSupportFragmentManager(),SearchFragment.TAG);
            }

        });
    }

    @Override
    public Context context() {
        return null;
    }
}
