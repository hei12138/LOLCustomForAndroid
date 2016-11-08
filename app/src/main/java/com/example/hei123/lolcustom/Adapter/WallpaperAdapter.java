package com.example.hei123.lolcustom.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.hei123.lolcustom.Picture.BingFragment;
import com.example.hei123.lolcustom.Picture.WallPaperListFragment;

/**
 * Created by hei123 on 11/1/2016.
 * CopyRight @hei123
 */

public class WallpaperAdapter extends FragmentPagerAdapter {
    public static final String[] TITLES = {"最新","最热"};

    public WallpaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       return WallPaperListFragment.getInstanse(position);
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
