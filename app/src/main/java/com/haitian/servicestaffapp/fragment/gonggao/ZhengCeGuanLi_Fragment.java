package com.haitian.servicestaffapp.fragment.gonggao;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseFragment;

public class ZhengCeGuanLi_Fragment extends BaseFragment {
    private RecyclerView mRecy_id;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.zhengce_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecy_id = view.findViewById(R.id.recy_id);

    }
}
