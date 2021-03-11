package com.haitian.servicestaffapp.fragment.jiesuanye;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.activity.TiXian_Activity;
import com.haitian.servicestaffapp.base.BaseFragment;

public class ZhangHu_Fragment extends BaseFragment {

    private Button mDuihuan_btn;
    private Button mTixian_btn;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.zhanghu_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        //兑换
        mDuihuan_btn = view.findViewById(R.id.duihuan_btn);
        //提现
        mTixian_btn = view.findViewById(R.id.tixian_btn);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mTixian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TiXian_Activity.class);
                startActivity(intent);
            }
        });
    }
}
