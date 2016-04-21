package com.ayd.rhcf.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spanned;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.TvInit;
import com.ayd.rhcf.view.AnimProgressBar;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;
import com.ayd.rhcf.view.pulltorefresh.PullableScrollView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 理财产品详情页；
 * created by gqy on 2016/3/1
 */
public class LccpDetailActivity extends BaseActivity implements ClickEventCallBack, PtrCallBack {
    private TextView mTvXmName;

    private TextView mTvXmze;
    private TextView mTvNhsy;

    private AnimProgressBar mProgressbar;
    private TextView mProgressValue;

    private TextView mTvXmqx;
    private TextView mTvHkfs;
    private TextView mTvSysj;

    private LinearLayout mPanelXmxx;
    private LinearLayout mPanelFkxx;
    private LinearLayout mPanelTzjl;
    private LinearLayout mPanelHkjh;
    private PullToRefreshLayout refreshLayout;

    private Button mBtnLjtz;
    private ScheduledExecutorService ses;
    private static int sysjTimeIns = 0; //剩余时间；

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.LccpDetail_REQ_TAG};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    private void updateTimeLabel() {
        if (sysjTimeIns <= 0) {
            sysjTimeIns = 0;
        }
        CommonUtil.updateTimeLabel(mTvSysj, sysjTimeIns);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_lccp_detail;
    }

    // 进度更新的Handler；
    private Handler progressUpdateHandler = new Handler();

    private void init() {
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PtrRefreshRegister.regist(refreshLayout, this);
        refreshLayout.setPullUpEnable(false);

        mTvXmName = (TextView) findViewById(R.id.tv_xm_name);
        mTvXmze = (TextView) findViewById(R.id.tv_xmze);
        mTvNhsy = (TextView) findViewById(R.id.tv_nhsy);

        mProgressValue = (TextView) findViewById(R.id.progress_value);
        mProgressbar = (AnimProgressBar) findViewById(R.id.progressbar);
        mProgressbar.setTvProgress(mProgressValue);
        progressUpdateHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressbar.setCurAnimProgress(80);
            }
        }, 500);

        mTvXmqx = (TextView) findViewById(R.id.tv_xmqx);
        mTvHkfs = (TextView) findViewById(R.id.tv_hkfs);
        mTvSysj = (TextView) findViewById(R.id.tv_sysj);

        mPanelXmxx = (LinearLayout) findViewById(R.id.panel_xmxx);
        ClickListenerRegister.regist(mPanelXmxx, this);
        mPanelFkxx = (LinearLayout) findViewById(R.id.panel_fkxx);
        ClickListenerRegister.regist(mPanelFkxx, this);

        mPanelTzjl = (LinearLayout) findViewById(R.id.panel_tzjl);
        ClickListenerRegister.regist(mPanelTzjl, this);
        mPanelHkjh = (LinearLayout) findViewById(R.id.panel_hkjh);
        ClickListenerRegister.regist(mPanelHkjh, this);

        mBtnLjtz = (Button) findViewById(R.id.btn_ljtz);
        ClickListenerRegister.regist(mBtnLjtz, this);
        updateTimeLabel();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateTimeLabel();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                // 运行在非UI线程中；
                handler.sendEmptyMessage(0x1122);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void initTv(String content) {
        TvInit.set(mTvXmName, "");

        TvInit.set(mTvXmze, "");
        TvInit.set(mTvNhsy, "");
        mProgressbar.setProgress(0);
        TvInit.set(mProgressValue, "");

        TvInit.set(mTvXmqx, "");
        TvInit.set(mTvHkfs, "");
        TvInit.set(mTvSysj, "");
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mPanelXmxx.getId()) {
            Bundle bundle = new Bundle();
            bundle.putString("", "");
            readyGoWithBundle(this, LiCai_XmxxActivity.class, bundle);
        } else if (viewId == mPanelFkxx.getId()) {
            Bundle bundle = new Bundle();
            bundle.putString("", "");
            readyGoWithBundle(this, LiCai_FkxxActivity.class, bundle);
        } else if (viewId == mPanelTzjl.getId()) {
            Bundle bundle = new Bundle();
            bundle.putString("", "");
            readyGoWithBundle(this, LiCai_TzjlActivity.class, bundle);
        } else if (viewId == mPanelHkjh.getId()) {
            Bundle bundle = new Bundle();
            bundle.putString("", "");
            readyGoWithBundle(this, HkjhActivity.class, bundle);
        } else if (viewId == mBtnLjtz.getId()) {

            Bundle bundle = new Bundle();
            bundle.putString("", "");
            readyGoWithBundle(this, QrtzActivity.class, bundle);
        }
    }

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_cpxx);
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected void onDestroy() {
        if (ses != null && !ses.isShutdown()) {
            ses.shutdown();
        }
        super.onDestroy();
    }
}
