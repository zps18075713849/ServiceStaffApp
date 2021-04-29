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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.NewGongDan_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.GongDanIngJuJue_Bean;
import com.haitian.servicestaffapp.bean.NewGongDan_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JinXingZhongQiangDan_Fragment extends BaseFragment {
    private PopupWindow mPopupWindow;
    private LinearLayout mLl;
    private RecyclerView mRecy_id;
    private ArrayList<NewGongDan_Bean.DataBean> mlist = new ArrayList<>();
    private NewGongDan_Adapter mAdapter;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.jinxingzhong_qiangdan;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mLl = view.findViewById(R.id.ll);
        mRecy_id = view.findViewById(R.id.recy_id);

        mRecy_id.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewGongDan_Adapter(getActivity(),mlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {   // 不在最前端显示 相当于调用了onPause();
            return;
        }else{  // 在最前端显示 相当于调用了onResume();
            requestListData();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnClickItem(new NewGongDan_Adapter.onClickItem() {
            @Override
            public void onClick(int position, int type) {
                switch (type){
                    case 0:{
                        openPopup(position);
                        break;
                    }
                    case 1:{
                        //接单
                        requestJieDan(position);
                        break;
                    }
                }
            }
        });
    }

    private void openPopup(final int position) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.gongdanjudan_popup, null);
        mPopupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);

        mPopupWindow.showAtLocation(mLl, Gravity.CENTER, 0, 0);

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        Button quxiao_btn = inflate.findViewById(R.id.quxiao_btn);
        Button right_btn = inflate.findViewById(R.id.right_btn);
        final EditText jujue_ed = inflate.findViewById(R.id.jujue_ed);

        quxiao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jujue = jujue_ed.getText().toString().trim();
                if (jujue.equals("")){
                    Toast.makeText(getActivity(), "请输入拒单原因", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    requestJuJue(position,jujue);
                    mPopupWindow.dismiss();
                }
            }
        });
    }

    //拒单
    private void requestJuJue(int position,String jujue) {
        showWaitDialog();
        int id = mlist.get(position).getId();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id",DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));
        map.put("jujueyuanyintext",jujue);
        map.put("id",id+"");

        OkHttpUtil.getInteace().doPost(Constants.GONGDANINGJUJUE, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("进行中工单拒绝接口失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("进行中工单拒绝接口成功："+json);
                Gson gson = new Gson();
                final GongDanIngJuJue_Bean bean = gson.fromJson(json, GongDanIngJuJue_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20011){
                            Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                            mlist.clear();
                            requestListData();
                        }else {
                            Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //接单
    private void requestJieDan(int position) {
        showWaitDialog();
        int id = mlist.get(position).getId();
        String userid = DoctorBaseAppliction.spUtil.getString(Constants.USERID, "");
        Integer user_id = Integer.valueOf(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("gongdanid",id);

        OkHttpUtil.getInteace().doPost(Constants.GONGDANJIEDAN, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                LogUtil.e("接单失败："+e.getMessage());
                hideWaitDialog();
            }

            @Override
            public void onResponse(String json) {
                LogUtil.e("接单成功："+json);
                Gson gson = new Gson();
                final NewGongDan_Bean bean = gson.fromJson(json, NewGongDan_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20011){
                            hideWaitDialog();
                            Toast.makeText(getContext(), bean.getMessage()+"", Toast.LENGTH_SHORT).show();
                            mlist.clear();
                            requestListData();
                        }else if (bean.getCode() == 20010){
                            Toast.makeText(getContext(), "接单失败", Toast.LENGTH_SHORT).show();
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
        map.put("user_id",id);

        OkHttpUtil.getInteace().doPost(Constants.NEWXINGONGDAN, map, getActivity(), new OkHttpUtil.OkCallBack() {
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
                final NewGongDan_Bean bean = gson.fromJson(json, NewGongDan_Bean.class);
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



}
