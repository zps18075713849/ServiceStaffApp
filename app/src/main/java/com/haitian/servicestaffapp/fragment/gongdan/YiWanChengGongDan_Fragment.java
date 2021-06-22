package com.haitian.servicestaffapp.fragment.gongdan;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.NewGongDan_Adapter;
import com.haitian.servicestaffapp.adapter.YiwanchangGongDan_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.CodeMessageBean;
import com.haitian.servicestaffapp.bean.GongDanYiWanCheng_Bean;
import com.haitian.servicestaffapp.bean.NewGongDan_Bean;
import com.haitian.servicestaffapp.bean.QiangdanListBean;
import com.haitian.servicestaffapp.bean.YiwanchangGongDanBean;
import com.haitian.servicestaffapp.okutils.DoctorNetService;
import com.haitian.servicestaffapp.okutils.NetWorkRequestInterface;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YiWanChengGongDan_Fragment extends BaseFragment {

    private RecyclerView mRecy_id;
    private ArrayList<GongDanYiWanCheng_Bean.DataBean> mQiangdanlist = new ArrayList<>();
    private YiwanchangGongDan_Adapter mAdapter;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.yiwanchenggongdan_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecy_id = view.findViewById(R.id.yiwancheng_recyc);

        mRecy_id.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new YiwanchangGongDan_Adapter(getActivity(), mQiangdanlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {
        super.initData();
//        requestYiWanChengList();
    }

    private void requestYiWanChengList() {
        showWaitDialog();
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5000, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().get().url("http://111.17.215.37:817/waiter/gongdan/yiwancheng?user_id=" + Integer.valueOf(DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""))).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                hideWaitDialog();
                LogUtil.e("失败：" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                hideWaitDialog();
                final String json = response.body().string();
                LogUtil.e("成功：" + json);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            GongDanYiWanCheng_Bean bean = gson.fromJson(json, GongDanYiWanCheng_Bean.class);
                            if (bean.getCode() == 20041) {
                                mQiangdanlist.addAll(bean.getData());
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mQiangdanlist.clear();
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        } else {
            requestYiWanChengList();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
    }


}
