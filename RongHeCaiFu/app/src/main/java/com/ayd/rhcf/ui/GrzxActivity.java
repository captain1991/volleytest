package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.MyEventCallBack;
import com.ayd.rhcf.R;

/**
 * Created by gqy on 2016/2/27.
 * 个人中心Fragment；
 */
public class GrzxActivity extends BaseActivity implements ClickEventCallBack {
    private TextView mTvZh;
    private TextView mTvZsxm;
    private TextView mTvSfzh;
    private LinearLayout mPanelMyyhk;

    private LinearLayout mPanelXgdlmm;
    private Button mBtnLoginout;
    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_grzx;
    }

    private void init() {
        mTvZh = (TextView) findViewById(R.id.tv_zh);
        mTvZsxm = (TextView) findViewById(R.id.tv_zsxm);
        mTvSfzh = (TextView) findViewById(R.id.tv_sfzh);
        mPanelMyyhk = (LinearLayout) findViewById(R.id.panel_myyhk);
        ClickListenerRegister.regist(mPanelMyyhk, this);

        mPanelXgdlmm = (LinearLayout) findViewById(R.id.panel_xgdlmm);
        ClickListenerRegister.regist(mPanelXgdlmm, this);

        mBtnLoginout = (Button) findViewById(R.id.btn_loginout);
        ClickListenerRegister.regist(mBtnLoginout, this);
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mPanelXgdlmm.getId()) {//修改登录密码
            readyGo(this, ModifyLoginPwdActivity.class);
        } else if (viewId == mBtnLoginout.getId()) { //退出登录
            showNoticeDialog();
        }
    }

    private void showNoticeDialog() {
        NoticeDialog nd = new NoticeDialog();
        nd.setTitle(getString(R.string.text_tc))
                .setContent(getString(R.string.loginout_notice))
                .setNoLabel(getString(R.string.text_qx))
                .setYesLabel(getString(R.string.text_yes));
        nd.setCallBack(new MyEventCallBack() {
            @Override
            public void adapterEventCallBack(Object... args) {
                if (args != null && args.length > 0) {
                    if (args[0] instanceof Integer) {
                        int index = (int) args[0];
                        if (index == 1) {
                            // 退出；
                        }
                    }
                }
            }
        });
        nd.show(getSupportFragmentManager(), "LoginOutDialog");
    }

    @Override
    protected boolean isTitleBarRightVisible() {
        return false;
    }

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_grzx);
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.GRZX_REQ_TAG};
    }
}
