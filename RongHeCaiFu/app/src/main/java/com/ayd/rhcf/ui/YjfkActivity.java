package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;

/**
 * created by gqy on 2016/2/26
 * 意见反馈；
 */
public class YjfkActivity extends BaseActivity implements ClickEventCallBack {
    private TextView mTvKfrx;
    private Button mBtnSend;
    private EditText mEtFk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.Yjfk_REQ_TAG};
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    private void init() {
        mEtFk = (EditText) findViewById(R.id.et_fk);
        mTvKfrx = (TextView) findViewById(R.id.tv_kfrx);
        ClickListenerRegister.regist(mTvKfrx, this);
        mBtnSend = (Button) findViewById(R.id.btn_send);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_yjfk;
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    protected boolean isTitleBarRightVisible() {
        return false;
    }


    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_yjfk);
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mTvKfrx.getId()) {
            CommonUtil.dialNumber(this, getString(R.string.text_kfrx_number));
        } else if (viewId == mBtnSend.getId()) {
        }
    }
}
