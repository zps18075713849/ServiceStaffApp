package com.haitian.servicestaffapp.fragment.gongdan;

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
import com.haitian.servicestaffapp.base.BaseFragment;

public class JinXingZhongQiangDan_Fragment extends BaseFragment {
    private PopupWindow mPopupWindow;
    private LinearLayout mLl;

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
        Button jujue_btn = view.findViewById(R.id.jujue_btn);
        mLl = view.findViewById(R.id.ll);



        jujue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopup();
            }
        });
    }

    private void openPopup() {
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
                    mPopupWindow.dismiss();
                }
            }
        });
    }
}
