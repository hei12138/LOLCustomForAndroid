package com.example.hei123.lolcustom.Fragment.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Views.MainActivity;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 * 所有Tab页的父类 页面内包含一个菜单按钮和framelayout用作承载子页面
 */

public abstract class BaseFragment extends Fragment {
    public MainActivity mActivity;
    public View mRootview;//当前页面的布局对象
    public ImageButton btn_menu;
    public FrameLayout fl_content;
    public View toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootview == null) {
            Log.i("tag", "创建了UI内容");
            mRootview = inflater.inflate(R.layout.base_fragment, container, false);
            initView();
            initData();
        }
        ViewGroup parent = (ViewGroup) mRootview.getParent();
        if (parent != null) {
            parent.removeView(mRootview);
        }

        return mRootview;
    }

    //初始化布局
    public void initView() {
        toolbar = mRootview.findViewById(R.id.toolbar);
        btn_menu = (ImageButton) mRootview.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMenu();
            }
        });
        fl_content = (FrameLayout) mRootview.findViewById(R.id.fl_content);
    }

    private void toggleMenu() {
        //点击切换菜单状态
        mActivity.getSlidemenu().toggle();
    }

    public abstract void initData();
}
