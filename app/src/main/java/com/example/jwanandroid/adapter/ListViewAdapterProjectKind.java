package com.example.jwanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jwanandroid.R;
import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.bean.ProjectKindBean;

import java.util.List;

/**
 * Created by SJC on 2020/6/25.
 * Describe：
 */
public class ListViewAdapterProjectKind extends BaseAdapter {

    private Context mContext;
    private List<ProjectKindBean.DataBean> list;

    public ListViewAdapterProjectKind(Context mContext, List<ProjectKindBean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder=new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.list_na_item, viewGroup, false);
            viewHolder.textView = view.findViewById(R.id.mTextItemNa);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(list.get(i).getName());
        return view;

    }

    class ViewHolder {
        private TextView textView;
    }
}
