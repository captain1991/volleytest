package com.ayd.rhcf.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayd.rhcf.R;
import com.ayd.rhcf.bean.TestBean;
import com.ayd.rhcf.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 理财产品
 * Created by gqy on 2016/2/24.
 */
public class LicaiChanPinAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<TestBean> dataList;

    public LicaiChanPinAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public TestBean getItem(int position) {
        return dataList == null ? null : dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_licai_chanpin_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTvLeftTitle = (TextView) convertView.findViewById(R.id.tv_left_title);
            viewHolder.mTvYqnh = (TextView) convertView.findViewById(R.id.tv_yqnh);
            viewHolder.mTvPercent = (TextView) convertView.findViewById(R.id.tv_percent);
            viewHolder.mTvQtje = (TextView) convertView.findViewById(R.id.tv_qtje);
            viewHolder.mTvRzje = (TextView) convertView.findViewById(R.id.tv_rzje);
            viewHolder.mTvQx = (TextView) convertView.findViewById(R.id.tv_qx);
            viewHolder.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TestBean testBean = getItem(position);

        viewHolder.mTvQtje.setText(Html.fromHtml("<font color=\"#c3130f\">" + 100 + "</font>元起投"));
        viewHolder.mTvQx.setText(Html.fromHtml("期限<font color=\"#c3130f\">" + 3 + "</font>个月"));

        CommonUtil.updateTimeLabel(viewHolder.mTvTime, testBean.timeInMis);

        return convertView;
    }


    class ViewHolder {
        public TextView mTvLeftTitle;
        public TextView mTvYqnh;
        public TextView mTvPercent;
        public TextView mTvQtje;
        public TextView mTvRzje;
        public TextView mTvQx;
        public TextView mTvTime;
    }

    public List<TestBean> getDataList() {
        return dataList;
    }

    public void appendDataList(List<TestBean> dataListTemp) {
        if (dataListTemp == null || dataListTemp.size() == 0) {
            return;
        }
        if (dataList == null) {
            dataList = new ArrayList<TestBean>();
        }
        dataList.addAll(dataListTemp);
    }

    public void scheduleTime() {
        if (dataList != null && dataList.size() > 0) {
            for (TestBean bean : dataList) {
                bean.timeInMis = bean.timeInMis - 1;
            }

            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (dataList != null) {
            dataList.clear();
            notifyDataSetChanged();
        }
    }

}
