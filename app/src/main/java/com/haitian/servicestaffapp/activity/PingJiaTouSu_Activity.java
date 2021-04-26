package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.Bean;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.NewGongDan_Adapter;
import com.haitian.servicestaffapp.adapter.PingjiaList_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.NewGongDan_Bean;
import com.haitian.servicestaffapp.bean.PingJiaListBean;
import com.haitian.servicestaffapp.bean.QiangdanListBean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.haitian.servicestaffapp.app.DoctorBaseAppliction.getContext;

public class PingJiaTouSu_Activity extends BaseActivity {

    private ImageView mBack;
    private TextView mSousuo_tv;
    private TextView mTitle_right_tv;
    private TabLayout mTab_id;
    private SmartRefreshLayout mSmart_id;
    private RecyclerView mRecy_id;
    private PingjiaList_Adapter mAdapter;
    private ArrayList<PingJiaListBean.DataBean> mlist = new ArrayList<>();

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ping_jia_tou_su_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mSousuo_tv = findViewById(R.id.sousuo_tv);
        mTitle_right_tv = findViewById(R.id.title_right_tv);

        mBack.setVisibility(View.VISIBLE);
        mSousuo_tv.setVisibility(View.VISIBLE);
        mTitle_right_tv.setVisibility(View.VISIBLE);
        mTitle_right_tv.setText("投诉");

        mTab_id = findViewById(R.id.tab_id);
        mSmart_id = findViewById(R.id.smart_id);
        mRecy_id = findViewById(R.id.recy_id);

        mRecy_id.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PingjiaList_Adapter(PingJiaTouSu_Activity.this,mlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        Bean.DataBean bean = new Bean.DataBean("2",5);
        LogUtil.e("---------"+bean.get_$1()+"-  "+bean.get_$5());
        requestListData();
    }

    //抢单list
    private void requestListData() {
        showWaitDialog();
        String userid = DoctorBaseAppliction.spUtil.getString(Constants.USERID, "");
        Integer id = Integer.valueOf(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id",id);

        OkHttpUtil.getInteace().doPost(Constants.PINGJIA, map, PingJiaTouSu_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("新工单失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("新工单成功："+json);
                Gson gson = new Gson();
                final PingJiaListBean bean = gson.fromJson(json, PingJiaListBean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041){
                            try {
                                mlist.addAll(bean.getData());
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
