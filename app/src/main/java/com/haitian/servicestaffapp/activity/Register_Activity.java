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
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.GetCode_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends BaseActivity {

    private Button mNext_btn;
    private MyEdtext mMobile_ed;
    private MyEdtext mPassword_ed;
    private MyEdtext mMes_code_ed;
    private Button mGetduanxin_btn;
    private TextView mUseragreement_tv;
    private CheckBox mCheck_id;
    private String yanzhengcode;
    private TimeCount time;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //下一步
        mNext_btn = findViewById(R.id.next_btn);
        //手机号
        mMobile_ed = findViewById(R.id.mobile_ed);
        //密码
        mPassword_ed = findViewById(R.id.password_ed);
        //验证码
        mMes_code_ed = findViewById(R.id.mes_code_ed);
        //获取验证码
        mGetduanxin_btn = findViewById(R.id.getduanxin_btn);
        //服务用户协议
        mUseragreement_tv = findViewById(R.id.useragreement_tv);
        //是否阅读
        mCheck_id = findViewById(R.id.check_id);

        //倒计时重新获取验证码
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    @Override
    protected void initListener() {
        super.initListener();
        mNext_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobile_ed.getText().toString().trim();
                String password = mPassword_ed.getText().toString().trim();
                String mes_code = mMes_code_ed.getText().toString().trim();
                if (mobile.equals("") || mobile.length() != 11) {
                    Toast.makeText(mContext, "请检查手机号格式", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals("")) {
                    Toast.makeText(mContext, "请填写您的密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mes_code.equals("")) {
                    Toast.makeText(mContext, "请输入您获取的短信验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mCheck_id.isChecked()) {
                    LogUtil.e("验证id："+yanzhengcode);

                    Intent intent = new Intent(Register_Activity.this, Register_MessageInfo_Activity.class);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("password", password);
                    intent.putExtra("mes_code", mes_code);
                    intent.putExtra("yanzhengid",yanzhengcode);
                    startActivity(intent);
                } else {
                    Toast.makeText(mContext, "请先阅读并同意用户服务协议", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        //获取验证码
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

    private void getMobileCode(String mobile) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("user_name",mobile);
        map.put("biaoji",2);
        OkHttpUtil.getInteace().doPost(Constants.GETCODE, map, Register_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("获取验证码失败：" + e.getMessage());
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
