package com.haitian.servicestaffapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.activity.TongZhiGongGao_Activity;
import com.haitian.servicestaffapp.bean.TongZhiList_Bean;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TongZhiList_Adapter extends RecyclerView.Adapter {
    private final TongZhiGongGao_Activity mTongZhiGongGao_activity;
    private final ArrayList<TongZhiList_Bean.DataBean> mMlist;

    public TongZhiList_Adapter(TongZhiGongGao_Activity tongZhiGongGao_activity, ArrayList<TongZhiList_Bean.DataBean> mlist) {
        mTongZhiGongGao_activity = tongZhiGongGao_activity;
        mMlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mTongZhiGongGao_activity).inflate(R.layout.tongzhi_list_item, null, false);
        viewholderItem viewholderItem = new viewholderItem(inflate);
        return viewholderItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        viewholderItem viewholderItem = (TongZhiList_Adapter.viewholderItem) viewHolder;
        viewholderItem.mTitle_tv.setText(mMlist.get(i).getShequ_title());
//        viewholderItem.mContent_tv.setText(mMlist.get(i).get);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItem!=null){
                    mOnClickItem.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMlist.size();
    }

    class viewholderItem extends RecyclerView.ViewHolder {

        private final TextView mTitle_tv;
        private final TextView mContent_tv;

        public viewholderItem(@NonNull View itemView) {
            super(itemView);
            mTitle_tv = itemView.findViewById(R.id.title_tv);
            mContent_tv = itemView.findViewById(R.id.content_tv);
        }
    }

    private onClickItem mOnClickItem;


    public void setOnClickItem(onClickItem onClickItem) {
        mOnClickItem = onClickItem;
    }

    public interface onClickItem{
        void onClick(int position);
    }
}
