package com.example.hei123.lolcustom.Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SimpleCursorTreeAdapter;

import com.example.hei123.lolcustom.Global.NetWorkConstans;
import com.example.hei123.lolcustom.Utils.HttpRequest;
import com.example.hei123.lolcustom.Utils.MD5Encoder;
import com.example.hei123.lolcustom.Utils.PreUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by hei123 on 10/22/2016.
 * CopyRight @hei123
 */

public class NewsHelper {
    public static final int MSG_SUCCESS_LOCAL=0;
    public static final int MSG_SUCCESS_NET=1;
    public static final int MSG_FAIL_LOCAL=3;
    public static final int MSG_FAIL_NET=4;
    public static final int MSG_TOP_SUCCESS=5;
    public static final int ERROR=6;

    /**
     * msg what
     * 0 表示数据来自网络 获取成功
     * 1 表示数据来自本地 获取成功
     * 2 表示数据获取失败
     */


    /**
     *
     * @param url
     * @param handler
     */
    public static void getDataFromService(final String url, final Handler handler,int type) {
        final Message msg = Message.obtain();
        switch (type){
            case 0:
                msg.what=MSG_SUCCESS_NET;
                //说明是新闻数据
                break;
            case 1:
                msg.what=MSG_TOP_SUCCESS;
                //说明是推荐数据
                break;
        }
        new Thread() {
            @Override
            public void run() {

                try {
                    String response = HttpRequest.get(url).body();
                    //写缓存
                    writeLocalCache(url,response);
                    Bundle b = new Bundle();
                    b.putString("data", response);
                    msg.setData(b);
                } catch (Exception e) {
                    msg.what = ERROR;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }

    public static void getDataFromLocal(final String url, final Handler handler) {
        String data = readLocalCache(url);
        Message msg = Message.obtain();
        if(data!=null){
            //有数据
            msg.what = MSG_SUCCESS_LOCAL;
            Bundle b = new Bundle();
            b.putString("data", data);
            msg.setData(b);
        }else{
            //没有数据
            msg.what = MSG_FAIL_LOCAL;
        }
        handler.sendMessage(msg);

    }

    //好像最后一个字符没写进去
    public static void writeFileCache(String url, String data) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCache";
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();//创建文件夹
        }
        //String path2="data/data/com.example.hei123.lolcustom"
        FileWriter fw = null;
        File f = new File(path, "a.txt");

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(data, 0, data.length() - 1);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串写入到缓存文件家中
     * @param url
     * @param data
     */
    public static void writeLocalCache(String url, String data) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LOLCUSTOM";
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();//创建文件夹
        }
        try {
            File f = new File(path, MD5Encoder.encode(url));
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data.getBytes());
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String readLocalCache(String url) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LOLCUSTOM";
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();//创建文件夹
        }
        StringBuilder str = new StringBuilder("");

        try {
            File file = new File(path, MD5Encoder.encode(url));
            if (!file.exists()) {
                file.createNewFile();
            }
            Reader reader = null;

            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    str.append((char) tempchar);
                }
            }
            reader.close();
            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        try {
//            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
//            // 一次读多个字符
//            char[] tempchars = new char[30];
//            int charread = 0;
//            reader = new InputStreamReader(new FileInputStream(fileName));
//            // 读入多个字符到字符数组中，charread为一次读取字符数
//            while ((charread = reader.read(tempchars)) != -1) {
//                // 同样屏蔽掉\r不显示
//                if ((charread == tempchars.length)
//                        && (tempchars[tempchars.length - 1] != '\r')) {
//                    System.out.print(tempchars);
//                } else {
//                    for (int i = 0; i < charread; i++) {
//                        if (tempchars[i] == '\r') {
//                            continue;
//                        } else {
//                            System.out.print(tempchars[i]);
//                        }
//                    }
//                }
//            }
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
    }


    //    public <T> NewsHelper(String url,Class<T> c){
//
//    }
//
//    @Override
//    protected Object doInBackground(Object[] params) {
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);
//    }
    public static class CacheUtils {
        /**
         * 设置缓存
         *
         * @param key   url
         * @param value json
         */
        public static void setCache(String key, String value, Context context) {
            //也可以用文件缓存 以url为文件名 以json为文件内容
            PreUtils.setString(context, key, value);
        }

        /**
         * 获取缓存
         *
         * @param key
         * @param context
         * @return
         */
        public static String getCache(String key, Context context) {
            return PreUtils.getString(context, key, null);
        }

    }
}
