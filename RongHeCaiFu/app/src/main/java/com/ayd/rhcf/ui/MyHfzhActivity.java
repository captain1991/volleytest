package com.ayd.rhcf.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.R;
import com.ayd.rhcf.adapter.VPAdapter;
import com.ayd.rhcf.fragment.LicaiChanpinFragment;
import com.ayd.rhcf.fragment.LicaiZqzrFragment;
import com.ayd.rhcf.fragment.MyHfzh_Bdyhk_Fragment;
import com.ayd.rhcf.fragment.MyHfzh_Dzjyzh_Fragment;
import com.ayd.rhcf.utils.ToastUtil;
import com.ayd.rhcf.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的汇付账号；
 * created by gqy on 2016/3/7
 */
public class MyHfzhActivity extends BaseActivity {
    private PagerSlidingTabStrip mVpIndicator;
    private ViewPager mViewPager;
    private VPAdapter vpAdapter = null;

    private String[] titles = null;
    private List<Fragment> fragments = null;

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
    protected String[] getReqTagList() {
        return new String[]{AppConstants.MyHfzh_REQ_TAG};
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_hfzh;
    }

    private void init() {
        titles = getResources().getStringArray(R.array.hfzh_manage_titles);

        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.add(MyHfzh_Dzjyzh_Fragment.newInstance("", ""));
        fragments.add(MyHfzh_Bdyhk_Fragment.newInstance("", ""));

        mVpIndicator = (PagerSlidingTabStrip) findViewById(R.id.vpIndicator);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        vpAdapter = new VPAdapter(getSupportFragmentManager());

        vpAdapter.setTitles(titles);
        vpAdapter.setFragments(fragments);
        mViewPager.setAdapter(vpAdapter);

        try {
            mVpIndicator.setViewPager(mViewPager);
            mVpIndicator.setOnPageChangeListener(pageChangeListener);
        } catch (IllegalStateException e) {

        }
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                setTitlebarRightVisiblity(false);
            } else if (position == 1) {
                setTitlebarRightVisiblity(true);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void titleBarLeftClick() {
        finish();
    }

    /**
     * 添加银行卡；
     */
    @Override
    protected void titleBarRightClick() {
        ToastUtil.showToastShort(this, "right");
    }

    @Override
    protected boolean isTitleBarRightVisible() {
        return false;
    }

    @Override
    protected String getTitleBarTitle() {
        return getString(R.string.text_myhfzh);
    }

    @Override
    protected String getTitleBarRightText() {
        return getString(R.string.text_tj);
    }
}
