package com.example.hei123.lolcustom.UI;

import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 10/5/2016.
 */

public enum MainTab {

    NEWS(0, R.string.main_tab_name_news,R.drawable.tab_icon_news,NewsFragment.class),

    FRIEND(1, R.string.main_tab_name_friend, R.drawable.tab_icon_friend,
            FriendFragment.class),

    DISCOVER(2, R.string.main_tab_name_descover, R.drawable.tab_icon_discover,
            DisCoverFragment.class),

    EXPLORE(3, R.string.main_tab_name_me, R.drawable.tab_icon_me,
            MeFragment.class);




    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;


    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }



}
