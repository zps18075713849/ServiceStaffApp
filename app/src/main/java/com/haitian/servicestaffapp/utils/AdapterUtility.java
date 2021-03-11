package com.haitian.servicestaffapp.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AdapterUtility {
        public static void setListViewHeightBasedOnChildren(ListView listView) {

            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) return;
            int totalHeight = 0;
            for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);

        }
    }