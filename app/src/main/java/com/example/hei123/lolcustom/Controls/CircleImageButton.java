package com.example.hei123.lolcustom.Controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class CircleImageButton extends ImageButton {
    private Paint mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mRawBitmap;
    private BitmapShader mShader;
    private Matrix mMatrix = new Matrix();
    public CircleImageButton(Context context) {
        super(context);
    }

    public CircleImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap rawBitmap = getBitmap(getDrawable());
        //canvas.drawBitmap(rawBitmap,0,0,mPaintBitmap);
        if (rawBitmap != null){
            //获取到控件的宽高
            int viewWidth = getWidth();
            //Log.i("getwidth","getwidth"+viewWidth);
            int viewHeight = getHeight();
            //Log.i("getwidth","height"+viewHeight);

            //Log.i("tag", "i"+getBackground().getIntrinsicWidth());
            int viewMinSize = Math.min(viewWidth, viewHeight);
            float dstWidth = viewMinSize;
            float dstHeight = viewMinSize;
            if (mShader == null || !rawBitmap.equals(mRawBitmap)){
                mRawBitmap = rawBitmap;
                mShader = new BitmapShader(mRawBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            }
            if (mShader != null){
                //设置缩放
                mMatrix.setScale(dstWidth / rawBitmap.getWidth(), dstHeight / rawBitmap.getHeight());
                mShader.setLocalMatrix(mMatrix);
            }
            mPaintBitmap.setShader(mShader);
            float radius = viewMinSize / 2.0f;
            //canvas.drawCircle(radius, radius, radius - mBorderWidth / 2.0f, mPaintBorder);

            canvas.drawCircle(radius, radius, radius, mPaintBitmap);
        } else {
            super.onDraw(canvas);
        }
    }
    //把drawable变成bitmap
    private Bitmap getBitmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            return ((BitmapDrawable)drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable){
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            int color = ((ColorDrawable)drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        } else {
            return null;
        }
    }
}
