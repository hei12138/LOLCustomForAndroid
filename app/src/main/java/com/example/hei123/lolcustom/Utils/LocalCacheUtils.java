package com.example.hei123.lolcustom.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by hei123 on 10/19/2016.
 * CopyRight @hei123
 * 本地缓存
 */

public class LocalCacheUtils {
    private static final String LOCAL_CACHE_PATH= Environment.getExternalStorageDirectory().
            getAbsolutePath()+"/zhbj_cache";
    //写缓存
    public void setLocalCache(String url, Bitmap bitmap){
        File dir=new File(LOCAL_CACHE_PATH);
        if(!dir.exists()||!dir.isDirectory()){
            dir.mkdirs();//创建文件夹
        }
        try {
            String fileName = MD5Encoder.encode(url);
            File cacheFile=new File(dir,fileName);
            //保存到本地
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(cacheFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //读缓存
    public Bitmap getLocalCache(String url){
        String fileName = null;
        try {
            fileName = MD5Encoder.encode(url);
            File cacheFile=new File(LOCAL_CACHE_PATH,fileName);
            if(cacheFile.exists()){
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(cacheFile));
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
