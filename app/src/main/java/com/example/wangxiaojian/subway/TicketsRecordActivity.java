package com.example.wangxiaojian.subway;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wangxiaojian on 2016/12/10.
 */

public class TicketsRecordActivity extends AppCompatActivity{
    private NoGetTicketsFragment mNoTicketsFragment;
    private HadGetTicketsFragment mHadGetTicketsFragment;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragments = new ArrayList<>();          //fragment集合
    private List<String> titles = new ArrayList<String>();                //tab标题集合
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickets_record);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mTabLayout=(TabLayout)findViewById(R.id.tabs);
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //创建Fragment对象
        mNoTicketsFragment=new NoGetTicketsFragment();
        mHadGetTicketsFragment =new HadGetTicketsFragment();
        fragments.add(mNoTicketsFragment);
        fragments.add(mHadGetTicketsFragment);
        titles.add("未取票");
        titles.add("已取票");
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
