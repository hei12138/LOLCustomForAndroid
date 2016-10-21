package com.example.hei123.lolcustom.Controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by hei123 on 10/9/2016.
 */

public class LOLImageHeader extends ImageView {

    private Paint mPaintBitmap = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mRawBitmap;
    private BitmapShader mShader;
    private Matrix mMatrix = new Matrix();
    private float mBorderWidth = dip2px(3);
    private int mBorderColor = 0xFF0080FF;

    public LOLImageHeader(Context context) {
        super(context);
    }

    public LOLImageHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LOLImageHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImageViewPlus);
        //mBorderColor = ta.getColor(R.styleable.ImageViewPlus_borderColor, DEFAULT_BORDER_COLOR);
        //mBorderWidth = ta.getDimensionPixelSize(R.styleable.ImageViewPlus_borderWidth, dip2px(DEFAULT_BORDER_WIDTH));
        //ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap rawBitmap = toLOLBitmap(getBitmap(getDrawable()));
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
                mMatrix.setScale((dstWidth - mBorderWidth * 2) / rawBitmap.getWidth(), (dstHeight - mBorderWidth * 2) / rawBitmap.getHeight());
                mShader.setLocalMatrix(mMatrix);
            }
            mPaintBitmap.setShader(mShader);
            mPaintBorder.setStyle(Paint.Style.STROKE);
            mPaintBorder.setStrokeWidth(mBorderWidth);
            mPaintBorder.setColor(mBorderColor);
            float radius = viewMinSize / 2.0f;
            //canvas.drawCircle(radius, radius, radius - mBorderWidth / 2.0f, mPaintBorder);
            canvas.translate(mBorderWidth, mBorderWidth);
            canvas.drawCircle(radius - mBorderWidth, radius - mBorderWidth, radius - mBorderWidth, mPaintBitmap);
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

    private int dip2px(int dipVal)
    {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(dipVal * scale+0.5f);
    }



    public Bitmap toLOLBitmap(Bitmap bitmap){
        //构建一个bitmap
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int MinSize = Math.min(width, height);

        Bitmap backgroundBmp = Bitmap.createBitmap(MinSize,
                MinSize, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        //设置边缘光滑，去掉锯齿
        paint.setAntiAlias(true);
        //通过path画特殊图形
        Path path=new Path();
        path.moveTo(MinSize/2+15,0);//设置起点


        RectF oval=new RectF();//RectF对象
        oval.left=0;                              //左边
        oval.top=0;                                   //上边
        oval.right=MinSize;                             //右边
        oval.bottom=MinSize;//下边
        //path.addArc(oval,-75,330);
        path.arcTo(oval,-75,330);
        path.lineTo(MinSize/2,16);
        path.lineTo(MinSize/2+15,0);
        path.close();
        canvas.drawPath(path,paint);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap,0,0,paint);
        //返回已经绘画好的backgroundBmp
        return backgroundBmp;

    }
}
