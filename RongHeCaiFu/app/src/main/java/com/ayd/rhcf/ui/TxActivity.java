package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.HRCallBack;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.OwnTextWatcher;
import com.ayd.rhcf.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

public class TxActivity extends BaseActivity implements ClickEventCallBack, HRCallBack {
    private LinearLayout mPanelYhk;
    private TextView mTvYhkh;

    private EditText mEtTxje;

    private TextView mTvSsf;
    private TextView mTvKyye;
    private Button mBtnLktx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.Tx_REQ_TAG};
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tx;
    }

    private void init() {
        mPanelYhk = (LinearLayout) findViewById(R.id.panel_yhk);
        ClickListenerRegister.regist(mPanelYhk, this);
        mTvYhkh = (TextView) findViewById(R.id.tv_yhkh);

        mEtTxje = (EditText) findViewById(R.id.et_txje);
        mEtTxje.addTextChangedListener(new OwnTextWatcher() {
            @Override
            public void textChange(CharSequence text) {
                String text_ = text.toString().trim();

                LogUtil.i("===========>" + text_);

                //超过小数点后2位；
                if (CommonUtil.verifyMore2NumAfterPoint(text)) {
                    int pointIndex = text_.indexOf(".");
                    mEtTxje.setText(text_.substring(0, pointIndex + 3));
                    mEtTxje.setSelection(mEtTxje.getText().toString().length());
                }
            }
        });

        mTvSsf = (TextView) findViewById(R.id.tv_ssf);
        mTvKyye = (TextView) findViewById(R.id.tv_kyye);
        mTvKyye.setText(getString(R.string.text_kyye_hint, 0.00f));

        mBtnLktx = (Button) findViewById(R.id.btn_lktx);
        ClickListenerRegister.regist(mBtnLktx, this);
    }

    private static final int NET_TX_GETYHK_RESULT_CODE = 0;
    private static final int NET_TX_LKTX_RESULT_CODE = 1;

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mPanelYhk.getId()) {//银行卡；
//            showWDialog(AppConstants.TX_GETYHK_REQ_CODE, getString(R.string.text_getting_yhk_list));
            httpResponse(NET_TX_GETYHK_RESULT_CODE);

        } else if (viewId == mBtnLktx.getId()) { //立即提现；
            exeTx();
        }
    }

    public void showWDialog(int reqCode, String msg) {
        WaitingDialog wDialog = WaitingDialog.getInstance().setMsg(msg);
        wDialog.show(getSupportFragmentManager(), "WaitingDialog");
    }

    /**
     * 对话框被取消的回调；
     * 取消数据请求；
     */
    public void dialogCanceled(int resultCode) {
        switch (resultCode) {
            case AppConstants.DIALOG_TX_GETYHK_RESULT_CODE://获取银行卡信息请求取消；

                break;
            case AppConstants.DIALOG_TX_LKTX_RESULT_CODE://立刻提现信息请求取消；

                break;
        }
    }

    /**
     * Http响应；
     *
     * @param resultCode   不同的结果码代表不同的请求；
     * @param responseData
     */
    @Override
    public void httpResponse(int resultCode, Object... responseData) {
        switch (resultCode) {
            case NET_TX_GETYHK_RESULT_CODE:
                final String[] array = {"111", "222", "111"};
                ShowListStrDialog dialog = new ShowListStrDialog();
                dialog.setItems(array, new ShowListStrDialog.DialogItemClick() {
                    @Override
                    public void itemClick(int position) {
                        ToastUtil.showToastLong(TxActivity.this, position + "");
                    }
                }).show(getSupportFragmentManager(), "YHK_DIALOG_TAG");

                break;
            case NET_TX_LKTX_RESULT_CODE://立刻提现；

                break;
        }
    }


    /**
     * 执行提现；
     */
    private void exeTx() {
        String je = mEtTxje.getText().toString().trim();
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

        showWDialog(AppConstants.TX_LKTX_REQ_CODE, getString(R.string.text_zztj));
        Map<String, String> map = new HashMap<>();
//            HttpProxy.getDataByPost(this, NET_TAG_TX_LKTX, map, this, NET_TX_LKTX_RESULT_CODE);
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
        return getString(R.string.text_tx);
    }
}
