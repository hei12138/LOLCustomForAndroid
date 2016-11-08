package com.example.hei123.lolcustom.Fragment.Impl;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.hei123.lolcustom.Controls.MyLinearLayout;
import com.example.hei123.lolcustom.Fragment.Base.BaseFragment;
import com.example.hei123.lolcustom.Fragment.Base.MainBaseFragment;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.UI.MainTab;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class ContentFragment extends MainBaseFragment {
    private FragmentTabHost mTabHost;
    private MainTab[] tabs;
    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.main_content, null);
        MyLinearLayout main_content = (MyLinearLayout) inflate.findViewById(R.id.main_content);
        main_content.setSlideMenu(mActivity.getSlidemenu());
        mTabHost = (FragmentTabHost) inflate.findViewById(android.R.id.tabhost);
        mTabHost.setup(mActivity, mActivity.getSupportFragmentManager(), android.R.id.tabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        initTabs();

        //Log.i("tag",""+mTabHost.getCurrentTab());

        return inflate;
    }


    private void initTabs() {
        tabs = MainTab.values();
        final int size = tabs.length;
        for (int i=0;i<size;i++){
            //循环遍历初始化tabspec
            MainTab mainTab= tabs[i];
            TabHost.TabSpec tab=mTabHost.newTabSpec(getString(mainTab.getResName()));
            View inflate = View.inflate(mActivity, R.layout.layout_indicator, null);
            TextView title = (TextView) inflate.findViewById(R.id.tab_title);
            Drawable drawer=this.getResources().getDrawable(mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null,drawer,null,null);
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(inflate);
            Bundle bundle=new Bundle();
            bundle.putString("text","content"+getString(mainTab.getResName()));
            mTabHost.addTab(tab,mainTab.getClz(),bundle);
        }
    }

    //在oncreateview之后被调用
    @Override
    public void initData() {

    }
}
