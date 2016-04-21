package com.ayd.rhcf.utils;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by gqy on 2016/3/14.
 */
public class WvSettings {

    public static abstract class IWvCallBack {
        public abstract void onProgressChanged(int newProgress);
    }

    public static void setting(final WebView mWebView, final IWvCallBack callBack) {
        mWebView.setInitialScale(100);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置WebView可触摸放大缩小
        webSettings.setBuiltInZoomControls(true);
        //支持缩放
        webSettings.setSupportZoom(true);

        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);//自适应屏幕：

//        webSettings.setAllowFileAccess(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDatabaseEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (callBack != null) {
                    callBack.onProgressChanged(newProgress);
                }
            }
        });
    }
}
