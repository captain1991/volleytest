package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.ToastUtil;

/**
 * 找回密码；
 * created by gqy on 2016/2/23
 */
public class FindBackPwdActivity extends BaseActivity implements ClickEventCallBack {
    private EditText mEtPhoneNum;
    private EditText mEtYzm;
    private Button mBtnGetYzm;
    private Button mBtnNext;
    private ImageView mIvClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assignViews();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_find_pwd_back;
    }

    private void assignViews() {
        mEtPhoneNum = (EditText) findViewById(R.id.et_phonenumber);
        mEtPhoneNum.addTextChangedListener(textWatcher);

        mIvClear = (ImageView) findViewById(R.id.iv_clear);
        mIvClear.setVisibility(View.INVISIBLE);
        ClickListenerRegister.regist(mIvClear, this);

        mEtYzm = (EditText) findViewById(R.id.et_yzm);
        mBtnGetYzm = (Button) findViewById(R.id.btn_get_yzm);
        ClickListenerRegister.regist(mBtnGetYzm, this);

        mBtnNext = (Button) findViewById(R.id.btn_next);
        ClickListenerRegister.regist(mBtnNext, this);
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == R.id.btn_get_yzm) {
            ToastUtil.showToastShort(this, "获取验证码");
        } else if (viewId == R.id.iv_clear) {
            mEtPhoneNum.setText("");
        } else if (viewId == R.id.btn_next) {
            nextStep();
        }
    }

    /**
     * 下一步；
     */
    private void nextStep() {
        String phoneNum = mEtPhoneNum.getText().toString().trim();
        String yzm = mEtYzm.getText().toString().trim();

        LogUtil.i("phoneNum==>" + phoneNum + ",yzm=>" + yzm);

        if (CommonUtil.isEmpty(phoneNum)) {
            ToastUtil.showToastShort(this, R.string.sjh_not_null);
            return;
        }
        // 手机号段验证；
        if (!CommonUtil.isMobilePhone(phoneNum)) {
            ToastUtil.showToastShort(this, R.string.sjh_format_error);
            return;
        }
        if (CommonUtil.isEmpty(yzm)) {
            ToastUtil.showToastShort(this, R.string.yzm_not_null);
            return;
        }
        readyGo(this,ResetPwdActivity.class);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s != null && CommonUtil.isNotEmpty(s.toString())) {
                mIvClear.setVisibility(View.VISIBLE);
            } else {
                mIvClear.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_findback_pwd);
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
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.FINDBACKPWD_REQ_TAG};
    }

}
