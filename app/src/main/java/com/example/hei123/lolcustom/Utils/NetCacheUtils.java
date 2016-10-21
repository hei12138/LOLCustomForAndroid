package com.example.hei123.lolcustom.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hei123 on 10/18/2016.
 * CopyRight @hei123
 * 网络缓存工具类
 */

public class NetCacheUtils {

    private ImageView imageView;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        this.mLocalCacheUtils=localCacheUtils;
        this.mMemoryCacheUtils=memoryCacheUtils;
    }

    public void getBitmapFromNet(ImageView imageView, String url) {
        //AsyncTask 异步封装的工具 实现异步请求和主界面更新 线程池加handle的封装
        Log.i("tag","task任务");
        imageView.setTag(url);
        new BitmapTask().execute(imageView,url);//启动asyncTask

    }

    /**
     * 三个泛型的意义
     * 1。doinBackground里的参数类型可变数组
     * 2.opProgressUpdate的参数类型 可变数组
     * 3返回的参数类型
     *
     */
    class BitmapTask extends AsyncTask<Object,Integer,Bitmap>{

        private String url;

        //正在加载 运行在子线程
        @Override
        protected Bitmap doInBackground(Object... params) {
            imageView = (ImageView) params[0];
            url = (String) params[1];

            //开始下载图片
            Bitmap bitmap=download(url);
            Log.i("tag","downloadbitmap");
            //publishProgress();调用此方法实现进度更新 回调onpregressUpdate
            return bitmap;
        }

        //预加载 运行在主线程
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //进度更新 运行在主线程
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //更新进度条
        }
        //加载结束 运行在主线程
        @Override
        protected void onPostExecute(Bitmap result) {
            if(result!=null){
                //由于可能设置给错误的对象
                //设置校验判断是否是正确的对象
                String url= (String) imageView.getTag();
                if(url.equals(this.url)){
                    imageView.setImageBitmap(result);
                    Log.i("tag","从网络加载图片啦");
                    mLocalCacheUtils.setLocalCache(url,result);
                    mMemoryCacheUtils.setMemoryCache(url,result);
                }
            }
            super.onPostExecute(result);

        }


    }

    private Bitmap download(String url) {
        //下载图片
        HttpURLConnection conn=null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode==200){
                //Log.i("tag","code=200成功连接");
                InputStream inputStream = conn.getInputStream();
                //根据输入流生成一个bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            //Log.i("tag","出错了");
        }finally {
            if(conn!=null){
                conn.disconnect();
            }
        }
        return null;
    }
}
