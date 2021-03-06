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
import com.google.gson.JsonSyntaxException;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.adapter.NewGongDan_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.GongDanIngJuJue_Bean;
import com.haitian.servicestaffapp.bean.GongDanJinXingZhong_Bean;
import com.haitian.servicestaffapp.bean.NewGongDan_Bean;
import com.haitian.servicestaffapp.bean.TouSuHuiFu_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JinXingZhongQiangDan_Fragment extends BaseFragment {
    private PopupWindow mPopupWindow;
    private LinearLayout mLl;
    private RecyclerView mRecy_id;
    private ArrayList<GongDanJinXingZhong_Bean.DataBean> mlist = new ArrayList<>();
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
        mAdapter = new NewGongDan_Adapter(getActivity(), mlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {
        super.initData();
        requestListData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {   // ????????????????????? ??????????????????onPause();
            return;
        } else {  // ?????????????????? ??????????????????onResume();
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
                        openPopup(position);
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
                if (jujue.equals("")) {
                    Toast.makeText(getActivity(), "?????????????????????", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    requestJuJue(position, jujue);
                    mPopupWindow.dismiss();
                }
            }
        });
    }

    //??????
    private void requestJuJue(int position, String jujue) {
        showWaitDialog();
        String id = mlist.get(position).getId();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""));
        map.put("jujueyuanyintext", jujue);
        map.put("id", id + "");

        OkHttpUtil.getInteace().doPost(Constants.GONGDANINGJUJUE, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("????????????????????????????????????" + e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("????????????????????????????????????" + json);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            final GongDanIngJuJue_Bean bean = gson.fromJson(json, GongDanIngJuJue_Bean.class);
                            if (bean.getCode() == 20011) {
                                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                                mlist.clear();
                                requestListData();
                            } else {
                                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    //??????
    private void requestJieDan(int position) {
        showWaitDialog();
        String id = mlist.get(position).getId();
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
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("???????????????" + json);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            final TouSuHuiFu_Bean bean = gson.fromJson(json, TouSuHuiFu_Bean.class);
                            if (bean.getCode() == 20011) {
                                hideWaitDialog();
                                Toast.makeText(getContext(), bean.getMessage() + "", Toast.LENGTH_SHORT).show();
                                mlist.clear();
                                requestListData();
                            } else if (bean.getCode() == 20010) {
                                Toast.makeText(getContext(), "????????????", Toast.LENGTH_SHORT).show();
                                hideWaitDialog();
                            }else {
                                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
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

        OkHttpUtil.getInteace().doPost(Constants.GONGDANING, map, getActivity(), new OkHttpUtil.OkCallBack() {
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
                            final GongDanJinXingZhong_Bean bean = gson.fromJson(json, GongDanJinXingZhong_Bean.class);
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
