package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.CodeMessageBean;
import com.haitian.servicestaffapp.bean.UpdatePassWord_Bean;
import com.haitian.servicestaffapp.okutils.DoctorNetService;
import com.haitian.servicestaffapp.okutils.NetWorkRequestInterface;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.haitian.servicestaffapp.view.MyEdtext;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class UpdatePassWord_Activity extends BaseActivity {

    private ImageView mBack;
    private TextView mTitle_tv;
    private MyEdtext mOld_password_myed;
    private MyEdtext mNew_password_myed;
    private MyEdtext mNew_password_myed2;
    private Button mRight_btn;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_update_pass_word_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mBack = findViewById(R.id.title_back);
        mTitle_tv = findViewById(R.id.title_tv);

        mBack.setVisibility(View.VISIBLE);
        mTitle_tv.setText("修改密码");
        mTitle_tv.setVisibility(View.VISIBLE);

        mOld_password_myed = findViewById(R.id.old_password_myed);
        mNew_password_myed = findViewById(R.id.new_password_myed);
        mNew_password_myed2 = findViewById(R.id.new_password_myed2);
        mRight_btn = findViewById(R.id.right_btn);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mOld_password_myedString = mOld_password_myed.getText().toString();
                String mNew_password_myedString = mNew_password_myed.getText().toString();
                String mNew_password_myed2String = mNew_password_myed2.getText().toString();
                if(mOld_password_myedString==null){
                    Toast.makeText(UpdatePassWord_Activity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mNew_password_myedString==null){
                    Toast.makeText(UpdatePassWord_Activity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!mNew_password_myedString.equals(mNew_password_myed2String)){
                    Toast.makeText(UpdatePassWord_Activity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> map = new HashMap<>();
                map.put("user_id",DoctorBaseAppliction.spUtil.getString(Constants.USERID,""));
                map.put("user_pwd",mOld_password_myedString);
                map.put("newuser_pwd",mNew_password_myed2String);

                OkHttpUtil.getInteace().doPost(Constants.UPDATEPASSWORD, map, UpdatePassWord_Activity.this, new OkHttpUtil.OkCallBack() {
                    @Override
                    public void onFauile(Exception e) {
                        LogUtil.e("密码修改失败"+e.getMessage());
                    }

                    @Override
                    public void onResponse(final String json) {
                        LogUtil.e("密码修改成功："+json);

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Gson gson = new Gson();
                                    UpdatePassWord_Bean bean = gson.fromJson(json, UpdatePassWord_Bean.class);
                                    if (bean.getCode() == 20021){
                                        Toast.makeText(mContext, "密码修改成功！请重新登录", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(UpdatePassWord_Activity.this,Login_Activity.class));
                                        finish();
                                    }else {
                                        Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });

            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
