package com.example.hei123.lolcustom.Picture;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.hei123.lolcustom.Adapter.OriginalPicAdapter;
import com.example.hei123.lolcustom.Adapter.WallpaperAdapter;
import com.example.hei123.lolcustom.Controls.ViewPagerIndicator;
import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 11/6/2016.
 * CopyRight @hei123
 */

public class OriginalPicActivity extends FragmentActivity {

    private ViewPagerIndicator indicator;
    private ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.originalpic_activity);
        initView();
        initData();
    }

    private void initData() {
        OriginalPicAdapter mAdapter=new OriginalPicAdapter(getSupportFragmentManager());
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
