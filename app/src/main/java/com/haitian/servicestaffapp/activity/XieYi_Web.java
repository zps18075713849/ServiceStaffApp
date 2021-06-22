package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.base.BaseActivity;
import com.haitian.servicestaffapp.utils.LogUtil;


public class XieYi_Web extends BaseActivity {


    private ImageView mTitle_back;
    private TextView mTitle_name;
    private WebView mWeb_id;
    private String mTotalbarName;
    private String mWebUrl;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_yin_si__web;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mTitle_back = findViewById(R.id.title_back);
        mTitle_name = findViewById(R.id.title_content);
        mWeb_id = findViewById(R.id.web_id);

        mTitle_back.setVisibility(View.VISIBLE);
        mTitle_name.setVisibility(View.VISIBLE);

        mTotalbarName = getIntent().getStringExtra("totalbarName");
        mWebUrl = getIntent().getStringExtra("webUrl");

        mTitle_name.setText(mTotalbarName);

        intentWebView();
    }

    private void intentWebView() {

//        LogUtil.e(url+"000");
        WebSettings settings = mWeb_id.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setDisplayZoomControls(true);

        mWeb_id.setWebViewClient(new WebViewClient());
        mWeb_id.loadUrl(mWebUrl);
        mWeb_id.setWebChromeClient(new WebChromeClient());

        LogUtil.e(mWebUrl);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mTitle_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWeb_id.canGoBack()) {
                    mWeb_id.goBack();
                }
                else{
                    finish();
                }
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }



    //android webview点击返回键返回上一个html
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWeb_id.canGoBack()) {
            mWeb_id.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
