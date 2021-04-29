package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.TongZhiList_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.TongZhiList_Bean;
import com.haitian.servicestaffapp.fragment.gonggao.TongZhiSetting_Fragment;
import com.haitian.servicestaffapp.fragment.gonggao.ZhengCeGuanLi_Fragment;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TongZhiGongGao_Activity extends BaseActivity {

    private TabLayout mTab_id;
    private ImageView mTitle_back;
    private TextView mSousuo_tv2;
    private SmartRefreshLayout mSmart_id;
    private RecyclerView mRecy_id;
    private FragmentManager mManager;
    private TongZhiSetting_Fragment mTongZhiSetting_fragment;
    private ZhengCeGuanLi_Fragment mZhengCeGuanLi_fragment;
    private FrameLayout mFrag_id;
    private ArrayList<TongZhiList_Bean.DataBean> mlist = new ArrayList<>();
    private TongZhiList_Adapter mAdapter;
    private String mSousuo_name = "";
    private int mPosition = 0;
    private TextView mTitle_content;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_tong_zhi_gong_gao_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTab_id = findViewById(R.id.tab_id);
        mTitle_back = findViewById(R.id.title_back);
        mSousuo_tv2 = findViewById(R.id.sousuo_tv2);
        mFrag_id = findViewById(R.id.frag_id);

        mTitle_back.setVisibility(View.VISIBLE);
        mSousuo_tv2.setVisibility(View.GONE);
        mTitle_content = findViewById(R.id.title_content);
        mTitle_content.setText("通知公告");
        mTitle_content.setVisibility(View.VISIBLE);

        mSmart_id = findViewById(R.id.smart_id);
        mRecy_id = findViewById(R.id.recy_id);

//        mTab_id.addTab(mTab_id.newTab().setText("全部"));
        mTab_id.addTab(mTab_id.newTab().setText("通知管理"));
        mTab_id.addTab(mTab_id.newTab().setText("政策管理"));

        mRecy_id.setLayoutManager(new LinearLayoutManager(TongZhiGongGao_Activity.this));
        mAdapter = new TongZhiList_Adapter(TongZhiGongGao_Activity.this, mlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTitle_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTab_id.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPosition = tab.getPosition();
                if (mPosition == 0) {
                    requestDataList(2, 1, mSousuo_name);
                } else if (mPosition == 1) {
                    requestDataList(2, 2, mSousuo_name);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mAdapter.setOnClickItem(new TongZhiList_Adapter.onClickItem() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(TongZhiGongGao_Activity.this, TongZhiInfo_Activity.class);
                intent.putExtra("id", mlist.get(position).getId());
                startActivity(intent);
            }
        });

        mSousuo_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        //这里处理逻辑
//                        mTab_id.getTabAt(0).select();
                        mlist.clear();
                        mSousuo_name = keyword;
//                        Toast.makeText(mContext, "搜索内容：" + keyword, Toast.LENGTH_SHORT).show();
                        requestDataList(2, mPosition + 1, keyword);
                        mSousuo_tv2.setText(keyword);
                    }
                });
                searchFragment.showFragment(getSupportFragmentManager(), SearchFragment.TAG);
            }

        });
    }

    @Override
    protected void initData() {
        super.initData();
        requestDataList(2, 1, "");
    }


    private void requestDataList(int shequid, int shequ_type, String sousuo) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("shequid", shequid);
        map.put("shequ_type", shequ_type);
        map.put("sousuo", sousuo);

        OkHttpUtil.getInteace().doPost(Constants.TONGZHILIST, map, TongZhiGongGao_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                mlist.clear();
                LogUtil.e("通知公告列表失败：" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("通知列表成功：" + json);
                mlist.clear();
                Gson gson = new Gson();
                final TongZhiList_Bean bean = gson.fromJson(json, TongZhiList_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041) {
                            hideWaitDialog();
                            mlist.addAll(bean.getData());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            hideWaitDialog();
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                });

            }
        });

    }

    @Override
    public Context context() {
        return null;
    }
}
