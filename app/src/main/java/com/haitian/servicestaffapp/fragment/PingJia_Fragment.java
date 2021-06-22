package com.haitian.servicestaffapp.fragment;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.YiwanchangGongDan_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.YiwanchangGongDanBean;
import com.haitian.servicestaffapp.okutils.DoctorNetService;
import com.haitian.servicestaffapp.okutils.NetWorkRequestInterface;
import com.haitian.servicestaffapp.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PingJia_Fragment extends BaseFragment {

    private RecyclerView mRecy_id;
    private ArrayList<YiwanchangGongDanBean.DataBean> mQiangdanlist = new ArrayList<>();
    private YiwanchangGongDan_Adapter mAdapter;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.tousu_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecy_id = view.findViewById(R.id.recy_id);

        mRecy_id.setLayoutManager(new LinearLayoutManager(getContext()));
//        mAdapter = new YiwanchangGongDan_Adapter(getActivity(),mQiangdanlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        super.initData();
        requestQiangDanListData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    //抢单list
    private void requestQiangDanListData() {
        showWaitDialog();
        String userid = DoctorBaseAppliction.spUtil.getString(Constants.USERID, "");
        Integer id = Integer.valueOf(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id",id);
        DoctorNetService.requestYiWanChanggongdan(Constants.YIWANCHANGGONGDAN, map, new NetWorkRequestInterface() {
            @Override
            public void onError(Throwable throwable) {
                hideWaitDialog();
                LogUtil.e("新工单失败："+throwable.getMessage());
            }

            @Override
            public void onNext(Object info) {
                hideWaitDialog();
                final YiwanchangGongDanBean bean = (YiwanchangGongDanBean) info;
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041){
                            try {
                                mQiangdanlist.addAll(bean.getData());
                                mAdapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

}
