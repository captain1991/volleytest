package com.ayd.rhcf.ui;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.ToastUtil;
import com.ayd.rhcf.utils.TvInit;

/**
 * 购买债券；
 * Created by gqy on 2016/3/2.
 */
public class GmzqActivity extends BaseActivity implements ClickEventCallBack {
    private TextView mTvZqzz;
    private TextView mTvGmjj;
    private TextView mTvKyje;
    private TextView mTvCz;
    private Button mBtnGm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    public void initTv(String content) {
        TvInit.set(mTvZqzz, "");
        TvInit.set(mTvGmjj, "");
        TvInit.set(mTvKyje, "");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gmzq;
    }

    private void init() {
        mTvZqzz = (TextView) findViewById(R.id.tv_zqzz);
        mTvGmjj = (TextView) findViewById(R.id.tv_gmjj);
        mTvKyje = (TextView) findViewById(R.id.tv_kyje);
        mTvCz = (TextView) findViewById(R.id.tv_cz);
        mTvCz.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        ClickListenerRegister.regist(mTvCz, this);

        mBtnGm = (Button) findViewById(R.id.btn_gm);
        ClickListenerRegister.regist(mBtnGm, this);
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mTvCz.getId()) {
            ToastUtil.showToastShort(this, "充值");
        } else if (viewId == mBtnGm.getId()) {
            ToastUtil.showToastShort(this, "购买");
        }
    }

    @Override
    protected boolean isTitleBarRightVisible() {
        return false;
    }

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_gmzq);
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.GMZQ_REQ_TAG};
    }

}
