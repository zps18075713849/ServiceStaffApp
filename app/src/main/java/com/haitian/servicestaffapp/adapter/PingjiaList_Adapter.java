package com.haitian.servicestaffapp.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.bean.NewGongDan_Bean;
import com.haitian.servicestaffapp.bean.PingJiaListBean;

import org.w3c.dom.Text;

import java.util.ArrayList;

import me.zhouzhuo.zzratingbar.ZzRatingBar;

public class PingjiaList_Adapter extends RecyclerView.Adapter {
    private final FragmentActivity mActivity;
    private final ArrayList<PingJiaListBean.DataBean> mMlist;

    public PingjiaList_Adapter(FragmentActivity activity, ArrayList<PingJiaListBean.DataBean> mlist) {
        mActivity = activity;
        mMlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.pingjia_item, null, false);
        viewholderItem viewholderItem = new viewholderItem(inflate);
        return viewholderItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        viewholderItem viewitem = (viewholderItem) viewHolder;
        try {
            if (mMlist.get(i).getEvaluate().getAddr() == null){
                viewitem.mFuwu_address.setText("地址：");
            }else {
                viewitem.mFuwu_address.setText("地址：" + mMlist.get(i).getEvaluate().getAddr());
            }
            viewitem.mFuwuleixing_tv.setText("服务类型：" + mMlist.get(i).getEvaluate().getGoodsTypeName());
            viewitem.mFuwuneirong_tv.setText("服务内容：" + mMlist.get(i).getEvaluate().getFuwu_value());
            viewitem.mFuwutime_tv.setText("服务时间：" + mMlist.get(i).getEvaluate().getStartTime() + "-" + mMlist.get(i).getEvaluate().getEndTime());
            viewitem.mPrice_tv.setText("￥" + mMlist.get(i).getEvaluate().getCost());
            viewitem.mXing_bar.setRating(Integer.valueOf(mMlist.get(i).getEvaluate().getXingji()));


            if (mMlist.get(i).getEvaluate().getContent() == null) {
                viewitem.mKehupingjia_tv.setText("客户评价：");
            } else {
                viewitem.mKehupingjia_tv.setText("客户评价：" + mMlist.get(i).getEvaluate().getContent());

            }


            viewitem.mMobile_tv.setText(mMlist.get(i).getEvaluate().getOld_phone());
            Glide.with(mActivity).load(mMlist.get(i).getEvaluate().getOldtupian()).error(R.mipmap.icon_jibenzhongxin).into(viewitem.mImg_id);


            if (mMlist.get(i).getReply().getReply_content() != null) {
                if (mMlist.get(i).getReply().getReply_content().equals("")) {
                    viewitem.mHuifu_btn.setVisibility(View.VISIBLE);
                    viewitem.mHuifu_ll.setVisibility(View.VISIBLE);
                    viewitem.mHuifu_line.setVisibility(View.GONE);
                } else {
                    viewitem.mHuifu_ll.setVisibility(View.GONE);
                    viewitem.mHuifu_btn.setVisibility(View.GONE);
                    viewitem.mHuifu_line.setVisibility(View.VISIBLE);
                    viewitem.mHuifu_tv.setText("回复内容：" + mMlist.get(i).getReply().getReply_content());
                }
            }else {
                viewitem.mHuifu_btn.setVisibility(View.VISIBLE);
                viewitem.mHuifu_ll.setVisibility(View.VISIBLE);
                viewitem.mHuifu_line.setVisibility(View.GONE);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        viewitem.mZhuanchu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItem != null) {
                    mOnClickItem.onClick(i, 0);
                }
            }
        });

        //回复
        viewitem.mHuifu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItem!=null){
                    mOnClickItem.onClick(i,1);
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
        private final ZzRatingBar mXing_bar;
        private final TextView mFuwuleixing_tv;
        private final TextView mFuwuneirong_tv;
        private final TextView mFuwutime_tv;
        private final TextView mFuwu_address;
        private final TextView mPrice_tv;
        private final Button mZhuanchu_btn;
        private final TextView mKehupingjia_tv;
        private final TextView mHuifu_tv;
        private final LinearLayout mHuifu_line;
        private final Button mHuifu_btn;
        private final RelativeLayout mHuifu_ll;

        public viewholderItem(@NonNull View itemView) {
            super(itemView);
            mImg_id = itemView.findViewById(R.id.img_id);
            mMobile_tv = itemView.findViewById(R.id.mobile_tv);
            mXing_bar = itemView.findViewById(R.id.xing_bar);
            mFuwuleixing_tv = itemView.findViewById(R.id.fuwuleixing_tv);
            mFuwuneirong_tv = itemView.findViewById(R.id.fuwuneirong_tv);
            mFuwutime_tv = itemView.findViewById(R.id.fuwutime_tv);
            mFuwu_address = itemView.findViewById(R.id.fuwu_address);
            mPrice_tv = itemView.findViewById(R.id.price_tv);
            mZhuanchu_btn = itemView.findViewById(R.id.zhuanchu_btn);
            mKehupingjia_tv = itemView.findViewById(R.id.kehupingjia_tv);
            mHuifu_tv = itemView.findViewById(R.id.huifu_tv);
            mHuifu_line = itemView.findViewById(R.id.huifu_line);
            mHuifu_btn = itemView.findViewById(R.id.huifu_btn);
            mHuifu_ll = itemView.findViewById(R.id.huifu_ll);

        }
    }

    private onClickItem mOnClickItem;

    public void setOnClickItem(onClickItem onClickItem) {
        mOnClickItem = onClickItem;
    }

    public interface onClickItem {
        void onClick(int position, int type);
    }

}
