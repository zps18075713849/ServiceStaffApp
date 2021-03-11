package com.haitian.servicestaffapp.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SetHeight {

    public static void setListViewHeightBasedOnChildren(ListView listView, android.widget.BaseAdapter adapter) {

        if (adapter==null){
            return;
        }
        int totalHeight = 0;

        for (int i = 0; i < adapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目

            View listItem = adapter.getView(i, null, listView);

            listItem.measure(0, 0); // 计算子项View 的宽高

            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (adapter.getCount() - 1));

        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);

    }
}
