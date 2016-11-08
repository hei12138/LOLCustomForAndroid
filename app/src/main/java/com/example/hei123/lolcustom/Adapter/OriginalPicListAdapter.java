package com.example.hei123.lolcustom.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hei123.lolcustom.Model.WallpaperModel;
import com.example.hei123.lolcustom.Picture.FullScreenDialogFragment;
import com.example.hei123.lolcustom.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hei123 on 11/7/2016.
 * CopyRight @hei123
 */

public class OriginalPicListAdapter extends RecyclerView.Adapter<OriginalPicListAdapter.OriginalPicViewHolder> {
    private ArrayList<WallpaperModel.PaperItem> data;
    private Context context;
    final List<String> imgUrls;

    public OriginalPicListAdapter(Context ctx, ArrayList<WallpaperModel.PaperItem> wallpapers) {
        context=ctx;
        data=wallpapers;
        imgUrls=new ArrayList<>();
        //构建url数组
        for (int i=0;i<data.size();i++){
            imgUrls.add(data.get(i).url);
        }
    }


    @Override
    public OriginalPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.originalpic_item, parent, false);
        return new OriginalPicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OriginalPicViewHolder holder, final int position) {
        holder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialogFragment.newInstance(context,imgUrls,position).show(((FragmentActivity)context)
                        .getSupportFragmentManager(),"OriginalPicListAdapter");
            }
        });
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.news_image_normal)
                .showImageOnFail(R.mipmap.news_image_normal)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(data.get(position).thumbUrl, holder.iv_image, options);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class OriginalPicViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_image;

        public OriginalPicViewHolder(View itemView) {
            super(itemView);
            iv_image= (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }
}
