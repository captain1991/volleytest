package com.ayd.rhcf.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.HRCallBack;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.PtrRefreshRegister;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.LicaiChanPinAdapter;
import com.ayd.rhcf.bean.TestBean;
import com.ayd.rhcf.ui.GywmActivity;
import com.ayd.rhcf.ui.LccpDetailActivity;
import com.ayd.rhcf.ui.PtggActivity;
import com.ayd.rhcf.ui.SyjsActivity;
import com.ayd.rhcf.ui.YqhyActivity;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.utils.UmsUtil;
import com.ayd.rhcf.view.SlideShowView;
import com.ayd.rhcf.view.pulltorefresh.PullToRefreshLayout;
import com.ayd.rhcf.view.pulltorefresh.PullableListView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 首页Fragment；
 * created by gqy on 2016/2/23
 */
public class ShouyeFragment extends BaseFragment implements PtrCallBack, ClickEventCallBack,
        AdapterView.OnItemClickListener, HRCallBack {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private LinearLayout mPanelGywm;
    private LinearLayout mPanelPtgg;
    private LinearLayout mPanelYqhy;
    private LinearLayout mPanelSyjs;
    private PullToRefreshLayout refreshLayout;
    private ListView refreshListView;
    private SlideShowView mSlideShowView;
    private LicaiChanPinAdapter adapter = null;
    private ScheduledExecutorService ses;

    public static ShouyeFragment newInstance(String param1, String param2) {
        ShouyeFragment fragment = new ShouyeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ShouyeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        Log.i("SlideShowView", "SlideShowView");

        mSlideShowView = (SlideShowView) view.findViewById(R.id.slideShowView);
        initSlideShowView();
        mPanelGywm = (LinearLayout) view.findViewById(R.id.panel_gywm);
        mPanelPtgg = (LinearLayout) view.findViewById(R.id.panel_ptgg);
        mPanelYqhy = (LinearLayout) view.findViewById(R.id.panel_yqhy);
        mPanelSyjs = (LinearLayout) view.findViewById(R.id.panel_syjs);
        ClickListenerRegister.regist(mPanelGywm, this);
        ClickListenerRegister.regist(mPanelPtgg, this);
        ClickListenerRegister.regist(mPanelYqhy, this);
        ClickListenerRegister.regist(mPanelSyjs, this);

        refreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refreshLayout);
        PtrRefreshRegister.regist(refreshLayout, this);


        refreshListView = (ListView) refreshLayout.getPullableView();
        refreshListView.setFooterDividersEnabled(false);
        refreshListView.setHeaderDividersEnabled(false);
        adapter = new LicaiChanPinAdapter(activity);

        refreshListView.setAdapter(adapter);
        refreshListView.setOnItemClickListener(this);

        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new UpdateAdapterTask(), 1, 1, TimeUnit.SECONDS);
        refreshLayout.setPullDownEnable(true);
        refreshLayout.setPullUpEnable(true);
        refreshLayout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
           @Override
           public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
               LogUtil.i("onRefresh");
               refreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
           }

           @Override
           public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
               LogUtil.i("onLoadMore");
               refreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
           }
        });
        //首次自动刷新；
//        refreshLayout.autoRefresh();
        initData();
    }

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

    private static final int MSG_WHAT = 0x1111;

    private class UpdateAdapterTask implements Runnable {
        @Override
        public void run() {
            shouYeHandler.sendEmptyMessage(MSG_WHAT);
        }
    }

    private void initSlideShowView() {
        ViewGroup.LayoutParams vpLp = mSlideShowView.getLayoutParams();
        vpLp.height = (int) (0.5 + CommonUtil.getScreenWidth(activity) / AppConstants.VP_IMG_WH_SCALE);
        mSlideShowView.setLayoutParams(vpLp);

        List<String> urlList = new ArrayList<>();
        urlList.add("http://img1.imgtn.bdimg.com/it/u=3573202674,1863124697&fm=21&gp=0.jpg");
        urlList.add("http://img1.imgtn.bdimg.com/it/u=3573202674,1863124697&fm=21&gp=0.jpg");
        urlList.add("http://img1.imgtn.bdimg.com/it/u=3573202674,1863124697&fm=21&gp=0.jpg");
        urlList.add("http://img1.imgtn.bdimg.com/it/u=3573202674,1863124697&fm=21&gp=0.jpg");
        mSlideShowView.setImageDataList(urlList);
    }

    @Override
    public void onRefresh() {
//        HttpProxy.getDataByGet(activity, "", AppConstants.SHOUYE_REQ_TAG, null, this, 101);
    }

    @Override
    public void httpResponse(int resultCode, Object... responseData) {

    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (mPanelGywm.getId() == viewId) {
            readyGo(activity, GywmActivity.class);
//            UmsUtil.openShareBoad(getActivity());
        } else if (mPanelPtgg.getId() == viewId) {
            UmsUtil.goShouquan(getActivity(), SHARE_MEDIA.QQ);
//            readyGo(activity, PtggActivity.class);
        } else if (mPanelYqhy.getId() == viewId) {
            readyGo(activity, YqhyActivity.class);
        } else if (mPanelSyjs.getId() == viewId) {
            readyGo(activity, SyjsActivity.class);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        readyGoWithBundle(activity, LccpDetailActivity.class, bundle);
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{AppConstants.SHOUYE_REQ_TAG};
    }

    @Override
    public void onDestroy() {
        if (ses != null && !ses.isShutdown()) {
            ses.shutdown();
        }
        mSlideShowView.stopPlay();
        super.onDestroy();
    }

}
