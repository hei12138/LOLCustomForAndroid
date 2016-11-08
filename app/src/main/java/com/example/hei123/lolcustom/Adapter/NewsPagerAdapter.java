package com.example.hei123.lolcustom.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.hei123.lolcustom.News.NewsDefaultFragment;
import com.example.hei123.lolcustom.UI.NewsFragment;

import java.util.ArrayList;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    public static String[] TITLES = new String[] { "首页","活动","娱乐","官方"};
    private ArrayList<NewsDefaultFragment> fragments;

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.i("tag","创建NewsPagerAdapter");
        fragments=new ArrayList<>();
        for (int i=0;i<TITLES.length;i++){
            NewsDefaultFragment instances = NewsDefaultFragment.getInstances(i);
            fragments.add(instances);
        }

    }


    @Override
    public Fragment getItem(int position) {
        fragments.get(position).initData();
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
