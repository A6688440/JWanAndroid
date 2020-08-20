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
import com.example.jwanandroid.utils.CommUtil;
import com.example.jwanandroid.web.WebActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by SJC on 2020/5/31.
 * Describe：
 */
public class RecyclerAdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerAdapterHome";
    private List<ArticleBean.DataBean.DatasBean> list;
    private MyCall myCall;

    public MoreLoading moreLoading = null;

    private Context mContext;

    public RecyclerAdapterHome(Context mContext, List<ArticleBean.DataBean.DatasBean> list, List<Integer> loves, MyCall myCall) {
        this.mContext = mContext;
        this.list = list;
        this.myCall = myCall;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CommUtil.TYPE_CONTEXT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, parent, false);
            final MyViewHolder myViewHolder = new MyViewHolder(view);

            myViewHolder.mImageLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    view.setSelected(view.isSelected());
                    myCall.LoveOnClick(view);
                }
            });
            return myViewHolder;
        } else if (viewType == CommUtil.TYPE_FOOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.refresh_foot, parent, false);
            return new FootHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof MyViewHolder) {
            final MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.mTitle.setText(list.get(position).getTitle());


            if (list.get(position).getShareUser().equals("")) {
                myViewHolder.mLayoutShare.setVisibility(View.GONE);
            } else {
                myViewHolder.mLayoutShare.setVisibility(View.VISIBLE);
                myViewHolder.mTextSharePeople.setText(list.get(position).getShareUser());
            }
            if (list.get(position).getNiceShareDate().equals("")) {
                myViewHolder.mLayoutTime.setVisibility(View.GONE);
            } else {
                myViewHolder.mLayoutTime.setVisibility(View.VISIBLE);
                myViewHolder.mTextTime.setText(list.get(position).getNiceDate());
            }

            if (list.get(position).getSuperChapterName().equals("") && list.get(position).getChapterName().equals("")) {
                myViewHolder.mLayoutKind.setVisibility(View.GONE);
            } else {
                myViewHolder.mLayoutKind.setVisibility(View.VISIBLE);
                myViewHolder.mTextKind1.setText(list.get(position).getSuperChapterName());
                myViewHolder.mTextKind2.setText(list.get(position).getChapterName());
            }

            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!list.get(position).getLink().equals("")) {
                        Intent intent = new Intent(mContext, WebActivity.class);
                        intent.putExtra("WebViewUrl", list.get(position).getLink());
                        mContext.startActivity(intent);
                    }
                    myCall.ItemOnClick(view, position, list.get(position).getLink());

                }
            });

        } else if (holder instanceof FootHolder) {
            FootHolder footHolder = (FootHolder) holder;

        }

    }

    @Override
    public int getItemCount() {

        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {


        return position + 1 == getItemCount() ? CommUtil.TYPE_FOOT : CommUtil.TYPE_CONTEXT;


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //        HomeRecyclerItemBinding binding;
        ImageView mImageLove;
        TextView mTitle, mTextSharePeople, mTextTime, mTextKind1, mTextKind2;
        LinearLayout mLayoutShare, mLayoutTime, mLayoutKind;

        public MyViewHolder(@NonNull View itemView) {
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

    public static class FootHolder extends RecyclerView.ViewHolder {
        public FootHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface MyCall {

        void LastItem(boolean isLastItem);

        void LoveOnClick(View view);

        void ItemOnClick(View view, int position, String link);
    }

    public interface MoreLoading {
        void loading(boolean isLastItem);
    }

}
