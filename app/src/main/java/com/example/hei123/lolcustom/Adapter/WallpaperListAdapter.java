package com.example.hei123.lolcustom.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hei123.lolcustom.Model.WallpaperModel;
import com.example.hei123.lolcustom.Picture.FullScreenDialogFragment;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.MyBitmapUtils;

import java.lang.annotation.Retention;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hei123 on 11/2/2016.
 * CopyRight @hei123
 */

public class WallpaperListAdapter extends RecyclerView.Adapter<WallpaperListAdapter.WallpaperViewHolder> {
    private ArrayList<WallpaperModel.PaperItem> datas;
    private static Context context;
    private final MyBitmapUtils myBitmapUtils;
    private static ItemClikListener mListener;
    final List<String> imgUrls=new ArrayList<>();

    public void setItemClickListener(ItemClikListener listener){
        this.mListener=listener;
    }

    public WallpaperListAdapter(Context ctx, ArrayList<WallpaperModel.PaperItem> items){
        this.context=ctx;
        this.datas=items;
        myBitmapUtils = new MyBitmapUtils();
        //构建url数组
        for (int i=0;i<datas.size();i++){
            imgUrls.add(datas.get(i).url);
            //Log.i("wallfragment",datas.get(i).thumbUrl+"");
        }
    }

    @Override
    public WallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_item, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WallpaperViewHolder holder, final int position) {
        Log.e("walladapter",""+position);
        holder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("walladapterclick",""+position);
                if(mListener!=null){
                    mListener.OnItemClicked(v,position);
                }
                FullScreenDialogFragment.newInstance(context,imgUrls,position).show(((FragmentActivity)context).getSupportFragmentManager(),"tagh");
            }
        });
        myBitmapUtils.display(holder.iv_image,datas.get(position).thumbUrl);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


   public static class WallpaperViewHolder extends RecyclerView.ViewHolder{

       private final ImageView iv_image;

       public WallpaperViewHolder(View itemView) {
           super(itemView);
           iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
       }
   }
    public interface ItemClikListener{
        void OnItemClicked(View view,int position);
    }


}
