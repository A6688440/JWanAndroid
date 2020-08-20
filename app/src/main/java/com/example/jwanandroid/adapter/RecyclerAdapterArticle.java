package com.example.jwanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jwanandroid.R;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.web.WebActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by SJC on 2020/6/13.
 * Describe：
 */
public class RecyclerAdapterArticle extends RecyclerView.Adapter<RecyclerAdapterArticle.ViewHolder> {

    private Context mContext;

    private List<ArticleBean.DataBean.DatasBean> mArticleList;
    private MyCall myCall;

    public RecyclerAdapterArticle(Context mContext, List<ArticleBean.DataBean.DatasBean> mArticleList, MyCall myCall) {
        this.mContext = mContext;
        this.mArticleList = mArticleList;
        this.myCall = myCall;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ViewHolder viewHolder = holder;
        holder.itemView.setTag(position);
        viewHolder.mTitle.setText(mArticleList.get(position).getTitle());


        viewHolder.mImageLove.setSelected(mArticleList.get(position).isCollect());


        if (mArticleList.get(position).getShareUser().equals("")) {
            viewHolder.mLayoutShare.setVisibility(View.GONE);
        } else {
            viewHolder.mLayoutShare.setVisibility(View.VISIBLE);
            viewHolder.mTextSharePeople.setText(mArticleList.get(position).getShareUser());
        }
        if (mArticleList.get(position).getNiceShareDate().equals("")) {
            viewHolder.mLayoutTime.setVisibility(View.GONE);
        } else {
            viewHolder.mLayoutTime.setVisibility(View.VISIBLE);
            viewHolder.mTextTime.setText(mArticleList.get(position).getNiceDate());
        }

        if (mArticleList.get(position).getSuperChapterName().equals("") && mArticleList.get(position).getChapterName().equals("")) {
            viewHolder.mLayoutKind.setVisibility(View.GONE);
        } else {
            viewHolder.mLayoutKind.setVisibility(View.VISIBLE);
            viewHolder.mTextKind1.setText(mArticleList.get(position).getSuperChapterName());
            viewHolder.mTextKind2.setText(mArticleList.get(position).getChapterName());
        }

        viewHolder.mImageLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCall.setLove(position,mArticleList.get(position).getId(), view.isSelected());
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mArticleList.get(position).getLink().equals("")) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("WebViewUrl", mArticleList.get(position).getLink());
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageLove;
        TextView mTitle, mTextSharePeople, mTextTime, mTextKind1, mTextKind2;
        LinearLayout mLayoutShare, mLayoutTime, mLayoutKind;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.mTextTitle);
            mImageLove = itemView.findViewById(R.id.mImageLove);
            mTextSharePeople = itemView.findViewById(R.id.mTextSharePeople);
            mTextTime = itemView.findViewById(R.id.mTextTime);
            mTextKind1 = itemView.findViewById(R.id.mTextKind1);
            mTextKind2 = itemView.findViewById(R.id.mTextKind2);
            mLayoutShare = itemView.findViewById(R.id.mLayoutShare);
            mLayoutTime = itemView.findViewById(R.id.mLayoutTime);
            mLayoutKind = itemView.findViewById(R.id.mLayoutKind);

        }
    }

    public interface MyCall {

        void setLove(int position,int cid, boolean isLove);

    }
}
