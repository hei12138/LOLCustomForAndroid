package com.example.hei123.lolcustom.Model;

import java.util.ArrayList;

/**
 * Created by hei123 on 10/14/2016.
 * CopyRight @hei123
 * 新闻模型 解析出模型数据
 */

public class NewsModel {

    public String previous;
    public String next ;
    public int this_page_num ;
    public ArrayList<NewsItem> list ;
    public class NewsItem
    {
        public String channel_id ;
        public String channel_desc ;
        public int article_id ;
        public String insert_date ;
        public String publication_date ;
        public String is_direct ;
        public String is_top ;
        public String article_url ;
        public String title ;
        public String image_url_small ;
        public String image_url_big;
        public int image_spec ;
        public String image_with_btn ;
        public int score ;
        public String summary ;
        public String targetid ;
        public int is_act ;
        public int is_hot ;
        public int is_subject ;
        public String is_report ;
        public int is_new ;
    }
}
