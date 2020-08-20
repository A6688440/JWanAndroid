package com.example.jwanandroid.systematic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jwanandroid.adapter.RecyclerAdapterArticle;
import com.example.jwanandroid.adapter.RecyclerAdapterSystematicKind;
import com.example.jwanandroid.base.BaseFragment;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.databinding.FragmentSystematicBinding;

import com.example.jwanandroid.utils.CommUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class SystematicFragment extends BaseFragment<SystematicPresenter> implements MvpSystematic.V {


    private FragmentSystematicBinding binding;
    private StaggeredGridLayoutManager managerKind2;
    private StaggeredGridLayoutManager managerKind1;
    private RecyclerAdapterSystematicKind adapter1;
    private RecyclerAdapterSystematicKind adapter2;
    private List<ArticleBean.DataBean.DatasBean> list=new ArrayList<>();
    private List<SystematicKindBean.DataBean.ChildrenBean> mListKind2=new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapterArticle adapterArticle;

    private int pager=0;

    private int courseId;
    private RecyclerAdapterSystematicKind adapterKind2;

    private int mLovePosition;

    public SystematicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSystematicBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getKind();
    }

    @Override
    protected SystematicPresenter getInstantiate() {
        return new SystematicPresenter();
    }

    @Override
    public void getKind(final List<SystematicKindBean.DataBean> dataBeans) {

        mListKind2.addAll(dataBeans.get(0).getChildren());

        //recycle，瀑布布局设置
        managerKind1 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        binding.mRecycleKind1.setLayoutManager(managerKind1);

        managerKind2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        binding.mRecycleKind2.setLayoutManager(managerKind2);


        //一进来就要默认显示的文章
        list.clear();
        courseId = dataBeans.get(0).getChildren().get(0).getId();
        mPresenter.getArticle(0,  courseId );

        //一进来默认显示的Kind2的列表
        //默认recycler的点击
        adapterKind2 = new RecyclerAdapterSystematicKind(CommUtil.TYPE_KIND_2, mListKind2, new RecyclerAdapterSystematicKind.MyCall2() {
            @Override
            public void MyClick2(View view, int cid) {
                list.clear();
                courseId = cid;
                mPresenter.getArticle(0, courseId);
            }
        });
        binding.mRecycleKind2.setAdapter(adapterKind2);

        adapter1 = new RecyclerAdapterSystematicKind(dataBeans, CommUtil.TYPE_KIND_1, new RecyclerAdapterSystematicKind.MyCall() {
            @Override
            public void MyClick(View view, List<SystematicKindBean.DataBean.ChildrenBean> childrenBeans, int position) {

                //点击kind1后默认的点击事件（第一个item）
                list.clear();
                courseId = dataBeans.get(position).getChildren().get(0).getId();
                mPresenter.getArticle(0,  courseId );

                //点击kind1后的kind2的点击事件
                mListKind2.clear();
                mListKind2.addAll(dataBeans.get(position).getChildren());
                adapterKind2.notifyDataSetChanged();

            }
        });
        binding.mRecycleKind1.setAdapter(adapter1);
    }

    @Override
    public void getArticle(List<ArticleBean.DataBean.DatasBean> beans) {



        binding.mRecycleArticle.refreshComplete();
        binding.mRecycleArticle.loadMoreComplete();

        list=beans;

        if (adapterArticle == null) {

            linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.mRecycleArticle.setLayoutManager(linearLayoutManager);

            //设置是否允许下拉刷新
            binding.mRecycleArticle.setPullRefreshEnabled(true);
            //设置是否允许上拉加载
            binding.mRecycleArticle.setLoadingMoreEnabled(true);

            binding.mRecycleArticle.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            binding.mRecycleArticle.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

            adapterArticle = new RecyclerAdapterArticle(getContext(), list, new RecyclerAdapterArticle.MyCall() {
                @Override
                public void setLove(int position,int cid, boolean isLove) {
                    mLovePosition = position;
                    mPresenter.setLove(cid, isLove);

                }
            });
            binding.mRecycleArticle.setAdapter(adapterArticle);
        } else {
            adapterArticle.notifyDataSetChanged();
        }

        if (adapterArticle != null) {
            binding.mRecycleArticle.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                //下拉刷新
                public void onRefresh() {
                    //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                    pager = 0; //当前页码
                    /**加载数据处理**/
                    mPresenter.getArticle(pager ,courseId);
                }

                @Override
                //上拉加载
                public void onLoadMore() {
                    // Log.e(TAG, "onLoadMore: "+pager );
                    pager++;//当前页码
                    /**加载数据处理**/
                    mPresenter.getArticle(pager ,courseId);
                }
            });
        }

    }


    @Override
    public void getMessage(String Message) {
        switch (Message){
            case "收藏成功":
                list.get(mLovePosition).setCollect(true);
                break;
            case "取消成功":
                list.get(mLovePosition).setCollect(false);
                break;
        }
        mLovePosition=-1;
        adapterArticle.notifyDataSetChanged();


        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
    }
}
