package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.KeHuGuanLi_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.KeHuGuanLi_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeHuGuanLi_Activity extends BaseActivity {

    private ImageView mBack;
    private TextView mSousuo_tv;
    private ImageView mTitle_right_riqi;
    private RecyclerView mRecy_id;
    private TabLayout mTab_id;
    private ArrayList<String> mlist = new ArrayList<>();
    private KeHuGuanLi_Adapter mAdapter;
    private TextView mTitle_content;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ke_hu_guan_li_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mSousuo_tv = findViewById(R.id.sousuo_tv);
        mTitle_right_riqi = findViewById(R.id.title_right_riqi);

        mBack.setVisibility(View.VISIBLE);
        mSousuo_tv.setVisibility(View.GONE);
        mTitle_right_riqi.setVisibility(View.GONE);

        mTitle_content = findViewById(R.id.title_content);
        mTitle_content.setVisibility(View.VISIBLE);
        mTitle_content.setText("客户管理");

        mTab_id = findViewById(R.id.tab_id);
        mRecy_id = findViewById(R.id.recy_id);

        mTab_id.addTab(mTab_id.newTab().setText("新客户"));
        mTab_id.addTab(mTab_id.newTab().setText("老客户"));

        mRecy_id.setLayoutManager(new LinearLayoutManager(KeHuGuanLi_Activity.this));
        mAdapter = new KeHuGuanLi_Adapter(KeHuGuanLi_Activity.this,mlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {
        super.initData();
        requestKeHuList("1");
    }
    //客户管理列表接口
    private void requestKeHuList(String i) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));
        map.put("biaoshi",i);

        OkHttpUtil.getInteace().doPost(Constants.KEHUGUANLILIST, map, KeHuGuanLi_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("客户管理失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("客户管理成功："+json);
                Gson gson = new Gson();
                final KeHuGuanLi_Bean bean = gson.fromJson(json, KeHuGuanLi_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                try {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (bean.getCode() == 20041){
                                if (bean.getData()!=null){
                                    List<String> data = bean.getData();
                                    for (int j = 0; j < data.size(); j++) {
                                        LogUtil.e(data.get(j));
                                    }
                                    mlist.addAll(data);
                                    mAdapter.notifyDataSetChanged();
                                }else {
                                    mlist.clear();
                                    mAdapter.notifyDataSetChanged();
                                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            }else {
                                mlist.clear();
                                mAdapter.notifyDataSetChanged();
                                Toast.makeText(mContext, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        mTab_id.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0){
                    mlist.clear();
                    requestKeHuList("1");
                }else {
                    mlist.clear();
                    requestKeHuList("2");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mAdapter.setOnClickItem(new KeHuGuanLi_Adapter.onClickItem() {
            @Override
            public void onClick(int position) {
                callPhone(mlist.get(position));
            }
        });

    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + phoneNum);
            intent.setData(data);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(mContext, "跳转拨号页失败！请手动拨打"+phoneNum, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public Context context() {
        return null;
    }
}
