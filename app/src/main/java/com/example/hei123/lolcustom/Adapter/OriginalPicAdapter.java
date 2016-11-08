package com.example.hei123.lolcustom.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.hei123.lolcustom.Picture.OriginalPicListFragment;

/**
 * Created by hei123 on 11/6/2016.
 * CopyRight @hei123
 */

public class OriginalPicAdapter extends FragmentPagerAdapter {
    public static final String[] TITLES = {"英雄原画","皮肤原画"};
    public OriginalPicAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("oriadapter","创建元素");
        return OriginalPicListFragment.getInstanse(position);
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
