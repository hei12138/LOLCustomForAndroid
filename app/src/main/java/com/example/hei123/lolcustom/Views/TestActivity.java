package com.example.hei123.lolcustom.Views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Test.TestFragment;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by hei123 on 2016/10/24.
 * CopyRight @hei123
 */

public class TestActivity extends FragmentActivity {

    private ArrayList<Fragment> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        datas = new ArrayList<>();
        datas.add(new TestFragment());
        datas.add(new TestFragment());
        datas.add(new TestFragment());
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        MyPageAdapter mPagerAdapter=new MyPageAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mPagerAdapter);
        indicator.setViewPager(viewpager);

    }
    class MyPageAdapter extends FragmentPagerAdapter{

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }


        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
