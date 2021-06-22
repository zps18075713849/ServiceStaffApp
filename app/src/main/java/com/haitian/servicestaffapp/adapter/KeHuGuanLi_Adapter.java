package com.haitian.servicestaffapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.activity.KeHuGuanLi_Activity;
import com.haitian.servicestaffapp.bean.KeHuGuanLi_Bean;

import org.w3c.dom.Text;

import java.util.ArrayList;

import me.nereo.multi_image_selector.bean.Image;

public class KeHuGuanLi_Adapter extends RecyclerView.Adapter {
    private final KeHuGuanLi_Activity mKeHuGuanLi_activity;
    private final ArrayList<String> mMlist;

    public KeHuGuanLi_Adapter(KeHuGuanLi_Activity keHuGuanLi_activity, ArrayList<String> mlist) {
        mKeHuGuanLi_activity = keHuGuanLi_activity;
        mMlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mKeHuGuanLi_activity).inflate(R.layout.kehuguanli_item, viewGroup, false);
        viewholderItem viewholderItem = new viewholderItem(inflate);
        return viewholderItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        viewholderItem viewholderItem = (KeHuGuanLi_Adapter.viewholderItem) viewHolder;
        viewholderItem.mMobile_tv.setText(mMlist.get(i));
        viewholderItem.mCall_phone.setOnClickListener(new View.OnClickListener() {
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

        private final ImageView mImg_id;
        private final TextView mMobile_tv;
        private final TextView mTime_tv;
        private final ImageView mCall_phone;

        public viewholderItem(@NonNull View itemView) {
            super(itemView);
            mImg_id = itemView.findViewById(R.id.img_id);
            mMobile_tv = itemView.findViewById(R.id.mobile_tv);
            mTime_tv = itemView.findViewById(R.id.time_tv);
            mCall_phone = itemView.findViewById(R.id.call_phone);

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
