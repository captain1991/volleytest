package com.ayd.rhcf.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;

/**
 * 账户-我的汇付账号-电子交易账户Fragment；
 * created by gqy on 2016/3/8
 */
public class MyHfzh_Dzjyzh_Fragment extends BaseLazyFragment implements PtrCallBack {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private PullToRefreshLayout mRefreshLayout;
    private TextView mTvHftxh;
    private TextView mTvZje;
    private TextView mTvKyje;
    private TextView mTvDjje;
    private TextView mTvNotice;

    public static MyHfzh_Dzjyzh_Fragment newInstance(String param1, String param2) {
        MyHfzh_Dzjyzh_Fragment fragment = new MyHfzh_Dzjyzh_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MyHfzh_Dzjyzh_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_myhfzh_dzjyzh, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        mRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refreshLayout);
        mRefreshLayout.setPullUpEnable(true);
        PtrRefreshRegister.regist(mRefreshLayout, this);
        mTvHftxh = (TextView) view.findViewById(R.id.tv_hftxh);
        mTvZje = (TextView) view.findViewById(R.id.tv_zje);
        mTvKyje = (TextView) view.findViewById(R.id.tv_kyje);
        mTvDjje = (TextView) view.findViewById(R.id.tv_djje);
        mTvNotice = (TextView) view.findViewById(R.id.tv_notice);
        mTvNotice.setText(Html.fromHtml("您的账户中尚有" + "元未同步余额，请点击" +
                "<a href='http://www.baidu.com'>立即同步</a>" + "，同步手续费由平台支付"));

        mTvNotice.setMovementMethod(new LinkMovementMethod());
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
