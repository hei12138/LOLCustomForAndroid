package com.example.hei123.lolcustom.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hei123.lolcustom.Fragment.Base.BaseFragment;
import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 10/5/2016.
 */

public class DisCoverFragment extends BaseFragment {

    @Override
    public void initData() {
        View view=View.inflate(mActivity, R.layout.fragment_discover,null);
        fl_content.addView(view);
    }
}
