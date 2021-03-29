package com.haitian.servicestaffapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.activity.DataSetting_Activity;
import com.haitian.servicestaffapp.activity.Reigster_UploadCertificate_Activity;


import java.util.ArrayList;

public class AddDataSetting_Adapter extends RecyclerView.Adapter {
    private final Reigster_UploadCertificate_Activity mHuLiZhiXing_activity;
    private final ArrayList<String> mNewPic;

    public AddDataSetting_Adapter(Reigster_UploadCertificate_Activity huLiZhiXing_activity, ArrayList<String> newPic) {
        mHuLiZhiXing_activity = huLiZhiXing_activity;
        mNewPic = newPic;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mHuLiZhiXing_activity).inflate(R.layout.addhulizhixing_item, null);
        viewholderItem viewholderItem = new viewholderItem(inflate);
        return viewholderItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        viewholderItem viewholderItem = (AddDataSetting_Adapter.viewholderItem) holder;
        Glide.with(mHuLiZhiXing_activity).load(mNewPic.get(position))
                .into(viewholderItem.mMine_item_image);

        viewholderItem.mDelete_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewPic.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewPic.size();
    }
    class viewholderItem extends RecyclerView.ViewHolder {

        private final ImageView mMine_item_image;
        private final ImageView mDelete_photo;

        public viewholderItem(@NonNull View itemView) {
            super(itemView);
            mMine_item_image = itemView.findViewById(R.id.mine_item_image);
            mDelete_photo = itemView.findViewById(R.id.delete_photo);
        }
    }
}
