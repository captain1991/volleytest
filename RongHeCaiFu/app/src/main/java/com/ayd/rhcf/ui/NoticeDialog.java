package com.ayd.rhcf.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ayd.rhcf.ClickEventCallBack;
import com.ayd.rhcf.ClickListenerRegister;
import com.ayd.rhcf.MyEventCallBack;
import com.ayd.rhcf.R;
import com.ayd.rhcf.utils.CommonUtil;

/**
 * Created by gqy on 2016/3/16.
 * 带yes 和no的dialog；
 */
public class NoticeDialog extends DialogFragment implements ClickEventCallBack {
    private String title = "";
    private String content = "";
    private String noLabel = "";
    private String yesLabel = "";
    private MyEventCallBack callBack;
    private TextView mTvTitle;
    private TextView mTvUpdateContent;
    private TextView mTvNo;
    private TextView mTvYes;

    public void setCallBack(MyEventCallBack callBack) {
        this.callBack = callBack;
    }

    public NoticeDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public NoticeDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public NoticeDialog setNoLabel(String noLabel) {
        this.noLabel = noLabel;
        return this;
    }

    public NoticeDialog setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.dialog_yhk_style);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.notice_dialog, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = CommonUtil.getScreenWidth(getActivity()) - CommonUtil.dip2px(getActivity(), 50);
        window.setAttributes(wl);

        init(view);
        return dialog;
    }

    private void init(View view) {
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvUpdateContent = (TextView) view.findViewById(R.id.tv_update_content);
        mTvNo = (TextView) view.findViewById(R.id.tv_no);
        mTvNo.setText(noLabel);

        mTvYes = (TextView) view.findViewById(R.id.tv_yes);
        mTvYes.setText(yesLabel);

        mTvTitle.setText(title == null ? "" : title);
        mTvUpdateContent.setText(content == null ? "" : content);

        ClickListenerRegister.regist(mTvNo, this);
        ClickListenerRegister.regist(mTvYes, this);
    }

    @Override
    public void clickEventCallBack(int viewId) {
        if (viewId == mTvNo.getId()) {
            if (callBack != null)
                callBack.adapterEventCallBack(0);
        } else if (viewId == mTvYes.getId()) {
            if (callBack != null)
                callBack.adapterEventCallBack(1);
        }
        this.dismiss();
    }
}
