package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.ToastUtil;

/**
 * 注册页；
 * created by gqy on 2016/2/23
 */
public class RegistrationActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtUname;
    private EditText mEtYzm;
    private EditText mEtPwd;

    private Button mBtnGetYzm;
    private Button mBtnRegist;
    private CheckBox mCheckbox;
    private TextView tv_notice_sub1;
    private TextView tv_notice_sub2;

    private ImageView mIvClear;
    private ImageView mIvEye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assignViews();
    }
    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.Registration_REQ_TAG};
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_registration;
    }

    private void assignViews() {
        mEtUname = (EditText) findViewById(R.id.et_uname);
        mEtUname.addTextChangedListener(textWatcher);

        mEtYzm = (EditText) findViewById(R.id.et_yzm);
        mBtnGetYzm = (Button) findViewById(R.id.btn_get_yzm);
        mBtnGetYzm.setOnClickListener(this);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);

        mIvClear = (ImageView) findViewById(R.id.iv_clear);
        mIvClear.setVisibility(View.INVISIBLE);
        mIvClear.setOnClickListener(this);

        mIvEye = (ImageView) findViewById(R.id.iv_eye);
        mIvEye.setTag(R.drawable.eye1);
        mIvEye.setOnClickListener(this);

        mBtnRegist = (Button) findViewById(R.id.btn_regist);
        mBtnRegist.setOnClickListener(this);
        mCheckbox = (CheckBox) findViewById(R.id.checkbox);
        tv_notice_sub1 = (TextView) findViewById(R.id.tv_notice_sub1);
        tv_notice_sub2 = (TextView) findViewById(R.id.tv_notice_sub2);

        tv_notice_sub1.setOnClickListener(this);
        tv_notice_sub2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_yzm:
                ToastUtil.showToastShort(this, "获取验证码");
                break;
            case R.id.btn_regist:
                ToastUtil.showToastShort(this, "注册");
                break;
            case R.id.tv_notice_sub1:
                mCheckbox.setChecked(!mCheckbox.isChecked());
                break;
            case R.id.tv_notice_sub2:
                ToastUtil.showToastShort(this, "协议");
                break;
            case R.id.iv_clear:
                mEtUname.setText("");
                break;
            case R.id.iv_eye:
                updateEyeState();
                break;
        }
    }

    /**
     * 更新眼睛的状态和密码的显示和隐藏；
     */
    private void updateEyeState() {
        int id = (Integer) mIvEye.getTag();
        if (id == R.drawable.eye1) {
            //显示密码；
            mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mIvEye.setBackgroundResource(R.drawable.eye2);
            mIvEye.setTag(R.drawable.eye2);
        } else {
            mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mIvEye.setBackgroundResource(R.drawable.eye1);
            mIvEye.setTag(R.drawable.eye1);
        }
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
        return getString(R.string.text_regist2);
    }

    @Override
    protected String getTitleBarRightText() {
        return getString(R.string.text_has_account);
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    protected void titleBarRightClick() {
        readyGoThenFinish(this, LoginActivity.class);
    }

}
