package com.example.hei123.lolcustom.News;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.example.hei123.lolcustom.R;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class NewsDetailActivity extends Activity{

    private WebView webview;
    private static final String LOG_TAG = "WebView";
    private boolean is_dicrect=false;
    private String wholeJS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Intent intent = getIntent();
        String is_direct = intent.getStringExtra("is_direct");
        String article_url = intent.getStringExtra("article_url");
        initView();
        if (is_direct.equals("True")){
            Log.i("tag",article_url);
            is_dicrect=true;
            webview.loadUrl(article_url);
        }else{
            String format = String.format("http://101.226.76.163/static/pages/news/phone/%s", article_url);
            // var apiUrl = string.Format("http://101.226.76.163/static/pages/news/phone/{0}", str);
            webview.loadUrl(format);
            Log.i("tag",format);
        }
    }

    public void injectImgSources() {
        try {

            InputStream in =  getAssets().open("image.js");
            byte buff[] = new byte[1024];
            ByteArrayOutputStream fromFile = new ByteArrayOutputStream();
            FileOutputStream out = null;
            do {
                int numread = in.read(buff);
                if (numread <= 0) {
                    break;
                }
                fromFile.write(buff, 0, numread);
            } while (true);
            wholeJS = fromFile.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        webview.loadUrl("javascript:("+wholeJS+")()");
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setSaveFormData(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(!is_dicrect){
                    injectImgSources();
                }
            }
        });


    }


}
