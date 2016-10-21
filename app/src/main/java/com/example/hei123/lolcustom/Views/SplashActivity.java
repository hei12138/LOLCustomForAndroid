package com.example.hei123.lolcustom.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.hei123.lolcustom.R;

/**
 * Created by hei123 on 10/20/2016.
 * CopyRight @hei123
 */

public class SplashActivity extends Activity {
    private RelativeLayout rlroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlroot = (RelativeLayout) findViewById(R.id.rlroot);
        //旋转动画
        RotateAnimation animRotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);//保持动画结束状态
        //缩放
        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animScale.setDuration(500);
        animScale.setFillAfter(true);
        //渐变
        AlphaAnimation animAlpha = new AlphaAnimation(0.5f, 1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);
        //动画集合
        AnimationSet set = new AnimationSet(true);
        //set.addAnimation(animRotate);
        //set.addAnimation(animScale);
        set.addAnimation(animAlpha);
        rlroot.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束,跳转页面
                // 如果是第一次进入, 跳新手引导
                // 否则跳主页面
                //boolean isFirstEnter = PrefUtils.getBoolean(
                //        SplashActivity.this, "is_first_enter", true);
                //boolean isFirstEnter= PreUtils.getBoolean(SplashActivity.this,"is_first_enter",true);

                Intent intent;

                // 主页面
                intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);

                finish();// 结束当前页面
            }
        });
    }
}
