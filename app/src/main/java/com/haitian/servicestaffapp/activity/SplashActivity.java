package com.haitian.servicestaffapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haitian.servicestaffapp.R;
import com.haitian.servicestaffapp.app.Constants;
import com.haitian.servicestaffapp.app.DoctorBaseAppliction;

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
        String user_id = DoctorBaseAppliction.spUtil.getString(Constants.USERID, "");
        if (!user_id.equals("") && !user_id.isEmpty()) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(SplashActivity.this, Login_Activity.class));
            finish();
        }
    }
}
