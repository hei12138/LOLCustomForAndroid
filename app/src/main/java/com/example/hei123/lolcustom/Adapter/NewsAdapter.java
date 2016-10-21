package com.example.hei123.lolcustom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hei123.lolcustom.Model.NewsModel;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.MyBitmapUtils;


/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class NewsAdapter extends BaseAdapter {
    private NewsModel newsModel;
    private Context context;
    private MyBitmapUtils bitmapUtils;
    public NewsAdapter(NewsModel model, Context context1){
        this.newsModel=model;
        this.context=context1;
        bitmapUtils=new MyBitmapUtils();

    }
    @Override
    public int getCount() {
        return newsModel.list.size();
    }

    @Override
    public NewsModel.NewsItem getItem(int position) {
        return newsModel.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.list_item_news,null);
            holder=new NewsViewHolder();
            holder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_data);
            convertView.setTag(holder);
        }else{
            holder= (NewsViewHolder) convertView.getTag();
        }
        NewsModel.NewsItem news = getItem(position);
        holder.tv_title.setText(news.title);
        holder.tv_date.setText(news.insert_date);

//        String read_ids = PreUtils.getString(mActivity, "read_ids", "");
//        if(read_ids.contains(news.article_id+"")){
//            holder.tv_title.setTextColor(Color.GRAY);
//        }else{
//            holder.tv_title.setTextColor(Color.BLACK);
//        }
        //设置图片
        //mbit.display()
        //holder.iv_icon.setImageResource(R.mipmap.news_pic_default);

        bitmapUtils.display(holder.iv_icon,news.image_url_small);
          return convertView;
    }
}

