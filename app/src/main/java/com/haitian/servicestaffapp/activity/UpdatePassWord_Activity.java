package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.CodeMessageBean;
import com.haitian.servicestaffapp.okutils.DoctorNetService;
import com.haitian.servicestaffapp.okutils.NetWorkRequestInterface;
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
                    Toast.makeText(UpdatePassWord_Activity.this,"请输入原密码",0).show();
                }
                if(mNew_password_myedString==null){
                    Toast.makeText(UpdatePassWord_Activity.this,"请输入新密码",0).show();
                }
                if(mNew_password_myedString.length()<8){
                    Toast.makeText(UpdatePassWord_Activity.this,"密码长度应大于8",0).show();
                }
                if(mNew_password_myedString.equals(mNew_password_myed2String)){
                    Toast.makeText(UpdatePassWord_Activity.this,"两次输入密码不一致",0).show();
                }
                Map<String,Object> map = new HashMap<>();
                map.put("user_id","1");
                map.put("user_pwd","");
                map.put("newuser_pwd","");
                DoctorNetService.requestUpdatePw("", map, new NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object info) {
                        //{
                        //    "code": 20021,
                        //    "data": null,
                        //    "message": "密码修改成功"
                        //}or{
                        //    "code": 20040,
                        //    "data": null,
                        //    "message": "新旧密码相同"
                        //}or{
                        //    "code": 20040,CRITICAL critical
                        //    "data": null,
                        //    "message": "旧密码错误"  volidate  Volatile Bootstrap Ext Dalvik MEMORY
                        // moderate Stack Starter  Supervisor   Foreground  OnTrimMemory
                        //}or{
                        //    "code": 20020,
                        //    "data": null,
                        //    "message": "密码修改失败,数据不合法"
                        //}
                        CodeMessageBean codeMessageBean = (CodeMessageBean) info;
                        if(codeMessageBean.getCode()==20021){
                            Toast.makeText(UpdatePassWord_Activity.this,"密码修改成功",0).show();
                            finish();
                        } if(codeMessageBean.getCode()==20040){
                            Toast.makeText(UpdatePassWord_Activity.this,"新旧密码相同",0).show();
                        } if(codeMessageBean.getCode()==20021){
                            Toast.makeText(UpdatePassWord_Activity.this,"密码长度应大于8",0).show();
                        } if(codeMessageBean.getCode()==20021){
                            Toast.makeText(UpdatePassWord_Activity.this,"密码长度应大于8",0).show();
                        } if(codeMessageBean.getCode()==20020){
                            Toast.makeText(UpdatePassWord_Activity.this,"密码修改失败,数据不合法",0).show();
                        }
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
