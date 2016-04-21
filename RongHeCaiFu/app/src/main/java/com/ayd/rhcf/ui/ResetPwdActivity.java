package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.ToastUtil;

/**
 * 密码重置；
 * created by gqy on 2016/2/23
 */
public class ResetPwdActivity extends BaseActivity implements ClickEventCallBack {
    private EditText mEtNewPwd1;
    private EditText mEtNewPwd2;
    private Button mBtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assignViews();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_reset_pwd;
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.ResetPwd_REQ_TAG};
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    private void assignViews() {
        mEtNewPwd1 = (EditText) findViewById(R.id.et_new_pwd1);
        mEtNewPwd2 = (EditText) findViewById(R.id.et_new_pwd2);
        mBtnOk = (Button) findViewById(R.id.btn_ok);
        ClickListenerRegister.regist(mBtnOk, this);
    }


    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == R.id.btn_ok) {
            submitPwd();
        }
    }

    private void submitPwd() {
        String newPwd1 = mEtNewPwd1.getText().toString().trim();
        String newPwd2 = mEtNewPwd2.getText().toString().trim();

        LogUtil.i("newPwd1==>" + newPwd1 + ",newPwd2=>" + newPwd2);

        if (CommonUtil.isEmpty(newPwd1) || CommonUtil.isEmpty(newPwd2)) {
            ToastUtil.showToastShort(this, R.string.pwd_not_null);
            return;
        }
        // 提交；
    }


    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_reset_pwd);
    }

    @Override
    protected boolean isTitleBarRightVisible() {
        return false;
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

}
