package com.haitian.servicestaffapp.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.bean.YiJieDanList_Bean;
import com.haitian.servicestaffapp.utils.HcUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import me.zhouzhuo.zzratingbar.ZzRatingBar;

import static com.haitian.servicestaffapp.utils.DateUtils.FORMAT_1;

public class YiJieDan_Adapter extends RecyclerView.Adapter {
    private final FragmentActivity mActivity;
    private ArrayList<YiJieDanList_Bean.DataBean> mMlist;

    public YiJieDan_Adapter(FragmentActivity activity, ArrayList<YiJieDanList_Bean.DataBean> mlist) {
        mActivity = activity;
        mMlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.yijiedan_gongdan_item, viewGroup, false);
        viewholderItem viewholderItem = new viewholderItem(inflate);
        return viewholderItem;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        viewholderItem viewholder = (YiJieDan_Adapter.viewholderItem) viewHolder;

        try {
            viewholder.mFuwu_address.setText("地址："+mMlist.get(i).getWaiter_address());
            viewholder.mFuwuleixing_tv.setText("服务类型："+mMlist.get(i).getGoodstypeName());
            viewholder.mFuwuneirong_tv.setText("服务内容："+mMlist.get(i).getFuwu_value());
            viewholder.mPrice_tv.setText("￥"+mMlist.get(i).getCost());
            viewholder.mMobile_tv.setText(mMlist.get(i).getOld_phone());
            viewholder.mXing_bar.setRating(Integer.valueOf(mMlist.get(i).getXingji()));

            long start = mMlist.get(i).getStartTime();
            long end = mMlist.get(i).getEndTime();
            String starttime = HcUtils.getData(start, FORMAT_1);
            String endTime = HcUtils.getData(end, FORMAT_1);
            viewholder.mFuwutime_tv.setText("服务时间："+starttime+"-"+endTime);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        viewholder.mZhuanchu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItem!=null){
                    mOnClickItem.onClick(i,0);
                }
            }
        });

        viewholder.mZhixing_btn.setOnClickListener(new View.OnClickListener() {
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

        private final TextView mMobile_tv;
        private final ImageView mImg_id;
        private final ZzRatingBar mXing_bar;
        private final TextView mFuwuleixing_tv;
        private final TextView mFuwuneirong_tv;
        private final TextView mFuwutime_tv;
        private final TextView mFuwu_address;
        private final TextView mPrice_tv;
        private final TextView mZhuanru_mobile;
        private final TextView mZhuanru_name;
        private final Button mZhuanchu_btn;
        private final Button mZhixing_btn;

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

            mZhuanru_mobile = itemView.findViewById(R.id.zhuanru_mobile);
            mZhuanru_name = itemView.findViewById(R.id.zhuanru_name);
            mZhuanchu_btn = itemView.findViewById(R.id.zhuanchu_btn);
            mZhixing_btn = itemView.findViewById(R.id.zhixing_btn);
        }
    }

    private onClickItem mOnClickItem;

    public void setOnClickItem(onClickItem onClickItem) {
        mOnClickItem = onClickItem;
    }

    public interface onClickItem{
        void onClick(int position,int type);
    }
}
