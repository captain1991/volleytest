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
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.OwnTextWatcher;
import com.ayd.rhcf.utils.ToastUtil;

/**
 * Created by gqy on 2016/2/29.
 * 充值Fragment；
 */
public class CzActivity extends BaseActivity implements ClickEventCallBack {
    private EditText mEtJe;
    private TextView mTvSjdz;
    private Button mBtnCz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mEtJe = (EditText) findViewById(R.id.et_je);
        mEtJe.addTextChangedListener(new OwnTextWatcher() {
            @Override
            public void textChange(CharSequence text) {
                String text_ = text.toString().trim();

                LogUtil.i("===========>" + text_);

                //超过小数点后2位；
                if (CommonUtil.verifyMore2NumAfterPoint(text)) {
                    int pointIndex = text_.indexOf(".");
                    mEtJe.setText(text_.substring(0, pointIndex + 3));
                    mEtJe.setSelection(mEtJe.getText().toString().length());
                }
            }
        });
        mTvSjdz = (TextView) findViewById(R.id.tv_sjdz);
        mBtnCz = (Button) findViewById(R.id.btn_cz);
        ClickListenerRegister.regist(mBtnCz, this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_cz;
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mBtnCz.getId()) {
            exeCz();
        }
    }

    private void exeCz() {
        String je = mEtJe.getText().toString().trim();
        LogUtil.i("je---->>" + je);

        if (CommonUtil.isEmpty(je)) {
            ToastUtil.showToastShort(this, R.string.not_allowed_null);
            return;
        }

        //数据格式的合法性验证；
        if (!CommonUtil.verifyDataFormat(je)) {
            ToastUtil.showToastShort(this, R.string.text_error_data_format);
            return;
        }

        //验证是50的倍数；?????????
        try {
            //余数；
            int ysNumber = (int) (Double.parseDouble(je) % 50);
            if (ysNumber != 0) {
                ToastUtil.showToastShort(this, R.string.text_number_50ys);
                return;
            }
            double zsNumber = Double.parseDouble(je);

            //投资金额不能为0；
            if (zsNumber < 50.00) {
                ToastUtil.showToastShort(this, R.string.text_number_dy50);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.showToastShort(this, R.string.text_number_50ys);
            return;
        }

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
        return getString(R.string.text_cz);
    }


    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.CZ_REQ_TAG};
    }
}
