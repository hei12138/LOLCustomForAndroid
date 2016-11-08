package com.example.hei123.lolcustom.Picture;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.hei123.lolcustom.Adapter.WallpaperAdapter;
import com.example.hei123.lolcustom.Controls.ViewPagerIndicator;
import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 11/1/2016.
 * CopyRight @hei123
 */

public class WallPaperActivity extends FragmentActivity{

    private ViewPagerIndicator indicator;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        initView();
        initData();
    }

    private void initData() {
        WallpaperAdapter mAdapter = new WallpaperAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        indicator.setVisibleTabCount(2);
        indicator.setTabItemTitles(mAdapter.TITLES);
        indicator.setViewPager(viewpager,0);
    }

    private void initView() {
        indicator = (ViewPagerIndicator) findViewById(R.id.indicator);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }
}
