package com.ayd.rhcf.ui;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
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
import com.ayd.rhcf.utils.TvInit;

/**
 * 确认投资；
 * created by gqy on 2016/3/2
 */
public class QrtzActivity extends BaseActivity implements ClickEventCallBack {
    private TextView mTvYjzsy;
    private EditText mEtTzje;
    private TextView mTvKtje;
    private TextView mTvCz;
    private Button mBtnCz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.Qrtz_REQ_TAG};
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_qrtz;
    }

    private void init() {
        mTvYjzsy = (TextView) findViewById(R.id.tv_yjzsy);
        mEtTzje = (EditText) findViewById(R.id.et_tzje);

        mEtTzje.addTextChangedListener(new OwnTextWatcher() {
            @Override
            public void textChange(CharSequence text) {
                String text_ = text.toString().trim();

                LogUtil.i("===========>" + text_);

                //超过小数点后2位；
                if (CommonUtil.verifyMore2NumAfterPoint(text)) {
                    int pointIndex = text_.indexOf(".");
                    mEtTzje.setText(text_.substring(0, pointIndex + 3));
                    mEtTzje.setSelection(mEtTzje.getText().toString().length());
                }
            }
        });

        mTvKtje = (TextView) findViewById(R.id.tv_ktje);
        mTvCz = (TextView) findViewById(R.id.tv_cz);
        //添加下划线；
        mTvCz.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mBtnCz = (Button) findViewById(R.id.btn_cz);
        ClickListenerRegister.regist(mBtnCz, this);
    }

    private void initContent(String content) {
        String yjzsy = "";
        String ktje = getString(R.string.text_ssf_hint);

        if (CommonUtil.isNotEmpty(content)) {
            yjzsy = "";
            ktje = "";
        }
        TvInit.set(mTvYjzsy, yjzsy);
        TvInit.set(mTvKtje, ktje);
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.qztz);
    }

    @Override
    protected boolean isTitleBarRightVisible() {
        return false;
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (mBtnCz.getId() == viewId) {
            exeTz();
        }
    }

    private void exeTz() {
        String je = mEtTzje.getText().toString().trim();
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

        if (CommonUtil.verifyNoZero(je)) {
            ToastUtil.showToastShort(this, R.string.text_number_dy0);
            return;
        }

        // 通过；
    }
}
