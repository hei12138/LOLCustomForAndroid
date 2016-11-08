package com.example.hei123.lolcustom.Picture;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hei123.lolcustom.Adapter.NewsTopPagerAdapter;
import com.example.hei123.lolcustom.Adapter.WallpaperListAdapter;
import com.example.hei123.lolcustom.Global.NetWorkConstans;
import com.example.hei123.lolcustom.Model.NewsModel;
import com.example.hei123.lolcustom.Model.WallpaperModel;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.HttpRequest;
import com.google.gson.Gson;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hei123 on 11/1/2016.
 * CopyRight @hei123
 */

public class WallPaperListFragment extends Fragment {


    private int position;
    private View view;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String data = msg.getData().getString("data");
            Log.e("wallfragment","获取到数据");
            processData(data);
        }
    };
    private RecyclerView recycle_list;

    /**
     * 将数据设置到界面上
     */
    private void processData(String json) {
        Log.e("wallfragment",position+"");
        Gson gson = new Gson();
        final WallpaperModel wallpaperModel = gson.fromJson(json, WallpaperModel.class);
        if (wallpaperModel != null) {
            WallpaperListAdapter adapter=new WallpaperListAdapter(getActivity(), wallpaperModel.wallpapers);
            recycle_list.setAdapter(adapter);
            //设置item之间的间隔
            SpacesItemDecoration decoration=new SpacesItemDecoration(16);
            recycle_list.addItemDecoration(decoration);

        }
    }

    public static WallPaperListFragment getInstanse(int pos) {
        WallPaperListFragment wallPaperListFragment = new WallPaperListFragment();
        wallPaperListFragment.position = pos;
        return wallPaperListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.pic_wallpaper, container, false);
            recycle_list = (RecyclerView) view.findViewById(R.id.recycle_list);
            recycle_list.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            initData();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    /**
     * 在此获取数据
     */
    private void initData() {
        Log.e("wallfragment","初始化数据");
        getDataFromService();

    }

    private void getDataFromService() {
        final Message msg = Message.obtain();
        final String url;
        switch (position) {
            case 0:
                url = NetWorkConstans.WALLPAPER_NEW_LIST;
                break;
            case 1:
                url = NetWorkConstans.WALLPAPER_HOT_LIST;
                break;
            default:
                url = NetWorkConstans.WALLPAPER_NEW_LIST;
                break;
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    String response = HttpRequest.get(url).body();
                    Bundle b = new Bundle();
                    b.putString("data", response);
                    msg.setData(b);
                } catch (Exception e) {

                }
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
}
