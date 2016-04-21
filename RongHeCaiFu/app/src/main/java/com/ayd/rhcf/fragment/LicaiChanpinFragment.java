package com.ayd.rhcf.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.LicaiChanPinAdapter;
import com.ayd.rhcf.bean.TestBean;
import com.ayd.rhcf.ui.LccpDetailActivity;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.ToastUtil;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 理财-理财产品-Fragment；
 * created by gqy on 2016/2/23
 */
public class LicaiChanpinFragment extends BaseLazyFragment implements PtrCallBack,
        AdapterView.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private PullToRefreshLayout refreshLayout;
    private ListView refreshListView;
    private LicaiChanPinAdapter adapter;
    private static final int MSG_WHAT = 0x1112;
    private ScheduledExecutorService ses;

    public static LicaiChanpinFragment newInstance(String param1, String param2) {
        LicaiChanpinFragment fragment = new LicaiChanpinFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LicaiChanpinFragment() {
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
        View view = inflater.inflate(R.layout.fragment_licai_chanpin, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        refreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshListView = (ListView) refreshLayout.getPullableView();
        refreshListView.setFooterDividersEnabled(false);
        refreshListView.setHeaderDividersEnabled(false);
//        LayoutInflater inflater = LayoutInflater.from(activity);
//        View refreshView = inflater.inflate(R.layout.refresh_head_custom, null);
//        View loadMoreView = inflater.inflate(R.layout.load_more_custom, null);
//        refreshLayout.setCustomRefreshView(refreshView);
//        refreshLayout.setCustomLoadmoreView(loadMoreView);
        refreshListView.setOnItemClickListener(this);
        adapter = new LicaiChanPinAdapter(getActivity());
        refreshListView.setAdapter(adapter);
        PtrRefreshRegister.regist(refreshLayout, this);
        initData();

        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new UpdateAdapterTask(), 1, 1, TimeUnit.SECONDS);
    }

    private class UpdateAdapterTask implements Runnable {
        @Override
        public void run() {
            shouYeHandler.sendEmptyMessage(MSG_WHAT);
        }
    }

    private Handler shouYeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (adapter != null) {
                List<TestBean> dataList = adapter.getDataList();
                if (dataList != null && dataList.size() > 0) {
                    adapter.scheduleTime();
                }
            }
        }
    };

    private void initData() {
         /*====================================测试数据*/
        List<TestBean> beans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestBean bean = new TestBean();
            beans.add(bean);
        }
        adapter.appendDataList(beans);

        adapter.notifyDataSetChanged();
        /*=========================================*/
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.showToastShort(getActivity(), "position=" + position);
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        readyGoWithBundle(getActivity(), LccpDetailActivity.class, bundle);
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.LICAI_LCCP_REQ_TAG};
    }

    @Override
    public void onDestroy() {
        if (ses != null &&!ses.isShutdown()) {
            ses.shutdown();
        }
        super.onDestroy();
    }
}
