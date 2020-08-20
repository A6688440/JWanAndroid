package com.example.jwanandroid.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jwanandroid.R;
import com.example.jwanandroid.adapter.ViewPagerAdapter;
import com.example.jwanandroid.base.BaseActivity;
import com.example.jwanandroid.collect.CollectFragment;
import com.example.jwanandroid.home.HomePagerFragment;
import com.example.jwanandroid.databinding.ActivityMainBinding;
import com.example.jwanandroid.event.EventUserInfo;
import com.example.jwanandroid.navigation.NavigationFragment;
import com.example.jwanandroid.project.ProjectFragment;
import com.example.jwanandroid.systematic.SystematicFragment;
import com.example.jwanandroid.user.login.LoginActivity;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MvpMain.V {

    private ActivityMainBinding binding;
    private TextView mTextUser;
    private ImageView mImgUser;


    private void initView() {
        mTextUser = binding.mNavigationView.getHeaderView(0).findViewById(R.id.mTextUser);
        mImgUser = binding.mNavigationView.getHeaderView(0).findViewById(R.id.mImgUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getUserName();
    }

    @Override
    protected MainPresenter getInstantiate() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();

        binding.mNavigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.mDrawerLayout.closeDrawers();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        binding.mExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.mDrawerLayout.closeDrawers();
                mPresenter.exitLogin();

            }
        });


        binding.mTab.addTab(binding.mTab.newTab());
        binding.mTab.addTab(binding.mTab.newTab());
        binding.mTab.addTab(binding.mTab.newTab());
        binding.mTab.addTab(binding.mTab.newTab());
        binding.mTab.addTab(binding.mTab.newTab());

        binding.mTab.getTabAt(0).setText("首页");
        binding.mTab.getTabAt(1).setText("体系");
        binding.mTab.getTabAt(2).setText("导航");
        binding.mTab.getTabAt(3).setText("项目");
        binding.mTab.getTabAt(4).setText("收藏");


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomePagerFragment());
        fragments.add(new SystematicFragment());
        fragments.add(new NavigationFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new CollectFragment());

        binding.mViewPager.setOffscreenPageLimit(5);
        binding.mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments));
        binding.mTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.mViewPager));
        binding.mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.mTab));
    }

    @Override
    public void getMessage(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserName(String username) {
        mTextUser.setText(username);
    }

    @Override
    public void getExitLogin() {
        mPresenter.getUserName();
    }
}
