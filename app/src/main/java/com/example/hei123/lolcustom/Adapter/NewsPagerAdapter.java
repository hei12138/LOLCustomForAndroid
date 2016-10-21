package com.example.hei123.lolcustom.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hei123.lolcustom.News.NewsDefaultFragment;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    public static String[] TITLES = new String[] { "首页", "赛事", "活动", "视频", "娱乐" };

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        NewsDefaultFragment instances = NewsDefaultFragment.getInstances(TITLES[position]);
        return instances;
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
