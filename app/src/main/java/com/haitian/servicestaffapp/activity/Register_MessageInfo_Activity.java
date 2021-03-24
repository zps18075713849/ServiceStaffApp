package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.Register_BuMen_Bean;
import com.haitian.servicestaffapp.bean.Register_FuwuType_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.HcUtils;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register_MessageInfo_Activity extends BaseActivity {


    private ImageView mBack_img;
    private MyEdtext mName_ed;
    private MyEdtext mMobile_ed;
    private Button mNext_btn;
    private TextView mFuwutype_tv;
    private TextView mFuwusuoshu_tv;
    private List<String> mCardItem;
    private OptionsPickerView pvOptions;
    private String mMobile;
    private String mPassword;
    private String mMes_code;
    private List<String> mFuWuItem;
    private String FUWUID;
    private String SUOSHUID;
    private Register_FuwuType_Bean mFuWuType_Bean;
    private Register_BuMen_Bean mBuMen_Bean;
    private String mYanzheng_code;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register__message_info_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack_img = findViewById(R.id.back_img);
        mName_ed = findViewById(R.id.name_ed);
        mMobile_ed = findViewById(R.id.mobile_ed);

        mFuwutype_tv = findViewById(R.id.fuwutype_tv);
        mFuwusuoshu_tv = findViewById(R.id.fuwusuoshu_tv);

        mNext_btn = findViewById(R.id.next_btn);

        try {
            mMobile = getIntent().getStringExtra("mobile");
            mPassword = getIntent().getStringExtra("password");
            mMes_code = getIntent().getStringExtra("mes_code");
            mYanzheng_code = getIntent().getStringExtra("yanzhengid");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initData() {
        super.initData();
        mCardItem = new ArrayList<>();
        mFuWuItem = new ArrayList<>();
    }

    @Override
    protected void initListener() {
        super.initListener();

        mBack_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName_ed.getText().toString().trim();
                String mobile = mMobile_ed.getText().toString().trim();
                String fuwu_type = mFuwutype_tv.getText().toString().trim();
                String fuwu_suoshu = mFuwusuoshu_tv.getText().toString().trim();

                if (name.equals("")){
                    Toast.makeText(mContext, "请输入您的名字", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fuwu_type.equals("")){
                    Toast.makeText(mContext, "请选择您的服务类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fuwu_suoshu.equals("")){
                    Toast.makeText(mContext, "请选择您的所属", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Register_MessageInfo_Activity.this, Register_Upload_CardId_Activity.class);
                intent.putExtra("mobile",mMobile);
                intent.putExtra("password",mPassword);
                intent.putExtra("mes_code",mMes_code);
                intent.putExtra("name",name);
                intent.putExtra("fuwu_typeid",FUWUID);
                intent.putExtra("suoshuid",SUOSHUID);
                intent.putExtra("yanzhengid",mYanzheng_code);
                startActivity(intent);
            }
        });

        mFuwutype_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HcUtils.hideKeyboard(Register_MessageInfo_Activity.this);
                initOptionPickerFuWuId(mFuwutype_tv);
                requestFuWuType();
            }
        });

        mFuwusuoshu_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HcUtils.hideKeyboard(Register_MessageInfo_Activity.this);
                initOptionPickerBuMenSuoShu(mFuwusuoshu_tv);
                requestSuoShu();
            }
        });

    }

    private void requestFuWuType(){
        showWaitDialog();
        mFuWuItem.clear();
        Map<String, Object> map = new HashMap<>();
        OkHttpUtil.getInteace().doPost(Constants.REGISTER_FUWU, map, Register_MessageInfo_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("服务类型失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("服务类型成功："+json);
                Gson gson = new Gson();
                mFuWuType_Bean = gson.fromJson(json, Register_FuwuType_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (mFuWuType_Bean.getCode() == 20041){
                                for (int i = 0; i < mFuWuType_Bean.getData().size(); i++) {
                                    mFuWuItem.add(mFuWuType_Bean.getData().get(i).getFuwutype());
                                }
                                pvOptions.setPicker(mFuWuItem);
                                pvOptions.show();
                            }else {
                                Toast.makeText(mContext, "服务类型获取失败", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void requestSuoShu(){
        showWaitDialog();
        mCardItem.clear();
        Map<String, Object> map = new HashMap<>();
        OkHttpUtil.getInteace().doPost(Constants.REGISTER_BUMEN, map, Register_MessageInfo_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("部门所属失败："+e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("部门所属成功："+json);
                Gson gson = new Gson();
                mBuMen_Bean = gson.fromJson(json, Register_BuMen_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mBuMen_Bean.getCode() == 20041){
                            for (int i = 0; i < mBuMen_Bean.getData().size(); i++) {
                                mCardItem.add(mBuMen_Bean.getData().get(i).getSupplier_name());
                            }
                            pvOptions.setPicker(mCardItem);
                            pvOptions.show();
                        }else {
                            Toast.makeText(mContext, "部门所属获取失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }

    private void initOptionPickerFuWuId(final TextView view) {//条件选择器初始化
        //设置滚轮文字大小
        //默认选中项
        //设置两横线之间的间隔倍数
        //设置外部遮罩颜色
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = mFuWuItem.get(options1);
                view.setText(tx);
                FUWUID = mFuWuType_Bean.getData().get(options1).getId()+"";
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCancelColor(getResources().getColor(R.color.liji_c_blue))
                .setSubmitColor(getResources().getColor(R.color.liji_c_blue))
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }

    private void initOptionPickerBuMenSuoShu(final TextView view) {//条件选择器初始化
        //设置滚轮文字大小
        //默认选中项
        //设置两横线之间的间隔倍数
        //设置外部遮罩颜色
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = mCardItem.get(options1);
                view.setText(tx);
                SUOSHUID = mBuMen_Bean.getData().get(options1).getSupplier_id()+"";
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCancelColor(getResources().getColor(R.color.liji_c_blue))
                .setSubmitColor(getResources().getColor(R.color.liji_c_blue))
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }


    private void initOptionPickerSex(final TextView view) {//条件选择器初始化
        //设置滚轮文字大小
        //默认选中项
        //设置两横线之间的间隔倍数
        //设置外部遮罩颜色
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = mCardItem.get(options1);
                view.setText(tx);
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCancelColor(getResources().getColor(R.color.liji_c_blue))
                .setSubmitColor(getResources().getColor(R.color.liji_c_blue))
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }

    @Override
    public Context context() {
        return null;
    }
}
