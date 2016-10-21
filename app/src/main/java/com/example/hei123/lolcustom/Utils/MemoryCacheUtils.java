package com.example.hei123.lolcustom.Utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

/**
 * Created by hei123 on 10/19/2016.
 * CopyRight @hei123
 * 内存缓存 建议使用lrucache
 */

public class MemoryCacheUtils {
    //private HashMap<String,SoftReference <Bitmap>> mMemoryCache=new HashMap<>();
    private LruCache<String,Bitmap> mMemoryCache;
    public MemoryCacheUtils(){
        //lruCache可以将最近最少使用的对象回收掉
        long maxM = Runtime.getRuntime().maxMemory();
        Log.i("tag","maxMemory"+maxM);
        mMemoryCache=new LruCache<String,Bitmap>((int) (maxM/8)){
            //返回每个对象的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };
    }
    public void setMemoryCache(String url,Bitmap bitmap){
//        Log.i("tag","设置内存缓存");
//        Log.i("tag","url为"+url);
//        SoftReference<Bitmap> soft=new SoftReference<Bitmap>(bitmap);
//        mMemoryCache.put(url,soft);
        mMemoryCache.put(url,bitmap);
    }
    public Bitmap getMemoryCache(String url){
//        SoftReference<Bitmap> bitmapSoftReference = mMemoryCache.get(url);
//        if(bitmapSoftReference!=null){
//            Log.i("tag","读取内存缓存"+url);
//            Bitmap bitmap = bitmapSoftReference.get();
//            return bitmap;
//        }
//        return null;
        return mMemoryCache.get(url);
    }

}
