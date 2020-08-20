package com.example.jwanandroid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jwanandroid.R;
import com.example.jwanandroid.bean.NavigationProjectBean;
import com.example.jwanandroid.utils.CommUtil;
import com.example.jwanandroid.web.WebActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by SJC on 2020/6/17.
 * Describe：
 */
public class RecyclerAdapterNavigation extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerAdapterNavigation";
    private List<NavigationProjectBean.DataBean> navigationBeans;
    private List<NavigationProjectBean.DataBean.ArticlesBean> articlesBeans;
    private Context mContext;
    private int mType;


    public RecyclerAdapterNavigation(List<NavigationProjectBean.DataBean> navigationBeans, Context mContext, int mType) {
        this.navigationBeans = navigationBeans;
        this.mContext = mContext;
        this.mType = mType;
    }

    public RecyclerAdapterNavigation(Context mContext, List<NavigationProjectBean.DataBean.ArticlesBean> articlesBeans, int mType) {
        this.articlesBeans = articlesBeans;
        this.mContext = mContext;
        this.mType = mType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (mType == CommUtil.TYPE_RECYCLER_TITLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_nav_title_item, parent, false);
            return new TitleViewHolder(view);
        } else if(mType==CommUtil.TYPE_RECYCLER_MESSAGE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_nav_message_item, parent, false);
            return new MessageViewHolder(view);
        }
        return null;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TitleViewHolder) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            titleViewHolder.mTitle.setText(navigationBeans.get(position).getName());
            titleViewHolder.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
            articlesBeans = navigationBeans.get(position).getArticles();
            RecyclerAdapterNavigation recyclerAdapterNavigation = new RecyclerAdapterNavigation(mContext,articlesBeans, CommUtil.TYPE_RECYCLER_MESSAGE);
            titleViewHolder.mRecyclerView.setAdapter(recyclerAdapterNavigation);
        } else if (holder instanceof MessageViewHolder) {
            MessageViewHolder messageViewHolder = (MessageViewHolder) holder;
            messageViewHolder.mMessage.setText(articlesBeans.get(position).getTitle());
            messageViewHolder.mMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!articlesBeans.get(position).getLink().equals("")) {
                        Intent intent = new Intent(mContext, WebActivity.class);
                        intent.putExtra("WebViewUrl", articlesBeans.get(position).getLink());
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mType == CommUtil.TYPE_RECYCLER_TITLE) {
            return navigationBeans.size();
        } else if (mType == CommUtil.TYPE_RECYCLER_MESSAGE) {
            return articlesBeans.size();
        }
        return 0;
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView mMessage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            mMessage = itemView.findViewById(R.id.mMessageItem);
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        RecyclerView mRecyclerView;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.mTitleItem);
            mRecyclerView = itemView.findViewById(R.id.mRecyclerTitle);

        }
    }
}
