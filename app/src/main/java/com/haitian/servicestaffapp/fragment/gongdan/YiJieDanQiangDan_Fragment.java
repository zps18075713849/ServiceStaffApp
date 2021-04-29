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
import com.haitian.servicestaffapp.adapter.YiJieDan_Adapter;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.GongDanIngJuJue_Bean;
import com.haitian.servicestaffapp.bean.YiJieDanList_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YiJieDanQiangDan_Fragment extends BaseFragment {
    private PopupWindow mPopupWindow;
    private LinearLayout mLl;
    private ArrayList<YiJieDanList_Bean.DataBean> mlist = new ArrayList<>();
    private YiJieDan_Adapter mAdapter;
    private RecyclerView mRecy_id;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.yijiedan_qiangdan;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        Button zhuanchu_btn = view.findViewById(R.id.zhuanchu_btn);
        Button zhixing_btn = view.findViewById(R.id.zhixing_btn);
        mRecy_id = view.findViewById(R.id.recy_id);

        mLl = view.findViewById(R.id.ll);

        mRecy_id.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new YiJieDan_Adapter(getActivity(),mlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        zhuanchu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转出
                openPopupwindow(1);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            return;
        }else {
            requestListData();
        }
    }

    private void requestListData() {
        showWaitDialog();
        final Map<String, Object> map = new HashMap<>();
        map.put("user_id",DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));

        OkHttpUtil.getInteace().doPost(Constants.YIJIEDANGONGDANLIST, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("已接单列表失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("已接单列表成功："+json);
                Gson gson = new Gson();
                final YiJieDanList_Bean bean = gson.fromJson(json, YiJieDanList_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20040){
                            Toast.makeText(getContext(), "暂无已接单订单", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (bean.getCode() == 20041){
                            mlist.addAll(bean.getData());
                            mAdapter.notifyDataSetChanged();

                        }
                    }
                });
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        mAdapter.setOnClickItem(new YiJieDan_Adapter.onClickItem() {
            @Override
            public void onClick(int position, int type) {
                switch (type){
                    case 1:{
                        openPopupwindow(position);
                        break;
                    }
                    case 2:{
                        YiJieDanList_Bean.DataBean dataBean = mlist.get(position);
                        int id = dataBean.getId();
                        reqeustZhiXing(id);
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });
    }

    private void openPopupwindow(final int position) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.gongdanzhuanchu_popup, null);
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

        final EditText yuanyin_ed = inflate.findViewById(R.id.yuanyin_ed);
        final EditText zhuanruzhanghao_ed = inflate.findViewById(R.id.zhuanruzhanghao_ed);
        Button quxiao_btn = inflate.findViewById(R.id.quxiao_btn);
        Button right_btn = inflate.findViewById(R.id.right_btn);

        quxiao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yuanyin = yuanyin_ed.getText().toString().trim();
                String zhuanruzhanghao = zhuanruzhanghao_ed.getText().toString().trim();
//                int id = mlist.get(position).getId();

                if (zhuanruzhanghao.equals("")) {
                    Toast.makeText(getActivity(), "请输入转入账号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (yuanyin.equals("")) {
                    Toast.makeText(getActivity(), "请填写转出原因", Toast.LENGTH_SHORT).show();
                } else {
                    YiJieDanList_Bean.DataBean dataBean = mlist.get(position);
                    int id = dataBean.getId();
                    mPopupWindow.dismiss();
                    requestZhuanChu(id, yuanyin);
                }
            }
        });


    }

    //转出
    private void requestZhuanChu(int gongdanid, String yuanyin) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", DoctorBaseAppliction.spUtil.getString(Constants.USERID, ""));
        map.put("zhuanchuzhanghao", DoctorBaseAppliction.spUtil.getString(Constants.USER_NAME, ""));
        map.put("yuanyin", yuanyin);
        map.put("gongdanid", gongdanid);

        OkHttpUtil.getInteace().doPost(Constants.GONGDANZHUANDAN, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("工单转出失败：" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("工单转出成功：" + json);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        mlist.clear();
//                        requestListData();
                    }
                });

            }
        });

    }

    //执行
    private void reqeustZhiXing(int gongdanid){
        Map<String, Object> map = new HashMap<>();
        map.put("user_id",DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));
        map.put("id",gongdanid);

        OkHttpUtil.getInteace().doPost(Constants.ZHIXINGGONGDAN, map, getActivity(), new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                LogUtil.e("执行工单接口失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                LogUtil.e("执行工单接口成功："+json);
                Gson gson = new Gson();
                final GongDanIngJuJue_Bean bean = gson.fromJson(json, GongDanIngJuJue_Bean.class);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20011){
                            Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                            //刷新界面

                        }else if (bean.getCode() == 20010){
                            Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
