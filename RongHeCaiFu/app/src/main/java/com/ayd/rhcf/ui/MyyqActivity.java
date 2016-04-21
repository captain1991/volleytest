package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.MyYqAdapter;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;

/**
 * Created by gqy on 2016/2/29.
 * 我的邀请Fragment；
 */
public class MyyqActivity extends BaseActivity implements PtrCallBack, AdapterView.OnItemClickListener {
    private PullToRefreshLayout refreshLayout;
    private ListView refreshListView;
    private MyYqAdapter adapter;
    private View headerView;

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
        return R.layout.activity_myyq;
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.Myyq_REQ_TAG};
    }

    private void init() {
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PtrRefreshRegister.regist(refreshLayout, this);

        refreshListView = (ListView) refreshLayout.getPullableView();
        headerView = LayoutInflater.from(this).inflate(R.layout.headerview_myyq, null);

        refreshListView.addHeaderView(headerView);

        refreshListView.setFooterDividersEnabled(false);
        refreshListView.setHeaderDividersEnabled(false);
        adapter = new MyYqAdapter(this);
        refreshListView.setAdapter(adapter);
        refreshListView.setOnItemClickListener(this);
        //首次自动刷新；
//        refreshLayout.autoRefresh();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
        return getString(R.string.text_myyq);
    }
}
