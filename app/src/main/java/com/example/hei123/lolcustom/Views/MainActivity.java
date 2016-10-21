package com.example.hei123.lolcustom.Views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.hei123.lolcustom.Controls.SlideMenu;
import com.example.hei123.lolcustom.Fragment.Impl.ContentFragment;
import com.example.hei123.lolcustom.Fragment.Impl.LeftMenuFragment;
import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class MainActivity extends FragmentActivity {
    private String TAG_MENU;
    private String TAG_CONTENT;
    private SlideMenu slidemenu;
    private View fl_menu;
    private View fl_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initFragment();
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        TAG_MENU = "TAG_MENU";
        fragmentTransaction.replace(R.id.fl_menu, new LeftMenuFragment(), TAG_MENU);
        TAG_CONTENT = "TAG_CONTENT";
        fragmentTransaction.replace(R.id.fl_content, new ContentFragment(), TAG_CONTENT);
        fragmentTransaction.commit();
    }

    private void initView() {
        slidemenu = (SlideMenu) findViewById(R.id.slidemenu);
        fl_menu = findViewById(R.id.fl_menu);
        fl_content = findViewById(R.id.fl_content);
    }

    private void initData() {

    }
    public SlideMenu getSlidemenu(){
        return slidemenu;
    }
}
