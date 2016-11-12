package com.example.hei123.lolcustom.Picture;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hei123.lolcustom.Controls.HackyViewPager;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.MyBitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by hei123 on 11/3/2016.
 * CopyRight @hei123
 */

public class FullScreenDialogFragment extends DialogFragment implements View.OnClickListener {
    private int mClickItem;//对应显示ViewPager子项的位置
    private List<String> mListImgUrls;
    private HackyViewPager mViewPager;
    private Integer[] mImgIds;//本地图片资源ID
    private Dialog mDialog;
    //public static final String TAG_NAME=AlertDlgFragment.class.getName();
    private Context mContext;

    //即学即用的工厂方法
    public static FullScreenDialogFragment newInstance(Context context, Integer[] imgIds, int clickItem) {
        Bundle args = new Bundle();
        FullScreenDialogFragment fragment = new FullScreenDialogFragment();
        fragment.setArguments(args);
        fragment.mContext = context;
        fragment.mImgIds = imgIds;
        fragment.mClickItem = clickItem;
        return fragment;
    }

    public static FullScreenDialogFragment newInstance(Context context, List<String> listUrl, int clickItem) {
        Bundle args = new Bundle();
        FullScreenDialogFragment fragment = new FullScreenDialogFragment();
        fragment.setArguments(args);
        fragment.mContext = context;
        fragment.mListImgUrls = listUrl;
        fragment.mClickItem = clickItem;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //设置Dialog样式
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog_fill);
        //setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        mDialog = new Dialog(mContext);
        //这样才能设置为全屏
        //mDialog=new Dialog(mContext, R.style.CustomDialog_fill);
        //去标题栏
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        return mDialog;
    }

    private void initView() {
        //将Dialog设置全屏！！！
        setDlgParams();
        View view = View.inflate(mContext, R.layout.dialog_normal_layout, null);
        mViewPager = (HackyViewPager) view.findViewById(R.id.viewpager);
        view.findViewById(R.id.btn_save).setOnClickListener(this);
        view.findViewById(R.id.btn_setwallpaper).setOnClickListener(this);
        view.findViewById(R.id.btn_share).setOnClickListener(this);
        mViewPager.setBackgroundColor(0xFF000000);
        initViewPager();
        mDialog.setContentView(view);
    }

    private void setDlgParams() {
        ViewGroup.LayoutParams lay = mDialog.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        mDialog.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect rect = new Rect();
        View view = mDialog.getWindow().getDecorView();
        //设置全屏时不需要setpadding
        //view.setPadding(0,0,0,0);
        view.getWindowVisibleDisplayFrame(rect);
        lay.height = dm.heightPixels - rect.top - 80;
        lay.width = dm.widthPixels;
    }

    private void initViewPager() {
        if (mImgIds != null && mImgIds.length > 0) {
//            List<View> listImgs = new ArrayList<>();
//            for (int i = 0; i < mImgIds.length; i++) {
//                ImageView iv = new ImageView(mContext);
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                iv.setLayoutParams(params);
//                listImgs.add(iv);
//                iv.setImageResource(mImgIds[i]);
//            }
            if (mImgIds.length > 0) {
                MyPagerAdapter pageAdapter = new MyPagerAdapter(mImgIds);
                mViewPager.setAdapter(pageAdapter);
                mViewPager.setCurrentItem(mClickItem);
            }
        } else if (mListImgUrls != null && mListImgUrls.size() > 0) {
//            List<View> listImgs=new ArrayList<>();
//            for(int i=0;i<mListImgUrls.size();i++){
//                ImageView iv=new ImageView(mContext);
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                iv.setLayoutParams(params);
//                listImgs.add(iv);
//            }
            MyPagerAdapter pageAdapter = new MyPagerAdapter(mListImgUrls);
            mViewPager.setAdapter(pageAdapter);
            mViewPager.setCurrentItem(mClickItem);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                Log.e("tag","点击了保存按钮"+mViewPager.getCurrentItem());
                break;
            case R.id.btn_setwallpaper:
                break;
            case R.id.btn_share:
                break;
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        int mode;
        Integer[] datas;
        List<String> stringList;

        public MyPagerAdapter(Integer[] data) {
            //表示当前位本地模式
            this.mode = 0;
            this.datas = data;
        }

        public MyPagerAdapter(List<String> stringList) {
            //表示位网络模式
            this.mode = 1;
            this.stringList = stringList;
        }


        @Override
        public int getCount() {
            switch (mode) {
                case 0:
                    return datas.length;
                case 1:
                    return stringList.size();
                default:
                    break;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = View.inflate(mContext, R.layout.full_screen_dialog_item, null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_image);
            final TextView tv_progress = (TextView) inflate.findViewById(R.id.tv_progress);

            final PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
            if (mListImgUrls != null && mListImgUrls.size() > 0) {
                DisplayImageOptions options = new DisplayImageOptions.Builder()
//                        .showImageOnLoading(R.mipmap.news_image_normal)
                        .showImageOnFail(R.mipmap.news_image_normal)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();
                ImageLoader.getInstance().displayImage(mListImgUrls.get(position), imageView, options, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        mAttacher.update();
                        tv_progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                }, new ImageLoadingProgressListener() {
                    //i current  il total
                    @Override
                    public void onProgressUpdate(String s, View view, int i, int i1) {
                        tv_progress.setVisibility(View.VISIBLE);
                        //在这里添加进度条
                        int progress = i * 100 / i1;
                        tv_progress.setText("正在加载:" + progress + "%");
                    }
                });
            }
            mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    FullScreenDialogFragment.this.dismiss();
                }
            });
            container.addView(inflate, 0);//添加页卡
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);//删除页卡
        }
    }
}
