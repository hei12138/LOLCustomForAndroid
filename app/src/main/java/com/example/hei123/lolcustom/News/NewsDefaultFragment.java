package com.example.hei123.lolcustom.News;

import android.content.Intent;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hei123.lolcustom.Adapter.NewsAdapter;
import com.example.hei123.lolcustom.Adapter.NewsTopPagerAdapter;
import com.example.hei123.lolcustom.Global.NetWorkConstans;
import com.example.hei123.lolcustom.Helper.NewsHelper;
import com.example.hei123.lolcustom.Model.NewsModel;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Views.MainActivity;
import com.google.gson.Gson;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class NewsDefaultFragment extends Fragment {
    private SwipeRefreshLayout swipe_layout;
    private ListView lv_news;
    private SwipeRefreshLayout.OnRefreshListener listener;
    private View news_header;
    private View view;
    private NewsModel data;
    private NewsModel TopData;
    private ViewPager viewpager;
    private CircleIndicator indicator;
    private String response;
    private int position;
    /**
     * msg what
     * 0 表示数据来自网络 获取成功
     * 1 表示数据来自本地 获取成功
     * 2 表示数据获取失败
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NewsHelper.MSG_SUCCESS_LOCAL:
                    //新闻读取本地数据成功
                    response = msg.getData().getString("data");
                    //可以接受到数据
                    processData(response);
                    //刷新
                    Refresh();
                    break;
                case NewsHelper.MSG_FAIL_LOCAL:
                    //读取本地数据失败
                    //刷新
                    Refresh();
                    break;
                case NewsHelper.MSG_SUCCESS_NET:
                    //获取网络数据成功
                    response = msg.getData().getString("data");
                    processData(response);
                    //关闭刷新按钮
                    CloseRefresh();
                    break;
                case NewsHelper.MSG_TOP_SUCCESS:
                    response = msg.getData().getString("data");
                    processTopData(response);
                    setIndicator();
                    break;
                case NewsHelper.MSG_FAIL_NET:
                    //网络连接失败
                    Toast.makeText(getContext(),"网络连接失败",Toast.LENGTH_LONG).show();
                    break;
                case NewsHelper.ERROR:
                    Toast.makeText(getContext(),"发生了错误",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };


    private void CloseRefresh(){
        swipe_layout.post(new Runnable() {
            @Override
            public void run() {
                swipe_layout.setRefreshing(false);
                Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
            }
        });
        //Log.e("tag","刷新完成");
    }


    /**
     * 刷新数据
     */
    private void Refresh() {
        swipe_layout.post(new Runnable() {
            @Override
            public void run() {
                swipe_layout.setRefreshing(true);
            }
        });
        listener.onRefresh();
    }



    private void setIndicator() {
        indicator.setViewPager(viewpager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.getSlidemenu().setCanOpenMenu(true);
                } else {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.getSlidemenu().setCanOpenMenu(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    private void processData(String json) {
        Gson gson = new Gson();
        try{
            data = gson.fromJson(json, NewsModel.class);
            if (data != null) {
                NewsAdapter newsAdapter = new NewsAdapter(data, getContext());
                lv_news.setAdapter(newsAdapter);
            }
        }catch (Exception e){
            Log.e("NewsDefaultFragment","json解析错误");
        }

    }

    private void processTopData(String json) {
        Gson gson = new Gson();
        TopData = gson.fromJson(json, NewsModel.class);
        if (TopData != null) {
            viewpager.setAdapter(new NewsTopPagerAdapter(TopData.list, getContext()));
        }
    }


    public static NewsDefaultFragment getInstances(int pos) {
        NewsDefaultFragment newsDefaultFragment = new NewsDefaultFragment();
        newsDefaultFragment.position=pos;
        return newsDefaultFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.news_list, container, false);
            initView();
            if(position==0){
                news_header = inflater.inflate(R.layout.news_header, null);
                initHeaderView();
            }

            setListener();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    private void setListener() {
        listener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (position){
                    case 0:
                        NewsHelper.getDataFromService(NetWorkConstans.HOMEPAGE_URL, handler,0);
                        break;
                    case 1:
                        NewsHelper.getDataFromService(NetWorkConstans.ACTIVITY_URL, handler,0);
                        break;
                    case 2:
                        NewsHelper.getDataFromService(NetWorkConstans.ENTERTAINMENT_URL, handler,0);
                        break;
                    case 3:
                        NewsHelper.getDataFromService(NetWorkConstans.INSTITUTE_URL, handler,0);
                        break;
                }

            }
        };
        swipe_layout.setOnRefreshListener(listener);
        swipe_layout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int headerViewsCount = lv_news.getHeaderViewsCount();
                position = position - headerViewsCount;
                String article_url = data.list.get(position).article_url;
                String is_direct = data.list.get(position).is_direct;
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("is_direct", is_direct);
                intent.putExtra("article_url", article_url);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initHeaderView() {
        viewpager = (ViewPager) news_header.findViewById(R.id.viewpager);
        indicator = (CircleIndicator) news_header.findViewById(R.id.indicator);
        lv_news.addHeaderView(news_header);
    }

    private void initView() {
        swipe_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        lv_news = (ListView) view.findViewById(R.id.lv_news);

    }


    public void initData() {
        switch (position){
            case 0:
                NewsHelper.getDataFromLocal(NetWorkConstans.HOMEPAGE_URL, handler);
                getTopData();
                break;
            case 1:
                NewsHelper.getDataFromLocal(NetWorkConstans.ACTIVITY_URL, handler);
                break;
            case 2:
                NewsHelper.getDataFromLocal(NetWorkConstans.ENTERTAINMENT_URL, handler);
                break;
            case 3:
                NewsHelper.getDataFromLocal(NetWorkConstans.INSTITUTE_URL, handler);
                break;
        }
    }

    private void getTopData() {
        NewsHelper.getDataFromService(NetWorkConstans.RECOMMANDPAGE_URL, handler, 1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("tag", "teststring");
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
