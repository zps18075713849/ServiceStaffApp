package com.haitian.servicestaffapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haitian.servicestaffapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                skipLoginOrMain();
            }
        }, 2000);
    }

    private void skipLoginOrMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
