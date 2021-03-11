package com.haitian.servicestaffapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.activity.FuWuTongJi_Activity;
import com.haitian.servicestaffapp.activity.GongDan_Activity;
import com.haitian.servicestaffapp.activity.JiFen_Activity;
import com.haitian.servicestaffapp.activity.JieSuanYe_Activity;
import com.haitian.servicestaffapp.activity.KeHuGuanLi_Activity;
import com.haitian.servicestaffapp.activity.PingJiaTouSu_Activity;
import com.haitian.servicestaffapp.activity.QiangDan_Activity;
import com.haitian.servicestaffapp.activity.RenWu_Activity;
import com.haitian.servicestaffapp.activity.TongZhiGongGao_Activity;
import com.haitian.servicestaffapp.base.BaseFragment;
import com.haitian.servicestaffapp.bean.TestPhotoBean;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends BaseFragment {

    private Banner mBanner_id;
    private ViewFlipper mViewfil;
    private LinearLayout mFuwutongji_line;
    private LinearLayout mPingjiatousu_line;
    private LinearLayout mKehuguanli_line;
    private LinearLayout mQiangdan_line;
    private LinearLayout mGongdan_line;
    private LinearLayout mRenwu_line;
    private LinearLayout mJifen_line;
    private LinearLayout mJiesuan_line;
    private LinearLayout mTongzhigonggao_line;
    ArrayList<String> titleList = new ArrayList<>();


    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        //轮播图
        mBanner_id = view.findViewById(R.id.banner_id);
        //文字轮播
        mViewfil = view.findViewById(R.id.viewfil);
        //服务统计
        mFuwutongji_line = view.findViewById(R.id.fuwutongji_line);
        //评价投诉
        mPingjiatousu_line = view.findViewById(R.id.pingjiatousu_line);
        //客户管理
        mKehuguanli_line = view.findViewById(R.id.kehuguanli_line);
        //抢单
        mQiangdan_line = view.findViewById(R.id.qiangdan_line);
        //工单
        mGongdan_line = view.findViewById(R.id.gongdan_line);
        //任务
        mRenwu_line = view.findViewById(R.id.renwu_line);
        //积分
        mJifen_line = view.findViewById(R.id.jifen_line);
        //结算
        mJiesuan_line = view.findViewById(R.id.jiesuan_line);
        //通知公告
        mTongzhigonggao_line = view.findViewById(R.id.tongzhigonggao_line);

        final ArrayList<String> photoUrlList = new ArrayList<>();
        photoUrlList.add("https://img.ivsky.com/img/tupian/pre/202002/29/dongji_xuejing-001.jpg");
        photoUrlList.add("https://img.ivsky.com/img/tupian/pre/202002/29/dongji_xuejing-003.jpg");

        mBanner_id.setImages(photoUrlList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerAnimation(Transformer.DepthPage)
                .setDelayTime(3000)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        LogUtil.e("Path:"+path);
                        Glide.with(getContext()).load(path.toString()).into(imageView);
                    }
                }).start();


        titleList.add("测试1");
        titleList.add("测试2");
        titleList.add("测试3");

        // 为ViewFlipper设置内容
        List<TextView> list = getData();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            mViewfil.addView(list.get(i));
        }

        // 设置文字in/out的动画效果
        mViewfil.setInAnimation(getContext(), R.anim.push_up_in);
        mViewfil.setOutAnimation(getContext(), R.anim.push_up_out);
        mViewfil.startFlipping();


    }

    /**
     * @return list
     * @Description: 要显示的文字信息
     * 在实际开发中，此方法可为对服务器返回数据的解析操作
     */
    private List<TextView> getData() {
        List<TextView> list = new ArrayList<TextView>();

        for (int i = 0; i < titleList.size(); i++) {
            TextView tv = (TextView) new TextView(getContext());
            tv.setText(titleList.get(i));
            list.add(tv);
        }

        return list;
    }

    @Override
    protected void initListener() {
        super.initListener();
        //评价投诉
        mPingjiatousu_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PingJiaTouSu_Activity.class);
                startActivity(intent);
            }
        });

        //服务统计
        mFuwutongji_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FuWuTongJi_Activity.class);
                startActivity(intent);
            }
        });

        //客户管理
        mKehuguanli_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KeHuGuanLi_Activity.class);
                startActivity(intent);
            }
        });

        //抢单
        mQiangdan_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QiangDan_Activity.class);
                startActivity(intent);
            }
        });

        //工单
        mGongdan_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GongDan_Activity.class);
                startActivity(intent);
            }
        });

        //积分
        mJifen_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JiFen_Activity.class);
                startActivity(intent);
            }
        });

        //任务
        mRenwu_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RenWu_Activity.class);
                startActivity(intent);
            }
        });
        //结算
        mJiesuan_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JieSuanYe_Activity.class);
                startActivity(intent);
            }
        });

        //通知
        mTongzhigonggao_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TongZhiGongGao_Activity.class);
                startActivity(intent);
            }
        });

        //滚动文字
        mViewfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int displayedChild = mViewfil.getDisplayedChild();
                Toast.makeText(getContext(), "标题：" +titleList.get(displayedChild), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), LunBoTitle_Info_Activity.class);
//                intent.putExtra("titleName", mTitlelist.get(displayedChild).getTitle());
////                intent.putExtra("text", mTitlelist.get(displayedChild).getText());
//                intent.putExtra("weburl", mTitlelist.get(displayedChild).getWangyelianjie());
//                startActivity(intent);
            }
        });
    }
}
