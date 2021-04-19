package com.haitian.servicestaffapp.fragment.gongdan;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import java.util.HashMap;
import java.util.Map;

public class YiJieDanQiangDan_Fragment extends BaseFragment {
    private PopupWindow mPopupWindow;
    private LinearLayout mLl;

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
        mLl = view.findViewById(R.id.ll);

        zhuanchu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //转出
                openPopupwindow(1);
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
                    mPopupWindow.dismiss();
                    requestZhuanChu(1, yuanyin);
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

}
