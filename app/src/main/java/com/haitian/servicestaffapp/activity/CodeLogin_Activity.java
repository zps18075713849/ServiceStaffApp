package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.GetCode_Bean;
import com.haitian.servicestaffapp.bean.Login_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class CodeLogin_Activity extends BaseActivity {

    private MyEdtext mMobile_ed;
    private MyEdtext mMes_code_ed;
    private Button mGetduanxin_btn;
    private Button mLogin_bt;
    private TextView mForgetpassword_tv;
    private TextView mLijizhuce_tv;
    private String yanzhengcode;
    private TimeCount time;
    private TextView mFuwuxieyi_tv;
    private TextView mYinsi_tv;
    private CheckBox mTongyi_check;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_code_login_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mMobile_ed = findViewById(R.id.mobile_ed);
        mMes_code_ed = findViewById(R.id.mes_code_ed);
        mGetduanxin_btn = findViewById(R.id.getduanxin_btn);
        mLogin_bt = findViewById(R.id.login_bt);
        mForgetpassword_tv = findViewById(R.id.forgetpassword_tv);
        mLijizhuce_tv = findViewById(R.id.lijizhuce_tv);

        mFuwuxieyi_tv = findViewById(R.id.yonghuxieyi_tv);
        mYinsi_tv = findViewById(R.id.yinsi_tv);
        mTongyi_check = findViewById(R.id.tongyi_check);

        //??????????????????????????????
        time = new TimeCount(60000, 1000);//??????CountDownTimer??????
    }

    @Override
    protected void initListener() {
        super.initListener();
        //???????????????
        mGetduanxin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobile_ed.getText().toString().trim();
                if (mobile.isEmpty() || mobile.length() != 11) {
                    Toast.makeText(mContext, "?????????????????????", Toast.LENGTH_SHORT).show();
                    return;
                }
                getMobileCode(mobile);
            }
        });


        //????????????
        mForgetpassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeLogin_Activity.this, ForGetPassWord_Activity.class);
                startActivity(intent);
            }
        });

        //????????????
        mLijizhuce_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeLogin_Activity.this, Register_Activity.class);
                startActivity(intent);
            }
        });

        //??????
        mLogin_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = mTongyi_check.isChecked();
                if (checked){
                    login();
                }else {
                    Toast.makeText(mContext, "??????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        //????????????
        mFuwuxieyi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeLogin_Activity.this, XieYi_Web.class);
                intent.putExtra("totalbarName","????????????");
                intent.putExtra("webUrl","http://111.17.215.37/yanglao/app/serviceAgreement.html");
                startActivity(intent);
            }
        });

        //????????????
        mYinsi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeLogin_Activity.this, XieYi_Web.class);
                intent.putExtra("totalbarName","????????????");
                intent.putExtra("webUrl","http://111.17.215.37/yanglao/app/privacyPolicy.html");
                startActivity(intent);
            }
        });

    }

    private void login() {
        String mobile = mMobile_ed.getText().toString().trim();
        String code = mMes_code_ed.getText().toString().trim();
        if (mobile.equals("") || mobile.length() != 11) {
            Toast.makeText(mContext, "????????????????????????", Toast.LENGTH_SHORT).show();
            return;
        }
        if (code.equals("")) {
            Toast.makeText(mContext, "??????????????????", Toast.LENGTH_SHORT).show();
            return;
        }

        requestLogin(mobile,code);

    }

    private void requestLogin(String mobile, String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name",mobile);
        map.put("messageId",yanzhengcode);
        map.put("messgae",code);

        OkHttpUtil.getInteace().doPost(Constants.CODELOGIN, map, CodeLogin_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(final Exception e) {
                LogUtil.e("????????????????????????"+e.getMessage());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (e.getMessage().equals("java.net.SocketTimeoutException: timeout")){
                            Toast.makeText(mContext, "?????????????????????", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }

            @Override
            public void onResponse(String json) {
                LogUtil.e("????????????????????????"+json);

                Gson gson = new Gson();
                final Login_Bean login_bean = gson.fromJson(json, Login_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (login_bean.getCode() == 20041){
                            Toast.makeText(mContext, login_bean.getMessage(), Toast.LENGTH_SHORT).show();
                            SputilData(login_bean);
                            Intent intent = new Intent(CodeLogin_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(mContext, login_bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private void getMobileCode(String mobile) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", mobile);
        map.put("biaoji", 1);
        OkHttpUtil.getInteace().doPost(Constants.GETCODE, map, CodeLogin_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(final Exception e) {
                hideWaitDialog();
                LogUtil.e("????????????????????????" + e.getMessage());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (e.getMessage().equals("java.net.SocketTimeoutException: timeout")){
                            Toast.makeText(mContext, "?????????????????????", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("????????????????????????" + json);

                Gson gson = new Gson();
                final GetCode_Bean bean = gson.fromJson(json, GetCode_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20011) {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            yanzhengcode = bean.getData() + "";
                            startRockOn();
                        } else {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }


    /**
     * ??????????????????
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//????????????????????????,????????????????????????
        }

        @Override
        public void onTick(long l) {//??????????????????
            mGetduanxin_btn.setClickable(false);
            mGetduanxin_btn.setText("??????" + l / 1000 + "???");
        }

        @Override
        public void onFinish() {//?????????????????????
            mGetduanxin_btn.setText("????????????");
            mGetduanxin_btn.setClickable(true);
        }
    }

    /**
     * ?????????????????????
     */
    private void getVerifyMessage() {
        //????????????????????????
        mGetduanxin_btn.setEnabled(false);
    }

    /**
     * ????????????
     */
    private void startRockOn() {
        time.start();//????????????
    }

    //??????????????????
    private void SputilData(Login_Bean login_bean) {
        try {
            DoctorBaseAppliction.spUtil.putString(Constants.USERID,login_bean.getData().getUser_id()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_NAME,login_bean.getData().getUser_name()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_NICKNAME,login_bean.getData().getUser_nickname()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_CARD_NAME,login_bean.getData().getUser_card_name()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_PWD,login_bean.getData().getUser_pwd()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_AFFILIATE,login_bean.getData().getUser_affiliate()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_ROLE_ID,login_bean.getData().getUser_role_id()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_DEPT,login_bean.getData().getUser_dept()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_MESSAGEID,login_bean.getData().getMessageId()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_MESSAGE,login_bean.getData().getMessgae()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_TYPE,login_bean.getData().getUser_type()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_DEPARTMENT,login_bean.getData().getUser_department()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_ZHENGJIAN,login_bean.getData().getUser_zhengjian()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.USER_NEWUSER_PWD,login_bean.getData().getNewuser_pwd()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.SHOUCHIZHAOP,login_bean.getData().getShouchizhaop()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.HUOJIANGZHENGSHU,login_bean.getData().getHuojiangzhengshu()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.SHOUCHI,login_bean.getData().getShouchi()+"");
            DoctorBaseAppliction.spUtil.putString(Constants.HUOJIANGZHENGS,login_bean.getData().getHuojiangzhengs()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public Context context() {
        return null;
    }
}
