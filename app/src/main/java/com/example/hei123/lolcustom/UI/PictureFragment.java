package com.example.hei123.lolcustom.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hei123.lolcustom.Controls.CustomDialog;
import com.example.hei123.lolcustom.Global.NetWorkConstans;
import com.example.hei123.lolcustom.Picture.BaseWallActivity;
import com.example.hei123.lolcustom.Picture.FullScreenDialogFragment;
import com.example.hei123.lolcustom.Picture.OriginalPicActivity;
import com.example.hei123.lolcustom.Picture.WallPaperActivity;
import com.example.hei123.lolcustom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hei123 on 10/5/2016.
 */

public class PictureFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_pic, null);
        inflate.findViewById(R.id.wallpaper_list).setOnClickListener(this);
        inflate.findViewById(R.id.wallpaper_bing).setOnClickListener(this);
        inflate.findViewById(R.id.wallpaper_club).setOnClickListener(this);
        inflate.findViewById(R.id.wallpaper_distribute).setOnClickListener(this);
        inflate.findViewById(R.id.wallpaper_splash).setOnClickListener(this);
        inflate.findViewById(R.id.wallpaper_original).setOnClickListener(this);

        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wallpaper_bing:
                Log.e("pictureFragment","点击了每日一图");
                //弹出自定义对话框
                ArrayList<String> bing=new ArrayList<>();
                bing.add(NetWorkConstans.BING_PAPER);
                FullScreenDialogFragment.newInstance(getActivity(),bing,0).show(getFragmentManager(),"PictureFragment");

                break;
            case R.id.wallpaper_list:
                Intent intent=new Intent(getContext(), WallPaperActivity.class);
                startActivity(intent);
                break;
            case R.id.wallpaper_club:
                break;
            case R.id.wallpaper_distribute:
                Intent intent1=new Intent(getContext(), BaseWallActivity.class);
                intent1.putExtra("id", 0);
                startActivity(intent1);
                break;
            case R.id.wallpaper_splash:
                intent1=new Intent(getContext(), BaseWallActivity.class);
                intent1.putExtra("id",1);
                startActivity(intent1);
                break;
            case R.id.wallpaper_original:
                Intent intentyuanhua=new Intent(getContext(), OriginalPicActivity.class);
                startActivity(intentyuanhua);
                break;
        }
    }
}
