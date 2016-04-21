package com.ayd.rhcf.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.MyEventCallBack;
import com.ayd.rhcf.R;

/**
 * Created by gqy on 2016/3/7.
 * 未开通汇付托管账户dialog；
 */
public class WktHftgDialog extends DialogFragment implements ClickEventCallBack {
    private Button mBtnCancel;
    private Button mBtnQkt;
    private MyEventCallBack callBack;

    public void setCallBack(MyEventCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.dialog_style);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.wkt_hftg_dialog, null);
        dialog.setContentView(view);
        init(view);
        return dialog;
    }

    private void init(View view) {
        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        ClickListenerRegister.regist(mBtnCancel, this);

        mBtnQkt = (Button) view.findViewById(R.id.btn_qkt);
        ClickListenerRegister.regist(mBtnQkt, this);
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mBtnCancel.getId()) {
            if (callBack != null) {
                callBack.adapterEventCallBack(0);
            }
        } else if (viewId == mBtnQkt.getId()) {
            if (callBack != null) {
                callBack.adapterEventCallBack(1);
            }
        }
    }
}
