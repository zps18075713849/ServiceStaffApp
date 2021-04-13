package com.haitian.servicestaffapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.baidu.location.LocationClient;
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
import com.haitian.servicestaffapp.activity.SplashActivity;
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

    List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;//权限请求码
    String[] per = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private boolean permission = false;

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
                        LogUtil.e("Path:" + path);
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


                if (permission){
                    Intent intent = new Intent(getActivity(), QiangDan_Activity.class);
                    startActivity(intent);
                }else {
                    //获取权限
                    if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
                        initPar();
                    }
                }


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
                Toast.makeText(getContext(), "标题：" + titleList.get(displayedChild), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), LunBoTitle_Info_Activity.class);
//                intent.putExtra("titleName", mTitlelist.get(displayedChild).getTitle());
////                intent.putExtra("text", mTitlelist.get(displayedChild).getText());
//                intent.putExtra("weburl", mTitlelist.get(displayedChild).getWangyelianjie());
//                startActivity(intent);
            }
        });
    }

    //申请权限
    private void initPar() {
        mPermissionList.clear();//清空没有通过的权限
        //逐个判断你要的权限是否已经通过
        for (int i = 0; i < per.length; i++) {
            if (ContextCompat.checkSelfPermission(getContext(), per[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(per[i]);//添加还未授予的权限
            }
        }

        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            new AlertDialog.Builder(getContext())
                    .setMessage("需要开启定位权限才能使用此功能")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //引导用户到设置中去进行设置
                            Intent intent = new Intent();
                            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create()
                    .show();

        } else {
            //权限都已经通过
            permission = true;
        }
    }

    //请求权限后回调的方法
    //参数： requestCode  是我们自己定义的权限请求码
    //参数： permissions  是我们请求的权限名称数组
    //参数： grantResults 是我们在弹出页面后是否允许权限的标识数组，数组的长度对应的是权限名称数组的长度，数组的数据0表示允许权限，-1表示我们点击了禁止权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    LogUtil.e(grantResults[i] + "i:" + i);

                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                showPermissionDialog();//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
            } else {
                //全部权限通过，可以进行下一步操作。。。
            }
        }

    }

    /**
     * 不再提示权限时的展示对话框
     */
    AlertDialog mPermissionDialog;

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(getContext())
                    .setMessage("已禁用定位权限，是否确定授予权限")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            getAppDetailSettingIntent(getContext());
                            cancelPermissionDialog();
                            LogUtil.e("点击确定了");
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();

                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }

    //关闭对话框
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    /**
     * 跳转到权限设置界面
     */
    private void getAppDetailSettingIntent(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", getActivity().getPackageName());
        }
        startActivity(intent);
    }

}
