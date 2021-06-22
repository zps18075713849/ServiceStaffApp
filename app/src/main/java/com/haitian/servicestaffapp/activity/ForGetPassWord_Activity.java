package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.GetCode_Bean;
import com.haitian.servicestaffapp.bean.Register_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ForGetPassWord_Activity extends BaseActivity {

    private MyEdtext mNewpassword_ed;
    private MyEdtext mNewpassword_ed2;
    private MyEdtext mMobile_ed;
    private MyEdtext mMes_code_ed;
    private Button mGetduanxin_btn;
    private Button mRight_btn;
    private TextView mLijizhuce_tv;
    private String yanzhengcode;
    private TimeCount time;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_for_get_pass_word_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //新密码输入框
        mNewpassword_ed = findViewById(R.id.newpassword_ed);
        //确认密码
        mNewpassword_ed2 = findViewById(R.id.newpassword_ed2);
        //手机号
        mMobile_ed = findViewById(R.id.mobile_ed);
        //验证码输入框
        mMes_code_ed = findViewById(R.id.mes_code_ed);
        //获取验证码按钮
        mGetduanxin_btn = findViewById(R.id.getduanxin_btn);
        //提交按钮
        mRight_btn = findViewById(R.id.right_btn);
        //联系管理员
        mLijizhuce_tv = findViewById(R.id.lijizhuce_tv);
//倒计时重新获取验证码
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String psw = mNewpassword_ed.getText().toString().trim();
                String psw2 = mNewpassword_ed2.getText().toString().trim();
                String mobile = mMobile_ed.getText().toString().trim();
                String code = mMes_code_ed.getText().toString().trim();

                if (!psw.equals(psw2)){
                    Toast.makeText(mContext, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mobile.equals("")||mobile.length()!=11){
                    Toast.makeText(mContext, "手机号格式有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code.equals("")){
                    Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                requestUpdatePsw(psw,mobile,code);

            }
        });

        mGetduanxin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobile_ed.getText().toString().trim();
                if (mobile.isEmpty() || mobile.length() != 11) {
                    Toast.makeText(mContext, "手机号格式有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                getMobileCode(mobile);
            }
        });
    }

    private void requestUpdatePsw(String psw, String mobile, String code) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_name",mobile);
        map.put("messageId",yanzhengcode);
        map.put("messgae",code);
        map.put("user_pwd",psw);

        OkHttpUtil.getInteace().doPost(Constants.FORGETPASSWORD, map, ForGetPassWord_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(final Exception e) {
                hideWaitDialog();
                LogUtil.e("忘记密码失败："+e.getMessage());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (e.getMessage().equals("java.net.SocketTimeoutException: timeout")){
                            Toast.makeText(mContext, "服务器请求超时", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("忘记密码成功："+json);

                Gson gson = new Gson();
                final Register_Bean bean = gson.fromJson(json, Register_Bean.class);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041){
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void getMobileCode(String mobile) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_name",mobile);
        map.put("biaoji",1);
        OkHttpUtil.getInteace().doPost(Constants.GETCODE, map, ForGetPassWord_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(final Exception e) {
                hideWaitDialog();
                LogUtil.e("获取验证码失败：" + e.getMessage());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (e.getMessage().equals("java.net.SocketTimeoutException: timeout")){
                            Toast.makeText(mContext, "服务器请求超时", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("获取验证码成功：" + json);

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
     * 验证码倒计时
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long l) {//计时过程显示
            mGetduanxin_btn.setClickable(false);
            mGetduanxin_btn.setText("剩余" + l / 1000 + "秒");
        }

        @Override
        public void onFinish() {//计时完毕时触发
            mGetduanxin_btn.setText("重新获取");
            mGetduanxin_btn.setClickable(true);
        }
    }

    /**
     * 获取验证码请求
     */
    private void getVerifyMessage() {
        //验证码获取成功后
        mGetduanxin_btn.setEnabled(false);
    }

    /**
     * 开始计时
     */
    private void startRockOn() {
        time.start();//开始计时
    }

    @Override
    public Context context() {
        return null;
    }
}
