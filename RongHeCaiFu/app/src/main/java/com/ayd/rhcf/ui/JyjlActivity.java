package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.JyjlListAdapter;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;

/**
 * Created by gqy on 2016/2/29.
 * 交易记录t；
 */
public class JyjlActivity extends BaseActivity implements PtrCallBack {
    private PullToRefreshLayout refreshLayout;
    private ListView mListView;
    private JyjlListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    private void init() {
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PtrRefreshRegister.regist(refreshLayout, this);
        mListView = (ListView) refreshLayout.getPullableView();
        adapter = new JyjlListAdapter(this);
        mListView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_jyjl;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

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
        return getString(R.string.text_jyjl);
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.JYJL_REQ_TAG};
    }
}
