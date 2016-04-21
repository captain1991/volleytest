package com.ayd.rhcf.ui;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.HkjhListAdapter;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;

/**
 * Created by gqy on 2016/2/29.
 * 回款计划；
 */
public class HkjhActivity extends BaseActivity implements PtrCallBack {
    private PullToRefreshLayout refreshLayout;
    private ListView mListView;
    private HkjhListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_hkjh;
    }

    private void init() {
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PtrRefreshRegister.regist(refreshLayout, this);
        mListView = (ListView) refreshLayout.getPullableView();
        adapter = new HkjhListAdapter(this);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

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
    protected String getTitleBarTitle() {
        return getString(R.string.text_hkjh);
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.HKJH_REQ_TAG};
    }
}
