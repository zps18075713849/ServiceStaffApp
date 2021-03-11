package com.haitian.servicestaffapp.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;


public class TestPopupWindow extends PopupWindow {

    public final TextView mAll_tv;
    public final TextView daijieshou_tv;
    public final TextView yijieshou_tv;
    public final TextView yijujue_tv;

    public TestPopupWindow(Context context) {
        super(context);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_test,
                null, false);
        setContentView(contentView);

        mAll_tv = contentView.findViewById(R.id.all_tv);
        daijieshou_tv = contentView.findViewById(R.id.daijieshou_tv);
        yijieshou_tv = contentView.findViewById(R.id.yijieshou_tv);
        yijujue_tv = contentView.findViewById(R.id.yijujue_tv);




    }
}