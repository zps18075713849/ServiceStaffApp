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
import com.haitian.servicestaffapp.activity.FuWuXiangMu_Activity;
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
    private LinearLayout mFuwuXiangMu_line;
    private LinearLayout mGongdan_line;
    private LinearLayout mRenwu_line;
    private LinearLayout mJifen_line;
    private LinearLayout mJiesuan_line;
    private LinearLayout mTongzhigonggao_line;
    ArrayList<String> titleList = new ArrayList<>();

    List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;//???????????????
    String[] per = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private boolean permission = false;
    private LinearLayout mShangcheng_line;
    private LinearLayout mKehuguanli_line;

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
        //?????????
        mBanner_id = view.findViewById(R.id.banner_id);
        //????????????
        mViewfil = view.findViewById(R.id.viewfil);
        //????????????
        mFuwutongji_line = view.findViewById(R.id.fuwutongji_line);
        //????????????
        mPingjiatousu_line = view.findViewById(R.id.pingjiatousu_line);
        //????????????
        mFuwuXiangMu_line = view.findViewById(R.id.fuwuxiangmu_line);
       //??????
        mShangcheng_line = view.findViewById(R.id.shangcheng_line);
        //??????
        mGongdan_line = view.findViewById(R.id.gongdan_line);
        //??????
        mRenwu_line = view.findViewById(R.id.renwu_line);
        //??????
        mJifen_line = view.findViewById(R.id.jifen_line);
        //??????
        mJiesuan_line = view.findViewById(R.id.jiesuan_line);
        //????????????
        mTongzhigonggao_line = view.findViewById(R.id.tongzhigonggao_line);
        //????????????
        mKehuguanli_line = view.findViewById(R.id.kehuguanli_line);


        final ArrayList<Integer> photoUrlList = new ArrayList<>();
        photoUrlList.add(R.mipmap.banner_two);
        photoUrlList.add(R.mipmap.banner_three);

        mBanner_id.setImages(photoUrlList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerAnimation(Transformer.DepthPage)
                .setDelayTime(3000)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        LogUtil.e("Path:" + path);
                        Glide.with(getContext()).load(path).into(imageView);
                    }
                }).start();


        titleList.add("????????????????????????APP");
//        titleList.add("??????2");
//        titleList.add("??????3");

        // ???ViewFlipper????????????
        List<TextView> list = getData();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            mViewfil.addView(list.get(i));
        }

        // ????????????in/out???????????????
        mViewfil.setInAnimation(getContext(), R.anim.push_up_in);
        mViewfil.setOutAnimation(getContext(), R.anim.push_up_out);
        mViewfil.startFlipping();


    }

    /**
     * @return list
     * @Description: ????????????????????????
     * ???????????????????????????????????????????????????????????????????????????
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
        //????????????
        mPingjiatousu_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PingJiaTouSu_Activity.class);
                startActivity(intent);
            }
        });

        //????????????
        mFuwutongji_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FuWuTongJi_Activity.class);
                startActivity(intent);
            }
        });

        //??????
        mGongdan_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permission){
                    Intent intent = new Intent(getActivity(), QiangDan_Activity.class);
                    startActivity(intent);
                }else {
                    //????????????
                    if (Build.VERSION.SDK_INT >= 23) {//6.0??????????????????
                        initPar();
                    }
                }
            }
        });

        //??????
        mJifen_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JiFen_Activity.class);
                startActivity(intent);
            }
        });

        //??????
        mRenwu_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RenWu_Activity.class);
                startActivity(intent);
            }
        });
        //??????
        mJiesuan_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JieSuanYe_Activity.class);
                startActivity(intent);
            }
        });

        //??????
        mTongzhigonggao_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TongZhiGongGao_Activity.class);
                startActivity(intent);
            }
        });

        //????????????
        mViewfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int displayedChild = mViewfil.getDisplayedChild();
//                Toast.makeText(getContext(), "?????????" + titleList.get(displayedChild), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), LunBoTitle_Info_Activity.class);
//                intent.putExtra("titleName", mTitlelist.get(displayedChild).getTitle());
////                intent.putExtra("text", mTitlelist.get(displayedChild).getText());
//                intent.putExtra("weburl", mTitlelist.get(displayedChild).getWangyelianjie());
//                startActivity(intent);
            }
        });

        //????????????
        mFuwuXiangMu_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FuWuXiangMu_Activity.class));
            }
        });

        //????????????
        mKehuguanli_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),KeHuGuanLi_Activity.class));
            }
        });
    }

    //????????????
    private void initPar() {
        mPermissionList.clear();//???????????????????????????
        //?????????????????????????????????????????????
        for (int i = 0; i < per.length; i++) {
            if (ContextCompat.checkSelfPermission(getContext(), per[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(per[i]);//???????????????????????????
            }
        }

        //????????????
        if (mPermissionList.size() > 0) {//????????????????????????????????????
            new AlertDialog.Builder(getContext())
                    .setMessage("?????????????????????????????????????????????")
                    .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //???????????????????????????????????????
                            Intent intent = new Intent();
                            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .create()
                    .show();

        } else {
            //?????????????????????
            permission = true;
        }
    }

    //??????????????????????????????
    //????????? requestCode  ???????????????????????????????????????
    //????????? permissions  ????????????????????????????????????
    //????????? grantResults ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????0?????????????????????-1?????????????????????????????????
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//?????????????????????
        if (mRequestCode == requestCode) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    LogUtil.e(grantResults[i] + "i:" + i);

                    hasPermissionDismiss = true;
                }
            }
            //??????????????????????????????
            if (hasPermissionDismiss) {
                showPermissionDialog();//????????????????????????????????????????????????????????????????????????????????????
            } else {
                //?????????????????????????????????????????????????????????
            }
        }

    }

    /**
     * ???????????????????????????????????????
     */
    AlertDialog mPermissionDialog;

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(getContext())
                    .setMessage("????????????????????????????????????????????????")
                    .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            getAppDetailSettingIntent(getContext());
                            cancelPermissionDialog();
                            LogUtil.e("???????????????");
                        }
                    })
                    .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //?????????????????????????????????
                            cancelPermissionDialog();

                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }

    //???????????????
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    /**
     * ???????????????????????????
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
