package com.haitian.servicestaffapp.fragment.gongdan;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.NewGongDan_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.NewGongDan_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewGongDan_Fragment extends BaseFragment {

    private RecyclerView mRecy_id;
    private ArrayList<NewGongDan_Bean.DataBean> mlist = new ArrayList<>();
    private NewGongDan_Adapter mAdapter;
    private PopupWindow mPopupWindow;


    private LinearLayout mLl;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.newgongdan_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecy_id = view.findViewById(R.id.recy_id);
        mLl = view.findViewById(R.id.ll);
//
//        mRecy_id.setLayoutManager(new LinearLayoutManager(getContext()));
//        mAdapter = new NewGongDan_Adapter(getActivity(), mlist);
//        mRecy_id.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();


    }

    @Override
    protected void initData() {
        super.initData();
        requestListData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        } else {
            requestListData();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnClickItem(new NewGongDan_Adapter.onClickItem() {
            @Override
            public void onClick(int position, int type) {
                switch (type) {
                    case 0: {

                        break;
                    }
                    case 1: {
                        //??????
                        requestJieDan(position);
                        break;
                    }
                }
            }
        });


    }


    //??????
    private void requestJieDan(int position) {
        showWaitDialog();
        int id = mlist.get(position).getId();
        String userid = DoctorBaseAppliction.spUtil.getString(Constants.USERID, "");
        Integer user_id = Integer.valueOf(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("gongdanid", id);


        OkHttpUtil.getInteace().doPost(Constants.GONGDANJIEDAN, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                LogUtil.e("???????????????" + e.getMessage());
                hideWaitDialog();
            }

            @Override
            public void onResponse(String json) {
                LogUtil.e("???????????????" + json);
                Gson gson = new Gson();
                final NewGongDan_Bean bean = gson.fromJson(json, NewGongDan_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20011) {
                            hideWaitDialog();
                            Toast.makeText(getContext(), bean.getMessage() + "", Toast.LENGTH_SHORT).show();
                            mlist.clear();
                            requestListData();
                        } else if (bean.getCode() == 20010) {
                            Toast.makeText(getContext(), "????????????", Toast.LENGTH_SHORT).show();
                            hideWaitDialog();
                        }
                    }
                });
            }
        });
    }


    private void requestListData() {
        showWaitDialog();
        String userid = DoctorBaseAppliction.spUtil.getString(Constants.USERID, "");
        Integer id = Integer.valueOf(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);

        OkHttpUtil.getInteace().doPost(Constants.NEWXINGONGDAN, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("??????????????????" + e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("??????????????????" + json);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            final NewGongDan_Bean bean = gson.fromJson(json, NewGongDan_Bean.class);
                            if (bean.getCode() == 20041) {
                                mlist.clear();
                                mlist.addAll(bean.getData());
                                mAdapter.notifyDataSetChanged();
                            }else {
                                mlist.clear();
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
}
