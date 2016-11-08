package com.example.hei123.lolcustom.Controls;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.hei123.lolcustom.Utils.ColorUtil;
import com.nineoldandroids.animation.FloatEvaluator;
import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by hei123 on 10/6/2016.
 */

public class SlideMenu extends FrameLayout {

    private View menuView;
    private View mainView;
    private ViewDragHelper viewDragHelper;
    private int width;
    private float dragRange;
    private FloatEvaluator floatEvaluator;
    private IntEvaluator intEvaluator;
    /**
     * 是否可以开启菜单
     */
    private boolean CanOpenMenu = true;
    private int startX;
    private int startY;

    //定义状态常量
    enum DragState {
        Open, Close;
    }

    private DragState currentState = DragState.Close;

    public SlideMenu(Context context) {
        super(context);
        init();
    }

    private void init() {
        viewDragHelper = ViewDragHelper.create(this, callback);
        floatEvaluator = new FloatEvaluator();
        intEvaluator = new IntEvaluator();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 该方法在Onmeasure执行后执行 在该方法中初始化狂傲
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        dragRange = width * 0.6f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (CanOpenMenu) {
            viewDragHelper.processTouchEvent(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (CanOpenMenu) {
            switch (ev.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int) ev.getX();
                    startY = (int) ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int endY= (int) ev.getY();
                    int endX= (int) ev.getX();
                    int dx=endX- startX;
                    int dy=endY- startY;
                    //设置父控件不拦截上下滑动
                    if(Math.abs(dx)<Math.abs(dy)){
                        return false;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return viewDragHelper.shouldInterceptTouchEvent(ev);
        }
        return false;
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == menuView || child == mainView;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return (int) dragRange;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == mainView) {
                if (left < 0)
                    left = 0;
                if (left > dragRange) left = (int) dragRange;
            }

            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (changedView == menuView) {
                //固定住menuview
                menuView.layout(0, 0, menuView.getMeasuredWidth(), menuView.getMeasuredHeight());
                //让mainView动
                int newleft = mainView.getLeft() + dx;
                if (newleft < 0) newleft = 0;
                if (newleft > dragRange) newleft = (int) dragRange;
                mainView.layout(newleft, mainView.getTop() + dy,
                        mainView.getRight() + dx, mainView.getBottom() + dy);
            }
            //1。计算百分比
            float fraction = mainView.getLeft() / dragRange;
            ecexcuteAnimation(fraction);
            //3.更改状态，回调方法
            if (fraction == 0 && currentState != DragState.Close) {
                currentState = DragState.Close;
                if (listener != null) listener.onClose();
            } else if (fraction == 1 && currentState != DragState.Open) {
                currentState = DragState.Open;
                if (listener != null) listener.onOpen();
            }
            //将drag的百分比暴露给外界
            if (listener != null) {
                listener.onDraging(fraction);
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (mainView.getLeft() < dragRange / 2) {
                //在左半边
                close();
            } else {
                //在右半边
                open();
            }
            //处理速度
            if (xvel > 200 && currentState != DragState.Open) {
                open();
            } else if (xvel < -200 && currentState != DragState.Close) {
                close();
            }
        }
    };

    public void close() {
        viewDragHelper.smoothSlideViewTo(mainView, 0, mainView.getTop());
        ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
    }

    public void open() {
        viewDragHelper.smoothSlideViewTo(mainView, (int) dragRange, mainView.getTop());
        ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
    }


    public void toggle() {
        if(currentState==DragState.Open){
            close();
        }else if(currentState==DragState.Close){
            open();
        }
    }

    /**
     * 获取菜单
     *
     * @return
     */
    public View getMenuView() {
        return menuView;
    }

    /**
     * 获取主页面
     *
     * @return
     */
    public View getMainView() {
        return mainView;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new IllegalArgumentException("slider only have two children");
        }
        //简单的异常处理
        menuView = getChildAt(0);
        mainView = getChildAt(1);
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(SlideMenu.this);
        }
    }

    private void ecexcuteAnimation(float fraction) {
        //缩小mainView fraction0 - 1
        //float scaleValue=0.8f+0.2f*(1-fraction);
        ViewHelper.setScaleX(mainView, floatEvaluator.evaluate(fraction, 1f, 0.8f));
        ViewHelper.setScaleY(mainView, floatEvaluator.evaluate(fraction, 1f, 0.8f));
        //移动menuView
        ViewHelper.setTranslationX(menuView, intEvaluator.evaluate(fraction,
                -menuView.getMeasuredWidth() / 2, 0));
        ViewHelper.setScaleX(menuView, floatEvaluator.evaluate(fraction, 0.6f, 1f));
        ViewHelper.setScaleY(menuView, floatEvaluator.evaluate(fraction, 0.6f, 1f));
        //改变透明度
        ViewHelper.setAlpha(menuView, floatEvaluator.evaluate(fraction, 0.3f, 1f));
        //给Slidermenu背景添加黑色的遮罩
        getBackground().setColorFilter((Integer) ColorUtil.evaluateColor(fraction, Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);

    }

    public DragState getCurrentState() {
        return currentState;
    }

    private OnDragStateChangedListener listener;

    public void setOnDragStateChangedListener(OnDragStateChangedListener listenter) {
        this.listener = listenter;
    }

    public interface OnDragStateChangedListener {

        void onOpen();

        void onClose();

        /**
         * 拖拽中
         */
        void onDraging(float fraction);
    }

    /**
     * 设置是否可以开启菜单
     *
     * @param state true可以开启 false 不能开启
     */
    public void setCanOpenMenu(boolean state) {
        this.CanOpenMenu = state;
    }

}
