package com.example.hei123.lolcustom.Model;

import java.util.ArrayList;

/**
 * Created by hei123 on 11/2/2016.
 * CopyRight @hei123
 */

public class WallpaperModel {
    public String result;
    public ArrayList<PaperItem> wallpapers;
    public class PaperItem{
        /**
         * "id": "1166",
         "name": "银河魔装机神千珏_Riot",
         "author": "掌上英雄联盟",
         "kind_type": "1",
         "thumb_width": "180",
         "thumb_height": "320",
         "url": "http://ossweb-img.qq.com/upload/qqtalk/lol_hero/wp_1166.jpg?2447",
         "thumbUrl": "http://ossweb-img.qq.com/upload/qqtalk/lol_hero/wp_1166_128.jpg?2447"
         */
        public String id;
        public String name;
        public String author;
        public String kind_type;
        public int thumb_width;
        public int thumb_height;
        public String url;
        public String thumbUrl;
    }
    public int hasnext;

}
