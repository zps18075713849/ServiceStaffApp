package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.bean.TongZhiInfo_Bean;
import com.haitian.servicestaffapp.okutils.OkHttpUtil;
import com.haitian.servicestaffapp.utils.LogUtil;
import com.zzhoujay.richtext.RichText;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class TongZhiInfo_Activity extends BaseActivity {


    private ImageView mTitle_back;
    private TextView mContent_tv;
    private int mId;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_tong_zhi_info_;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTitle_back = findViewById(R.id.title_back);
        mContent_tv = findViewById(R.id.content_tv);

        try {
            mId = getIntent().getIntExtra("id", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initData() {
        super.initData();
        requestData(mId);
    }

    private void requestData(int id) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);

        OkHttpUtil.getInteace().doPost(Constants.TONGZHIINFO, map, TongZhiInfo_Activity.this, new OkHttpUtil.OkCallBack() {
            @Override
            public void onFauile(Exception e) {
                hideWaitDialog();
                LogUtil.e("通知详情失败：" + e.getMessage());
            }

            @Override
            public void onResponse(String json) {
                hideWaitDialog();
                LogUtil.e("通知详情成功：" + json);

                Gson gson = new Gson();
                final TongZhiInfo_Bean bean = gson.fromJson(json, TongZhiInfo_Bean.class);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bean.getCode() == 20041){
                            String text = bean.getData().getShequ_text();
                            RichText.initCacheDir(TongZhiInfo_Activity.this);
                            RichText.from(text).into(mContent_tv);
                        }else {
                            Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });


            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTitle_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
