package com.example.jwanandroid.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jwanandroid.R;
import com.example.jwanandroid.adapter.RecyclerAdapterArticle;
import com.example.jwanandroid.adapter.RecyclerAdapterHome;
import com.example.jwanandroid.base.BaseFragment;
import com.example.jwanandroid.databinding.FragmentHomePagerBinding;
import com.example.jwanandroid.bean.ArticleBean;
import com.example.jwanandroid.search.SearchActivity;
import com.example.jwanandroid.systematic.SystematicFragment;
import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePagerFragment extends BaseFragment<HomePresenter> implements MvpHome.V {
    private FragmentHomePagerBinding binding;
    private List<ArticleBean.DataBean.DatasBean> list = new ArrayList<>();

    private int pager = 0;
    private String TAG = "HomePagerFragment";
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapterArticle adapterArticle;
    private int mLovePosition=-1;

    public HomePagerFragment() {
        // Required empty public constructor
    }

    @Override
    protected HomePresenter getInstantiate() {
        return new HomePresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomePagerBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        mPresenter.getHomeHead();
        mPresenter.getPager(pager);

        binding.mSearchView.setIconifiedByDefault(true);
        binding.mSearchView.setIconified(false);
        binding.mSearchView.clearFocus();
        binding.mSearchView.setQueryHint("搜索");

        binding.mTextSearch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if (binding.mSearchView.getQuery().toString().equals("")) {
                    Toast.makeText(getContext(), "请输入搜索关键词", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), SearchActivity.class);
                    intent.putExtra("SearchKey", binding.mSearchView.getQuery().toString());
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void setLove() {

    }

    @Override
    public void getHomeHead(List<String> imageUrl, List<String> title, List<String> link) {
        //切换的样式
        binding.mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //自定义加载方式
        binding.mHomeBanner.setImageLoader(new MyLoader());
        //标题设置
        binding.mHomeBanner.setBannerTitles(title);
        //图片地址设置
        binding.mHomeBanner.setImages(imageUrl);
        //切换动画
        binding.mHomeBanner.setBannerAnimation(Transformer.Default);
        //切换频率
        binding.mHomeBanner.setDelayTime(2000);
        //自动启动
        binding.mHomeBanner.isAutoPlay(true);
        //位置设置
        binding.mHomeBanner.setIndicatorGravity(BannerConfig.CENTER);
        //开始运行
        binding.mHomeBanner.start();

        binding.mHomeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    public void getHome(List<ArticleBean.DataBean.DatasBean> bean) {
        list = bean;

        binding.mRecyclerViewHome.refreshComplete();
        binding.mRecyclerViewHome.loadMoreComplete();

        //list.addAll(bean);

        if (adapterArticle == null) {

            linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.mRecyclerViewHome.setLayoutManager(linearLayoutManager);

            //设置是否允许下拉刷新
            binding.mRecyclerViewHome.setPullRefreshEnabled(true);
            //设置是否允许上拉加载
            binding.mRecyclerViewHome.setLoadingMoreEnabled(true);

            binding.mRecyclerViewHome.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            binding.mRecyclerViewHome.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

            //设置上下拉的文字提示语
            //binding.mRecyclerViewHome.getDefaultFootView().setLoadingHint("自定义加载中提示");
            //设置XRecyclerView加载到最后一页再上拉的提示信息
            //xRecyclerView.getDefaultFootView().setNoMoreHint("加载完毕");
            //设置XRecyclerView下拉刷新时间提示
            //xRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(false);
//            binding.mRecyclerViewHome.addHeaderView();
            binding.mRecyclerViewHome.getDefaultFootView().setLoadingDoneHint("加载中。。。");


            adapterArticle = new RecyclerAdapterArticle(getContext(), list, new RecyclerAdapterArticle.MyCall() {
                @Override
                public void setLove(int position, int cid, boolean isLove) {
                    mPresenter.setLove(cid, isLove);
                    mLovePosition = position;
                }
            });
            binding.mRecyclerViewHome.setAdapter(adapterArticle);
        } else {
            adapterArticle.notifyDataSetChanged();
        }

        if (adapterArticle != null) {
            binding.mRecyclerViewHome.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                //下拉刷新
                public void onRefresh() {
                    //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                    pager = 0; //当前页码
                    /**加载数据处理**/
                    mPresenter.getPager(pager);
                }

                @Override
                //上拉加载
                public void onLoadMore() {
                    // Log.e(TAG, "onLoadMore: "+pager );
                    pager++;//当前页码
                    /**加载数据处理**/
                    mPresenter.getPager(pager);
                }
            });
        }


    }

    @Override
    public void getMessage(String Message) {
        switch (Message){
            case "收藏成功":
                list.get(mLovePosition).setCollect(true);
                mLovePosition=-1;
                break;
            case "取消成功":
                list.get(mLovePosition).setCollect(false);
                mLovePosition=-1;
                break;
        }

        adapterArticle.notifyDataSetChanged();

        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 清除焦点 返回时隐藏输入法
        binding.mSearchView.clearFocus();
    }

    private class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unBingView();
    }
}
