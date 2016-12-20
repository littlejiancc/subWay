package com.example.wangxiaojian.subway;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Wangxiaojian on 2016/12/10.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;           //fragment集合
    private List<String> titles;                //tab标题集合

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (fragments!=null && fragments.size()!=0){
            ret = fragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}

