package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.OwnTextWatcher;
import com.ayd.rhcf.utils.ToastUtil;

/**
 * 修改登录密码；
 * created by gqy on 2016/2/27
 */
public class ModifyLoginPwdActivity extends BaseActivity implements ClickEventCallBack {
    private EditText mEtOldPwd;
    private EditText mEtNewPwd1;
    private EditText mEtNewPwd2;
    private Button mBtnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.ModifyLoginPwd_REQ_TAG};
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_modify_login_pwd;
    }

    private void init() {
        mEtOldPwd = (EditText) findViewById(R.id.et_old_pwd);
        mEtNewPwd1 = (EditText) findViewById(R.id.et_new_pwd1);
        mEtNewPwd2 = (EditText) findViewById(R.id.et_new_pwd2);

        mBtnSure = (Button) findViewById(R.id.btn_sure);
        ClickListenerRegister.regist(mBtnSure, this);
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mBtnSure.getId()) {
            exe();
        }
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    private void exe() {
        String oldPwd = mEtOldPwd.getText().toString().trim();
        String newPwd1 = mEtNewPwd1.getText().toString().trim();
        String newPwd2 = mEtNewPwd2.getText().toString().trim();

        LogUtil.i("oldPwd--->" + oldPwd + ",newPwd1--->" + newPwd1 + ",newPwd2--->" + newPwd2);

        if (CommonUtil.isEmpty(oldPwd) || CommonUtil.isEmpty(newPwd1) || CommonUtil.isEmpty(newPwd2)) {
            ToastUtil.showToastShort(ModifyLoginPwdActivity.this, R.string.not_allowed_null);
            return;
        }

        if (!CommonUtil.verifyPointAndEn(oldPwd) || !CommonUtil.verifyPointAndEn(newPwd1)
                || !CommonUtil.verifyPointAndEn(newPwd2)) {
            ToastUtil.showToastShort(ModifyLoginPwdActivity.this, R.string.text_pwd_allow_chars);
            return;
        }

        if (!CommonUtil.verifyLen(oldPwd, 6) || !CommonUtil.verifyLen(newPwd1, 6)
                || !CommonUtil.verifyLen(newPwd2, 6)) {

            ToastUtil.showToastShort(ModifyLoginPwdActivity.this, R.string.text_verify_len);
            return;
        }

        if (!newPwd1.equals(newPwd2)) {
            ToastUtil.showToastShort(ModifyLoginPwdActivity.this, R.string.text_pwd_not_same);
            return;
        }


    }


    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_setting_login_pwd);
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
