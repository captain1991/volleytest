package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.TzjlAdapter;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;

/**
 * 理财产品详情-投资记录；
 * created by gqy on 2016/3/2
 */
public class LiCai_TzjlActivity extends BaseActivity implements ClickEventCallBack,
        PtrCallBack, AdapterView.OnItemClickListener {
    private PullToRefreshLayout refreshLayout;
    private ListView refreshListView;
    private TzjlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.LiCai_Tzjl_REQ_TAG};
    }

    @Override
    protected Activity getUmengAnalyzeContext() {
        return this;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_li_cai_tzjl;
    }

    private void init() {
        refreshLayout = (PullToRefreshLayout) findViewById(R.id.refreshLayout);
        PtrRefreshRegister.regist(refreshLayout, this);
        refreshListView = (ListView) refreshLayout.getPullableView();
        refreshListView.setFooterDividersEnabled(false);
        refreshListView.setHeaderDividersEnabled(false);
        adapter = new TzjlAdapter(this);

        refreshListView.setAdapter(adapter);
        refreshListView.setOnItemClickListener(this);
        //首次自动刷新；
//        refreshLayout.autoRefresh();
    }

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_tzjl);
    }

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    @Override
    public void clickEventCallBack(int viewId) {
//        if (viewId == mBtnLjtz.getId()) {
//            ToastUtil.showToastShort(this, "投资");
//        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
