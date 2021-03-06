package com.ayd.rhcf.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.PtrCallBack;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.VPAdapter;
import com.ayd.rhcf.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gqy on 2016/3/3.
 * 我的理财-直投项目Fragment；
 */
public class ZtxmFragment extends Fragment implements ClickEventCallBack, PtrCallBack {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private PagerSlidingTabStrip mVpIndicator;
    private ViewPager mViewPager;
    private VPAdapter vpAdapter = null;

    private String[] titles = null;
    private List<Fragment> fragments = null;

    public static ZtxmFragment newInstance(String param1, String param2) {
        ZtxmFragment fragment = new ZtxmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ZtxmFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ztxm, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        titles = getResources().getStringArray(R.array.ztxm_vp_titles);

        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(Ztxm_Tbz_Fragment.newInstance("", ""));
        fragments.add(Ztxm_Tbz_Fragment.newInstance("", ""));
        fragments.add(Ztxm_Tbz_Fragment.newInstance("", ""));

        mVpIndicator = (PagerSlidingTabStrip) view.findViewById(R.id.vpIndicator);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        vpAdapter = new VPAdapter(getChildFragmentManager());

        vpAdapter.setTitles(titles);
        vpAdapter.setFragments(fragments);
        mViewPager.setAdapter(vpAdapter);

        try {
            mVpIndicator.setViewPager(mViewPager);
        } catch (IllegalStateException e) {

        }
    }

    @Override
    public void clickEventCallBack(int viewId) {
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
