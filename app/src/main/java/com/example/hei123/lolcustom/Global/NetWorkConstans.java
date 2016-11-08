package com.example.hei123.lolcustom.Global;

import java.security.PublicKey;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class NetWorkConstans {
    /**
     * 服务器地址
     */
    public static final String SERVER_URL = "http://qt.qq.com/static/pages/news/phone/";
    /**
     * 首页地址
     */
    public static final String HOMEPAGE_URL = SERVER_URL + "c12_list_1.shtml";
    /**
     * 推荐页面地址
     */
    public static final String RECOMMANDPAGE_URL = SERVER_URL + "c13_list_1.shtml";
    /**
     * 赛事地址
     */
    public static final String MATCHPAGE_URL = SERVER_URL + "c73_list_1.shtml";
    /**
     * 活动地址
     */
    public static final String ACTIVITY_URL = SERVER_URL + "c23_list_1.shtml";

    /**
     * 娱乐地址
     */
    public static final String ENTERTAINMENT_URL = SERVER_URL + "c18_list_1.shtml";

    /**
     * 官方地址
     */
    public static final String INSTITUTE_URL = SERVER_URL + "c3_list_1.shtml";
    /**
     *  //获取数据



     //client.Encoding = System.Text.Encoding.UTF8;
     //获取战队数据
     //var json = await client.GetStringAsync(new Uri("http://qt.qq.com/php_cgi/lol_mobile/club/varcache_team_entrancev2.php?plat=android&version=9676"));

     //http://apps.game.qq.com/lol/act/website2013/videoApp.php?page=1&p1=0&p2=0&p3=0&p4=0&p5=0 精彩视频
     //http://apps.game.qq.com/lol/act/website2013/videoApp.php?page=2&p1=0&p2=0&p3=0&p4=0&p5=0
     //http://apps.game.qq.com/lol/act/website2013/videoApp.php?page=1&p1=0&p2=0&p3=0&p4=9999&p5=0 解说
     //http://apps.game.qq.com/lol/act/website2013/videoApp.php?page=1&p1=0&p2=9999&p3=0&p4=0&p5=0 职业联赛
     //http://apps.game.qq.com/lol/act/website2013/videoApp.php?page=1&p1=2&p2=0&p3=0&p4=0&p5=0  娱乐


     //http://ossweb-img.qq.com/upload/qqtalk/lol_hero/hero_22.js 返回英雄数据 格式为json
     //http://ossweb-img.qq.com/upload/qqtalk/lol_hero/goods_list.js  返回物品列表 格式为json


     //list_2 3 4代表页面 c12代表常规页面 c13代表推荐页面 c73赛事 c18娱乐 c3官方 c17美女 c10攻略 c23活动
     //拼接字符串
     var apiUrl = string.Format("http://qt.qq.com/static/pages/news/phone/{0}_list_{1}.shtml",page,list);
     try
     {
     */
    public static final String WALLPAPER_NEW_LIST="http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_list.php?type=new&page=0&num=20&plat=android&version=9709";
    public static final String WALLPAPER_HOT_LIST="http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_list.php?type=hot&page=0&num=20&plat=android&version=9709";

    /**
     * 壁纸
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_list.php?type=new&page=0&num=20&plat=android&version=9709 最新
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_list.php?type=hot&page=0&num=20&plat=android&version=9709 最热
     * http://qt.qq.com/php_cgi/lol_mobile/club/varcache_team_entrancev2.php?plat=android&version=9709 Club列表
     */

    public static final String BING_PAPER="http://appserver.m.bing.net/BackgroundImageService/TodayImageService.svc/GetTodayImage?dateOffset=-0&urlEncodeHeaders=true&osName=windowsPhone&osVersion=8.10&orientation=480x800&deviceName=WP8Device&mkt=zh-CN";

    /**
     * http://appserver.m.bing.net/BackgroundImageService/TodayImageService.svc/GetTodayImage?dateOffset=-0&urlEncodeHeaders=true&osName=windowsPhone&osVersion=8.10&orientation=480x800&deviceName=WP8Device&mkt=zh-CN
     * 必应壁纸
     */

    public static final String SKIN_ORIGINAL="http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=8&page=0&num=20&plat=android&version=9709";

    /**
     * 皮肤原画
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=8&page=0&num=20&plat=android&version=9709
     */
    public static final String CLIENT_FLASH="http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=22&page=0&num=20&plat=android&version=9709";

    /**
     * 张萌闪屏
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=22&page=0&num=20&plat=android&version=9709
     */
    public static final String PLAYER_POST="http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=25&page=0&num=20&plat=android&version=9709";
    /**
     * 玩家投稿
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=25&page=0&num=20&plat=android&version=9709
     */

    public static final String HERO_ORIGINAL="http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=21&page=0&num=20&plat=android&version=9709";
    /**
     * 英雄原画
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=21&page=0&num=20&plat=android&version=9709
     */
    /**
     * vg
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=36&page=0&num=20&plat=android&version=9709
     */
    /**
     * lgd
     * http://qt.qq.com/php_cgi/lol_goods/varcache_wallpaper_kind.php?kind=30&page=0&num=20&plat=android&version=9709
     */

}
