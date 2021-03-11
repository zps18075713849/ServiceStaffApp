package com.haitian.servicestaffapp.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tu.loadingdialog.LoadingDailog;
import com.haitian.servicestaffapp.R;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    private View view;
    private Unbinder unbinder;
    protected LoadingDailog.Builder loadBuilder;
    protected LoadingDailog dialog;

    private Handler myHandler = new Handler();
    private int TIME = 2000*60;  //每隔两分钟执行一次.
    private int a = 0;      //用来判断用户是否执行操作，1是执行，2是未操作
    private int b = 0;      //用来计时。
    private String mTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(provideLayoutId(), container, false);
        unbinder = ButterKnife.bind(provideBindView(), view);
//        mTime = DoctorBaseAppliction.spUtil.getString(Constants.Time, "");

//        handler.postDelayed(runnable, TIME); // 在初始化方法里.
        return view;
    }



//    Handler handler = new Handler();
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                handler.postDelayed(this, TIME);
//                if (a == 1){
//                    a = 0;
//                    b+=2;
//                    mTime = b+"";
////                    Toast.makeText(mContext, "时间："+mTime, Toast.LENGTH_SHORT).show();
//                    LogUtil.e("if时间 mtime"+ mTime);
//                    LogUtil.e("if时间 b"+ b);
//                    DoctorBaseAppliction.spUtil.putString(Constants.Time,mTime);
//                }else {
////                    Toast.makeText(mContext, "时间"+mTime, Toast.LENGTH_SHORT).show();
//                    LogUtil.e("if时间 mtime"+ mTime);
//                    LogUtil.e("if时间 b"+ b);
//                    DoctorBaseAppliction.spUtil.putString(Constants.Time,mTime);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };











    protected abstract Object provideBindView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(view);
        //提供presenter
        initData();
        initListener();

    }

    @SuppressLint("ResourceAsColor")
    public void showInputDialog(String hint, OnInputClickListener onInputClickListener) {
        new CircleDialog.Builder(getActivity())
                //添加标题，参考普通对话框
                .setInputHint(hint)//提示
                .setTitle("请输入")
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .setInputCounterColor(R.color.text_color)//最大字符数文字的颜色值
                .autoInputShowKeyboard()//自动弹出键盘
                .setPositiveInput("确定", onInputClickListener)
                .show();
    }

    @SuppressLint("ResourceAsColor")
    public void showAffirmDialog(String title, String message, View.OnClickListener
            onClickListener) {
        new CircleDialog.Builder(getActivity())
                .setTitle(title)
                .setText(message)//内容
                .setTextColor(R.color.text_color)//内容字体色值
                .setPositive("确定", onClickListener)
                .setNegative("取消", null)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .show();
    }

    public void showWaitDialog() {
//        if (dialog.isShowing()) return;
        loadBuilder = new LoadingDailog.Builder(getActivity())
                .setCancelable(true)
                .setShowMessage(false)
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        dialog.show();
    }

    public void hideWaitDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
    }


    protected abstract int provideLayoutId();


    protected void initViews(View view) {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
