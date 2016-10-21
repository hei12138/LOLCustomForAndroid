package com.example.hei123.lolcustom.News;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.hei123.lolcustom.Adapter.NewsAdapter;
import com.example.hei123.lolcustom.Controls.MyPullToRefreshListView;
import com.example.hei123.lolcustom.Global.NetWorkConstans;
import com.example.hei123.lolcustom.Model.NewsModel;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.HttpRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class NewsDefaultFragment extends Fragment {
    public final static String TAG="MyFragment";
    String mData;
    private MyPullToRefreshListView lv_news;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                //数据读取成成功
                processData(response);
            }
        }
    };
    private NewsModel data;

    private void processData(String json) {
        //解析数据
        //Gson
        Gson gson = new Gson();
        data = gson.fromJson(json, NewsModel.class);

        //Log.i("tag", article_url);
        if(data !=null){
            NewsAdapter newsAdapter = new NewsAdapter(data, getContext());
            lv_news.setAdapter(newsAdapter);

        }
    }

    private String response;

    public static NewsDefaultFragment getInstances(String data){
        NewsDefaultFragment newsDefaultFragment = new NewsDefaultFragment();
        newsDefaultFragment.mData=data;
        return newsDefaultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //先执行Oncreate
        //Log.i("tag","创建create");

    }

    private void initData() {
        getDataFromService();
    }

    private void getDataFromService() {
        new Thread() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                try {
                    response = HttpRequest.get(NetWorkConstans.HOMEPAGE_URL).body();
                    //写缓存
                    //CacheUtils.setCache(GlobalConstants.CATEGORY_URL, response, mActivity);
                    // Log.i("tag", response);
                    msg.what = 0;
                } catch (Exception e) {
                    Log.i("tag", "出错了");
                    Log.i("tag",e.toString());
                    msg.what = 1;
                }
                handler.sendMessage(msg);
            }

        }.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.i("tag","创建createView");
        String str = "你好啊";
        if (savedInstanceState != null) {
            str = savedInstanceState.getString("SaveInstanceStateEXTRA");
        }
        View view = inflater.inflate(R.layout.news_list,null);
        lv_news = (MyPullToRefreshListView) view.findViewById(R.id.lv_news);
        lv_news.setOnRefreshListener(new MyPullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lv_news.onRefreshComplete(true);
                            }
                        });
                    }
                }.start();
            }
        });
        initData();
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int headerViewsCount = lv_news.getHeaderViewsCount();
                position=position-headerViewsCount;
                String article_url = data.list.get(position).article_url;
                Intent intent =new Intent(getContext(),NewsDetailActivity.class);
                intent.putExtra("newsurl",article_url);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tag","teststring");
    }

    @Override
    public void onDestroy() {
        Log.i("tag","销毁了"+mData);
        super.onDestroy();
    }

}
