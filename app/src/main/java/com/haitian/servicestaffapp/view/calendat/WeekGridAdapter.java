package com.haitian.servicestaffapp.view.calendat;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeekGridAdapter extends BaseAdapter {

    final String[] titles = new String[] { "日", "一", "二", "三", "四", "五", "六" };
    private Context mContext;

    public WeekGridAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView week = new TextView(mContext);
        ViewGroup.LayoutParams week_params = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        week.setLayoutParams(week_params);
        week.setPadding(0, 10, 0, 0);
        week.setGravity(Gravity.CENTER);
        week.setFocusable(false);
        week.setBackgroundColor(Color.TRANSPARENT);

        if (position == 6) { // 周六
//            week.setBackgroundColor(Color.argb(0xff, 0x52, 0x9b, 0xd0));
            week.setTextColor(Color.BLACK);
        } else if (position == 0) { // 周日
//            week.setBackgroundColor(Color.argb(0xff, 0xbc, 0x44, 0x45));
            week.setTextColor(Color.BLACK);
        } else {
            week.setTextColor(Color.BLACK);
        }
        week.setText(getItem(position) + "");
        return week;
    }
}
