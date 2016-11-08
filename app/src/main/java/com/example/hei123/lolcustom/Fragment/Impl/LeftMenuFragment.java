package com.example.hei123.lolcustom.Fragment.Impl;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.hei123.lolcustom.Fragment.Base.BaseFragment;
import com.example.hei123.lolcustom.Fragment.Base.MainBaseFragment;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Views.SettingActivity;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class LeftMenuFragment extends MainBaseFragment {

    private Button btn_setting;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.left_menu, null);
        btn_setting = (Button) view.findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }
}
