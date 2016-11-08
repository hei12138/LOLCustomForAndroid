package com.example.hei123.lolcustom.Utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.example.hei123.lolcustom.R;


/**
 * Created by hei123 on 10/18/2016.
 * CopyRight @hei123
 */

public class MyBitmapUtils {

    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;
    public MyBitmapUtils(){
        mLocalCacheUtils=new LocalCacheUtils();
        mMemoryCacheUtils=new MemoryCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils,mMemoryCacheUtils);

    }
    public static MyBitmapUtils getInstanse(){
        return new MyBitmapUtils();
    }

    public void display (ImageView imageView, String url){
        //Log.i("tag",mMemoryCacheUtils.toString());
        //设置默认图片
        imageView.setImageResource(R.mipmap.news_image_normal);
        //优先从内存中加载图片
        Bitmap bitmap = mMemoryCacheUtils.getMemoryCache(url);
        if(bitmap!=null){
            //Log.i("tag","从内存加载图片啦");
            imageView.setImageBitmap(bitmap);
            return;
        }
        //其次从sd卡加载图片
        bitmap = mLocalCacheUtils.getLocalCache(url);
        if(bitmap!=null){
            //有本地缓存
            imageView.setImageBitmap(bitmap);
            //Log.i("tag","从本地加载图片啦");
            //写内存缓存
            mMemoryCacheUtils.setMemoryCache(url,bitmap);
            return;
        }
        //最后从网络中加载啊
        mNetCacheUtils.getBitmapFromNet(imageView,url);

    }
}
