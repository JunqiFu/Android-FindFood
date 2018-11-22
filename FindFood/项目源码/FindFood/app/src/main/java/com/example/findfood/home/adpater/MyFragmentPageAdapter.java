package com.example.findfood.home.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyFragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments; //Fragment集合

    public MyFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = fragments;

    }

    //当前显示的是第几个
    @Override
    public Fragment getItem(int arg0) {
        return fragments.get(arg0);
    }
    //计算需要几个item
    @Override
    public int getCount() {
        return fragments.size();
    }

}
