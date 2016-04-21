package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.ToastUtil;
import com.ayd.rhcf.utils.WvSettings;

/**
 * Created by gqy on 2016/2/26.
 * 邀请好友Fragment；
 */
public class YqhyActivity extends BaseActivity {
    private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.Yqhy_REQ_TAG};
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_yqhy;
    }

    private void init() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWebView = (WebView) findViewById(R.id.webView);

        //此方法用在4.2以上系统并加上@JavascriptInterface注解时才具有安全性
        mWebView.addJavascriptInterface(this, "jsObject");

        String url = "https://www.douban.com/note/153676755/";
        WvSettings.setting(mWebView, new WvSettings.IWvCallBack() {

                    @Override
                    public void onProgressChanged(int newProgress) {
                        if (newProgress == 100) {
                            mProgressBar.setVisibility(View.GONE);
                        } else {
                            mProgressBar.setVisibility(View.VISIBLE);
                            mProgressBar.setProgress(newProgress);
                        }
                    }
                }
        );
        mWebView.loadUrl("file:///android_asset/jstest.html");
    }

    //在4.2以上系统并加上@JavascriptInterface注解时才具有安全性
    @JavascriptInterface
    public void jsCall() {
        ToastUtil.showToastLong(this, "调用了");
    }

    @Override
    protected boolean isTitleBarRightVisible() {
        return false;
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_yqhy);
    }
}
