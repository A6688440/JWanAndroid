package com.example.jwanandroid.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jwanandroid.R;
import com.example.jwanandroid.systematic.SystematicKindBean;
import com.example.jwanandroid.utils.CommUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by SJC on 2020/6/8.
 * Describe：
 */
public class RecyclerAdapterSystematicKind extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerAdapterSystematicKind";
    private List<SystematicKindBean.DataBean> dataBeans;
    private List<SystematicKindBean.DataBean.ChildrenBean> mKind2 = new ArrayList<>();
    private MyCall myCall;
    private MyCall2 myCall2;
    private int Type;
    private int p1 = 0, p2 = 0;

    public RecyclerAdapterSystematicKind(List<SystematicKindBean.DataBean> dataBeans, int Type, MyCall myCall) {
        this.dataBeans = dataBeans;
        this.myCall = myCall;
        this.Type = Type;
    }

    public RecyclerAdapterSystematicKind(int Type, List<SystematicKindBean.DataBean.ChildrenBean> childrenBeans,MyCall2 myCall2) {
        this.Type = Type;
        this.mKind2 = childrenBeans;
        this.myCall2=myCall2;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_king_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (Type == CommUtil.TYPE_KIND_1) {

            viewHolder.mTextKind.setText(dataBeans.get(position).getName());
            viewHolder.mTextKind.setSelected(p1 == position);

            viewHolder.mTextKind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myCall.MyClick(view, dataBeans.get(position).getChildren(),position);
                    p1 = position;
                    p2=0;
                    notifyDataSetChanged();
                }
            });
        } else if (Type == CommUtil.TYPE_KIND_2) {

            viewHolder.mTextKind.setSelected(p2 == position);
            viewHolder.mTextKind.setText(mKind2.get(position).getName());
            viewHolder.mTextKind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    p2 = position;
                    notifyDataSetChanged();
                    myCall2.MyClick2(view,mKind2.get(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (Type == CommUtil.TYPE_KIND_1) {
            return dataBeans.size();
        } else if (Type == CommUtil.TYPE_KIND_2) {

            return mKind2.size();

        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextKind;
        LinearLayout mLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextKind = itemView.findViewById(R.id.mTextKind);
            mLayout = itemView.findViewById(R.id.mLinearLayoutKind);

        }
    }

    public interface MyCall {
        void MyClick(View view, List<SystematicKindBean.DataBean.ChildrenBean> childrenBeans,int position);
    }

    public interface MyCall2 {
        void MyClick2(View view,int  cid);
    }


}
