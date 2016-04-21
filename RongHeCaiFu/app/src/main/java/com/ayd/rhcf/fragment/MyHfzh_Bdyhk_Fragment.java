package com.ayd.rhcf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ayd.rhcf.MyEventCallBack;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.MyBdyhkAdapter;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.ToastUtil;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;

/**
 * 账户-我的汇付账号-绑定银行卡Fragment；
 * created by gqy on 2016/3/8
 */
public class MyHfzh_Bdyhk_Fragment extends BaseLazyFragment implements PtrCallBack,
        MyEventCallBack {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private PullToRefreshLayout refreshLayout;
    private ListView refreshListView;
    private MyBdyhkAdapter adapter;

    public static MyHfzh_Bdyhk_Fragment newInstance(String param1, String param2) {
        MyHfzh_Bdyhk_Fragment fragment = new MyHfzh_Bdyhk_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MyHfzh_Bdyhk_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myhfzh_bdyhk, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        refreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshListView = (ListView) refreshLayout.getPullableView();
        refreshListView.setFooterDividersEnabled(false);
        refreshListView.setHeaderDividersEnabled(false);

        adapter = new MyBdyhkAdapter(getActivity());
        adapter.setAadapterEventCallBack(this);
        refreshListView.setAdapter(adapter);
        PtrRefreshRegister.regist(refreshLayout, this);
    }

    @Override
    public void adapterEventCallBack(Object... args) {
        int position = (int) args[0];
        ToastUtil.showToastShort(getActivity(), position + "");
    }

    /**
     * 第一次显示，联网加载数据；
     */
    @Override
    protected void onFirstUserVisible() {
//        refreshLayout.autoRefresh();
        LogUtil.i("onFirstUserVisible调用了");
    }

    /*获取下拉刷新时的数据*/
    @Override
    public void onRefresh() {
        LogUtil.i("onRefresh调用了");
    }

    @Override
    public void onLoadMore() {

    }

}
