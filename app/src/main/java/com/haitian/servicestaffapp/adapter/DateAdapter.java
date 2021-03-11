package com.haitian.servicestaffapp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.bean.DateEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * author：Administrator on 2017/4/10 16:11
 * description:文件说明
 * version:版本
 */
public class DateAdapter extends BaseAdapter {
    private Context mContext ;
    private ArrayList<DateEntity> datas ;
    private String[] weekTitle = {"日","一", "二", "三", "四", "五", "六"};
    public DateAdapter(Context mContext, ArrayList<DateEntity> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public DateEntity getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private int selectedPosition = -1;// 选中的位置

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DateEntity info = datas.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_data, null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.week_num = (TextView) convertView.findViewById(R.id.week_num);
            holder.item_data = (TextView) convertView.findViewById(R.id.item_data);
            holder.layout_week = convertView.findViewById(R.id.layout_week);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.week_num.setText(weekTitle[position]);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        // 获取当前的日期
        String format = df.format(new Date());

        if (info.date.equals(format)){
            holder.item_data.setText("今日");
            holder.item_data.setTextColor(ContextCompat.getColor(mContext,R.color.black));
        }else {
            holder.item_data.setText(info.day);
        }

        if (selectedPosition == position) {
            holder.week_num.setTextColor(ContextCompat.getColor(mContext,R.color.black));
            holder.item_data.setTextColor(ContextCompat.getColor(mContext,R.color.white));
            if (info.isToday){
//                holder.layout_week.setBackgroundResource(R.drawable.select_bg);
                holder.week_num.setTextColor(ContextCompat.getColor(mContext,R.color.black));
                holder.item_data.setTextColor(ContextCompat.getColor(mContext,R.color.black));
            }else {
                holder.item_data.setBackgroundResource(R.drawable.select_green_bg);
            }

        } else {
            if (info.isToday){
                holder.week_num.setTextColor(ContextCompat.getColor(mContext,R.color.black));
//                holder.layout_week.setBackgroundResource(R.drawable.select_bg);
                holder.item_data.setTextColor(ContextCompat.getColor(mContext,R.color.black));
            }else {
                holder.week_num.setTextColor(ContextCompat.getColor(mContext,R.color.black));
                holder.item_data.setTextColor(ContextCompat.getColor(mContext,R.color.black));
                holder.item_data.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_ffffff));
            }

        }
        return convertView;
    }
    public class ViewHolder {
        TextView week_num;
        TextView item_data;
        ImageView image;
        View layout_week;
    }
}
