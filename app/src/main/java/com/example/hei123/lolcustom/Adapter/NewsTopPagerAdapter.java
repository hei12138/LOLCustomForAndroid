package com.example.hei123.lolcustom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hei123.lolcustom.Model.NewsModel;
import com.example.hei123.lolcustom.News.NewsDetailActivity;
import com.example.hei123.lolcustom.Utils.MyBitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hei123 on 2016/10/24.
 * CopyRight @hei123
 */

public class NewsTopPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> datas;
    public NewsTopPagerAdapter(ArrayList<NewsModel.NewsItem> items, final Context context){
        datas=new ArrayList<>();
        MyBitmapUtils bitmapUtils=new MyBitmapUtils();
        for (final NewsModel.NewsItem item:items) {
            ImageView imageView = new ImageView(context);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String article_url = item.article_url;
                    String is_direct = item.is_direct;
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    intent.putExtra("is_direct", is_direct);
                    intent.putExtra("article_url", article_url);
                    context.startActivity(intent);
                }
            });
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bitmapUtils.display(imageView,item.image_url_big);
            datas.add(imageView);
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(datas.get(position));
        return datas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(datas.get(position));
    }
}
