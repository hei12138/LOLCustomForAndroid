package com.example.hei123.lolcustom.Controls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.ColorUtil;
import com.nineoldandroids.view.ViewHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hei123 on 10/15/2016.
 * CopyRight @hei123
 */

public class MyPullToRefreshListView extends ListView {

    private View mHeaderView;
    private int mHeaderViewHeight;
    private int startY=-1;
    private static final int STATE_PULL_TOREFRESH=1;
    private static  final int STATE_RELEASE_TO_REFRESH=2;
    private static  final  int STATE_REFRESHING=3;

    private int mCurrentState=STATE_PULL_TOREFRESH;
    private TextView tv_title;
    private TextView tv_data;
    private TextView tv_red;
    private TextView tv_green;
    private RotateAnimation animRotate;
    private int startX;

    public MyPullToRefreshListView(Context context) {
        super(context);
        initHeaderView();
    }

    public MyPullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
    }

    public MyPullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
    }
    public void initHeaderView(){
        mHeaderView = View.inflate(getContext(), R.layout.my_pull_to_refresh,null);
        this.addHeaderView(mHeaderView);
        tv_title = (TextView) mHeaderView.findViewById(R.id.tv_title);
        tv_data = (TextView) mHeaderView.findViewById(R.id.tv_data);
        tv_red = (TextView) mHeaderView.findViewById(R.id.tv_red);
        tv_green = (TextView) mHeaderView.findViewById(R.id.tv_green);
        mHeaderView.measure(0,0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
        initAnim();
        setCurrentTime();
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
//                if(mCurrentState==STATE_PULL_TOREFRESH){
//                    return true;
//                }
                startY = (int) ev.getY();
                startX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //getParent().requestDisallowInterceptTouchEvent(true);
                if(startY==-1){
                    //当用户按住头条新闻的viewpage进行下拉时
                    //action_down可能会被viewpager消费掉
                    //导致starty没有被赋值
                    //重新获取
                    startY= (int) ev.getY();
                }
                if(mCurrentState==STATE_REFRESHING){
                    break;
                }

                int endY= (int) ev.getY();
                int endX= (int) ev.getX();
                int dx=endX-startX;
                int dy=endY-startY;
                //设置父控件不拦截上下滑动
                if(Math.abs(dx)<Math.abs(dy)){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                //下拉并且当前显示的是第一个item
                if(dy>0&&getFirstVisiblePosition()==0){
                    int padding=dy-mHeaderViewHeight;//计算当前下拉布局控件的padding
                    mHeaderView.setPadding(0,padding,0,0);
                    float fraction=dy*1.0f/getMeasuredHeight();
                    //2/执行一系列的伴随动画
                    excuteAnimation(fraction);
                    if(padding>0&&mCurrentState!=STATE_RELEASE_TO_REFRESH){
                        mCurrentState=STATE_RELEASE_TO_REFRESH;
                        refreshState();
                    }else if(padding<0&&mCurrentState!=STATE_PULL_TOREFRESH){
                        mCurrentState=STATE_PULL_TOREFRESH;
                        refreshState();
                    }
                    //return  true;
                }

                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                startY=-1;
                if(mCurrentState==STATE_RELEASE_TO_REFRESH){
                    mCurrentState=STATE_REFRESHING;
                    refreshState();
                    //完全展示
                    mHeaderView.setPadding(0,0,0,0);
                    excuteAnimation(0);
                    if(mListener!=null) mListener.onRefresh();
                }else if(mCurrentState==STATE_PULL_TOREFRESH){
                    mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 初始化箭头动画
     */
    private void initAnim(){
        animRotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animRotate.setDuration(500);
        animRotate.setFillAfter(true);
        //animRotate.setRepeatMode(Animation.RESTART);
        animRotate.setRepeatCount(Animation.INFINITE);


    }
    public void  onRefreshComplete(boolean success){
        if(success){
            setCurrentTime();
        }
        mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
        //更新状态
        mCurrentState=STATE_PULL_TOREFRESH;
        tv_green.clearAnimation();
        tv_red.clearAnimation();
        tv_title.setText("下拉刷新");

    }

    private void excuteAnimation(float fraction){
        //fraction0-1
        //ViewHelper.setScaleX(redView,1+0.5f*fraction);
        //ViewHelper.setScaleY(redView,1+0.5f*fraction);
        ViewHelper.setRotation(tv_red,720*fraction);
        ViewHelper.setRotation(tv_green,720*fraction);
        //ViewHelper.setTranslationX(redView,80*fraction);
        //设置过渡颜色的渐变
        tv_red.setBackgroundColor((Integer) ColorUtil.evaluateColor(fraction, Color.RED,Color.GREEN));
        tv_green.setBackgroundColor((Integer) ColorUtil.evaluateColor(fraction, Color.GREEN,Color.RED));
    }

    private void setCurrentTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        tv_data.setText(time);
    }

    private void refreshState(){
        //根据当前状态来舒心界面
        switch (mCurrentState){
            case STATE_PULL_TOREFRESH:
                tv_title.setText("下拉刷新");
            break ;
            case STATE_RELEASE_TO_REFRESH:
                tv_title.setText("松开刷新");
                break ;
            case STATE_REFRESHING:
                tv_title.setText("正在刷新");
                tv_red.startAnimation(animRotate);
                tv_green.startAnimation(animRotate);
                break ;
        }
    }
    private OnRefreshListener mListener;
    public void setOnRefreshListener(OnRefreshListener listener){
        this.mListener=listener;
    }

    //下拉刷新的回校接口
    public interface OnRefreshListener{
        void onRefresh();
    }


}
