package com.example.hei123.lolcustom.Me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 10/21/2016.
 * CopyRight @hei123
 */

public class AssetsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.me_assets_fragment,null);
    }
}
