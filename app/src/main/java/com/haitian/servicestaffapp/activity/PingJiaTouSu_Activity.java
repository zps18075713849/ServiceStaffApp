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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
import com.haitian.servicestaffapp.bean.TouSuHuiFu_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;
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
    private RecyclerView mRecy_id;
    private PingjiaList_Adapter mAdapter;
    private ArrayList<PingJiaListBean.DataBean> mlist = new ArrayList<>();
    private TextView mTitle_content;
    private PopupWindow mPopupWindow;
    private LinearLayout mLl;

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
        mTitle_content = findViewById(R.id.title_content);
        mTitle_content.setText("投诉评价");
        mTitle_content.setVisibility(View.VISIBLE);

        mBack.setVisibility(View.VISIBLE);
        mSousuo_tv.setVisibility(View.GONE);
        mTitle_right_tv.setVisibility(View.GONE);
        mTitle_right_tv.setText("投诉");

        mTab_id = findViewById(R.id.tab_id);
        mRecy_id = findViewById(R.id.recy_id);

        mLl = findViewById(R.id.ll);


        mRecy_id.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new PingjiaList_Adapter(PingJiaTouSu_Activity.this, mlist);
        mRecy_id.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        requestListData();
    }

    //抢单list
    private void requestListData() {
        showWaitDialog();
        String userid = DoctorBaseAppliction.spUtil.getString(Constants.USERID, "");
        Integer id = Integer.valueOf(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);

        OkHttpUtil.getInteace().doPost(Constants.PINGJIA, map, PingJiaTouSu_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("评价列表：" + e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("评价列表成功：" + json);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            final PingJiaListBean bean = gson.fromJson(json, PingJiaListBean.class);
                            if (bean.getCode() == 20041) {
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

    @Override
    protected void initListener() {
        super.initListener();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.setOnClickItem(new PingjiaList_Adapter.onClickItem() {
            @Override
            public void onClick(int position, int type) {
                switch (type){
                    case 1:{
                        showHuiFuPopupWindow(mlist.get(position).getEvaluate().getId());
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });
    }

    private void requestHuiFu(String id,String content) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_id",Integer.valueOf(DoctorBaseAppliction.spUtil.getString(Constants.USERID,"")));
        map.put("gongdan_id",id);
        map.put("huifu",content);

        OkHttpUtil.getInteace().doPost(Constants.PINGJIAHUIFU, map, PingJiaTouSu_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("评价回复接口失败："+e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                hideWaitDialog();
                LogUtil.e("评价回复接口成功："+json);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Gson gson = new Gson();
                            TouSuHuiFu_Bean bean = gson.fromJson(json, TouSuHuiFu_Bean.class);
                            if (bean.getCode() == 20011){
                                Toast.makeText(mContext, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
                                mlist.clear();
                                requestListData();
                                return;
                            }else {
                                Toast.makeText(mContext, bean.getMessage()+"", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JsonSyntaxException e) {
                            Toast.makeText(mContext, "数据源错误，请测试真实数据", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }
    //弹出框
    private void showHuiFuPopupWindow(final String id) {
        View inflate = LayoutInflater.from(PingJiaTouSu_Activity.this).inflate(R.layout.huifupingjia_popup, null);
        mPopupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAtLocation(mLl, Gravity.CENTER,0,0);

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        final MyEdtext content_ed = inflate.findViewById(R.id.content);
        final TextView cancel = inflate.findViewById(R.id.cancel);
        TextView submit = inflate.findViewById(R.id.submit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = content_ed.getText().toString().trim();
                if (content.equals("")){
                    Toast.makeText(mContext, "请输入回复内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPopupWindow.dismiss();
                requestHuiFu(id,content);
            }
        });

    }

    @Override
    public Context context() {
        return null;
    }
}
