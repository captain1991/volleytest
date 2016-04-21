package com.ayd.rhcf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.R;

/**
 * 闪屏页；
 * created by gqy on 2016/2/22
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.Splash_REQ_TAG};
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }


    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }
}
