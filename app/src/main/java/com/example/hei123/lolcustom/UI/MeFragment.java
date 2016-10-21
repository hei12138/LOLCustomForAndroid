package com.example.hei123.lolcustom.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hei123.lolcustom.Adapter.MePageAdapter;
import com.example.hei123.lolcustom.Controls.ViewPagerIndicator;
import com.example.hei123.lolcustom.Fragment.Base.BaseFragment;
import com.example.hei123.lolcustom.Me.AssetsFragment;
import com.example.hei123.lolcustom.Me.CombatFragment;
import com.example.hei123.lolcustom.Me.PowerFragment;
import com.example.hei123.lolcustom.R;

import java.util.ArrayList;

/**
 * Created by hei123 on 10/5/2016.
 */

public class MeFragment extends BaseFragment {

    private ViewPagerIndicator indicator;
    private ViewPager viewpager;
    private static final String[] title={"战绩","能力","资产"};
    private ArrayList<Fragment> list;

    @Override
    public void initData() {
        //btn_menu.setVisibility(View.VISIBLE);
        View view = View.inflate(mActivity, R.layout.fragment_me, null);
        fl_content.addView(view);
        indicator = (ViewPagerIndicator) view.findViewById(R.id.indicator);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator.setVisibleTabCount(3);
        indicator.setTabItemTitles(title);
        indicator.setViewPager(viewpager,0);
        list=new ArrayList<>();
        list.add(new CombatFragment());
        list.add(new PowerFragment());
        list.add(new AssetsFragment());
        MePageAdapter mePageAdapter = new MePageAdapter(getChildFragmentManager(), list);
        viewpager.setAdapter(mePageAdapter);
        indicator.setOnPageChangeListener(new ViewPagerIndicator.PageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position!=0){
                    mActivity.getSlidemenu().setCanOpenMenu(false);
                }else{
                    mActivity.getSlidemenu().setCanOpenMenu(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
