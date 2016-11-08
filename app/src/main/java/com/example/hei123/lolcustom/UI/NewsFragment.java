package com.example.hei123.lolcustom.UI;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.hei123.lolcustom.Adapter.NewsPagerAdapter;
import com.example.hei123.lolcustom.Controls.ViewPagerIndicator;
import com.example.hei123.lolcustom.Fragment.Base.BaseFragment;
import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 10/5/2016.
 */


public class NewsFragment extends BaseFragment {

    private ViewPagerIndicator indicator;
    private ViewPager viewpager;
    private NewsPagerAdapter mAdapter;

    @Override
    public void initData() {
        Log.i("News","执行了news页面的initData方法");
        toolbar.setVisibility(View.INVISIBLE);
        View view = View.inflate(mActivity, R.layout.fragment_news, null);
        indicator = (ViewPagerIndicator) view.findViewById(R.id.indicator);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        mAdapter = new NewsPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(mAdapter);
        indicator.setVisibleTabCount(4);
        indicator.setTabItemTitles(mAdapter.TITLES);
        indicator.setViewPager(viewpager,0);
        indicator.setOnPageChangeListener(new ViewPagerIndicator.PageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.i("tag","页面滑动");
            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    mActivity.getSlidemenu().setCanOpenMenu(true);
                }else{
                    mActivity.getSlidemenu().setCanOpenMenu(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fl_content.addView(view);
    }

}
