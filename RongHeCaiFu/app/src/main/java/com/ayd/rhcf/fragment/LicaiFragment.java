package com.ayd.rhcf.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.VPAdapter;
import com.ayd.rhcf.utils.LogUtil;
import com.ayd.rhcf.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * 理财Fragment；
 * created by gqy on 2016/2/23
 */
public class LicaiFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PagerSlidingTabStrip mVpIndicator;
    private ViewPager mViewPager;
    private VPAdapter vpAdapter = null;

    private String mParam1;
    private String mParam2;
    private String[] titles = null;
    private List<Fragment> fragments = null;

    public static LicaiFragment newInstance(String param1, String param2) {
        LicaiFragment fragment = new LicaiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LicaiFragment() {
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
        View view = inflater.inflate(R.layout.fragment_licai, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        titles = getResources().getStringArray(R.array.licai_titles);

        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(LicaiChanpinFragment.newInstance("", ""));
        fragments.add(LicaiZqzrFragment.newInstance("", ""));

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
    protected String[] getReqTagList() {
        return null;
    }
}
