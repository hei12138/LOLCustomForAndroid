package com.example.hei123.lolcustom.Picture;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hei123.lolcustom.Adapter.OriginalPicListAdapter;
import com.example.hei123.lolcustom.Adapter.WallpaperListAdapter;
import com.example.hei123.lolcustom.Global.NetWorkConstans;
import com.example.hei123.lolcustom.Model.WallpaperModel;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.HttpRequest;
import com.google.gson.Gson;

/**
 * Created by hei123 on 11/6/2016.
 * CopyRight @hei123
 */

public class BaseWallActivity extends FragmentActivity {

    private int position;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String data = msg.getData().getString("data");
            Log.e("wallfragment","获取到数据");
            processData(data);
        }
    };

    private void processData(String json) {
        Gson gson = new Gson();
        final WallpaperModel wallpaperModel = gson.fromJson(json, WallpaperModel.class);
        if (wallpaperModel != null) {
            OriginalPicListAdapter adapter = new OriginalPicListAdapter(this, wallpaperModel.wallpapers);
            recycle_list.setAdapter(adapter);
            //设置item之间的间隔
            SpacesItemDecoration decoration = new SpacesItemDecoration(16);
            recycle_list.addItemDecoration(decoration);
        }
    }

    private RecyclerView recycle_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getIntent().getIntExtra("id",0);
        //0投稿
        //1闪屏
        setContentView(R.layout.activity_basewall);
        initView();
        initData();
    }

    private void initData() {
        final Message msg = Message.obtain();
        final String url;
        switch (position){
            case 0:
               //玩家投稿
                url=NetWorkConstans.PLAYER_POST;
                break;
            case 1:
                //掌盟闪屏
                url=NetWorkConstans.CLIENT_FLASH;
                break;
            default:
                url=NetWorkConstans.PLAYER_POST;
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

    private void initView() {
        recycle_list = (RecyclerView) findViewById(R.id.recycle_list);
        recycle_list.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
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
