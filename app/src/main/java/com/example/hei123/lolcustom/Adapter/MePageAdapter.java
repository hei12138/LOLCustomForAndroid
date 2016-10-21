package com.example.hei123.lolcustom.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hei123 on 10/21/2016.
 * CopyRight @hei123
 */

public class MePageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listFragments;
    public MePageAdapter(FragmentManager fm, ArrayList<Fragment>al) {
        super(fm);
        this.listFragments=al;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
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
