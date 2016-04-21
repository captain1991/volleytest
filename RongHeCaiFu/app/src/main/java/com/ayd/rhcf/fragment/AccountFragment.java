package com.ayd.rhcf.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayd.rhcf.AppConstants;
import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.MyEventCallBack;
import com.ayd.rhcf.R;
import com.ayd.rhcf.ui.CzActivity;
import com.ayd.rhcf.ui.GrzxActivity;
import com.ayd.rhcf.ui.HkjhActivity;
import com.ayd.rhcf.ui.JyjlActivity;
import com.ayd.rhcf.ui.LicaiActivity;
import com.ayd.rhcf.ui.LoginActivity;
import com.ayd.rhcf.ui.MyHfzhActivity;
import com.ayd.rhcf.ui.MyyqActivity;
import com.ayd.rhcf.ui.TxActivity;
import com.ayd.rhcf.ui.WktHftgDialog;
import com.ayd.rhcf.utils.CommonUtil;
import com.ayd.rhcf.utils.TvInit;

/**
 * 账户Fragment；
 * created by gqy on 2016/2/23
 */
public class AccountFragment extends BaseFragment implements ClickEventCallBack {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ImageView mIvThumb;
    private TextView mTvUname;

    private TextView mTvZzc;
    private TextView mTvKyye;
    private TextView mTvLjsy;

    private Button mBtnCz;
    private Button mBtnTx;

    private LinearLayout mPanelMyhfzh;
    private LinearLayout mPanelMylc;
    private LinearLayout mPanelHkjh;
    private LinearLayout mPanelJyjl;
    private LinearLayout mPanelMyyq;

    private WktHftgDialog dialog = null;
    private boolean isViewCreated = false;

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AccountFragment() {
    }

    public void initTv(String content) {
        TvInit.set(mTvUname, "");
        TvInit.set(mTvZzc, "");
        TvInit.set(mTvKyye, "");
        TvInit.set(mTvLjsy, "");

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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        if (view != null) {
            mIvThumb = (ImageView) view.findViewById(R.id.iv_thumb);
            ClickListenerRegister.regist(mIvThumb, this);

            mTvUname = (TextView) view.findViewById(R.id.tv_uname);
            ClickListenerRegister.regist(mTvUname, this);

            mTvZzc = (TextView) view.findViewById(R.id.tv_zzc);
            mTvKyye = (TextView) view.findViewById(R.id.tv_kyye);
            mTvLjsy = (TextView) view.findViewById(R.id.tv_ljsy);

            mBtnCz = (Button) view.findViewById(R.id.btn_cz);
            ClickListenerRegister.regist(mBtnCz, this);
            mBtnTx = (Button) view.findViewById(R.id.btn_tx);
            ClickListenerRegister.regist(mBtnTx, this);

            mPanelMyhfzh = (LinearLayout) view.findViewById(R.id.panel_myhfzh);
            ClickListenerRegister.regist(mPanelMyhfzh, this);
            mPanelMylc = (LinearLayout) view.findViewById(R.id.panel_mylc);
            ClickListenerRegister.regist(mPanelMylc, this);
            mPanelHkjh = (LinearLayout) view.findViewById(R.id.panel_hkjh);
            ClickListenerRegister.regist(mPanelHkjh, this);
            mPanelJyjl = (LinearLayout) view.findViewById(R.id.panel_jyjl);
            ClickListenerRegister.regist(mPanelJyjl, this);
            mPanelMyyq = (LinearLayout) view.findViewById(R.id.panel_myyq);
            ClickListenerRegister.regist(mPanelMyyq, this);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    public void refreshDataWhenVisible() {
        if (isViewCreated) {
            //获取资产数据；
        }
    }

    @Override
    public void clickEventCallBack(int viewId) {
        Bundle bundle = new Bundle();

        if (mTvUname.getId() == viewId) {
            //跳转到登录；
            readyGo(activity, LoginActivity.class);
        } else if (mIvThumb.getId() == viewId) {
            //判断登录；
            readyGo(activity, GrzxActivity.class);
        } else if (mBtnCz.getId() == viewId) {
            readyGo(activity, CzActivity.class);
        } else if (mBtnTx.getId() == viewId) {
            readyGo(activity, TxActivity.class);
        } else if (mPanelMyhfzh.getId() == viewId) {
            //未开通汇付托管账户；
            if (true) {
                showWktHftgDialog();
            } else {
                readyGo(activity, MyHfzhActivity.class);
            }
        } else if (mPanelMylc.getId() == viewId) {
            bundle.putString("", "");
            readyGoWithBundle(activity, LicaiActivity.class, bundle);
        } else if (mPanelHkjh.getId() == viewId) {
            bundle.putString("", "");
            readyGoWithBundle(activity, HkjhActivity.class, bundle);
        } else if (mPanelJyjl.getId() == viewId) {
            readyGo(activity, JyjlActivity.class);
        } else if (mPanelMyyq.getId() == viewId) {
            readyGo(activity, MyyqActivity.class);
        }
    }

    private void showWktHftgDialog() {
        if (dialog == null) {
            dialog = new WktHftgDialog();
        }
        dialog.setCallBack(new MyEventCallBack() {
            @Override
            public void adapterEventCallBack(Object... args) {
                int index = (int) args[0];
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (index == 1) { //去开通；
                    readyGo(activity, MyHfzhActivity.class);
                }
            }
        });
//        dialog.setTargetFragment(this, AppConstants.OPEN_WKTHFZH_FRAGMENT_REQ_CODE);
        dialog.show(getChildFragmentManager(), AppConstants.WKTHFZF_FRAGMENT_TAG);
    }

    @Override
    protected String[] getReqTagList() {
        return new String[]{"account_req_tag"};
    }

}
