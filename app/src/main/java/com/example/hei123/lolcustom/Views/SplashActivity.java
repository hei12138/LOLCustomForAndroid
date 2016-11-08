package com.example.hei123.lolcustom.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.example.hei123.lolcustom.R;
import com.example.hei123.lolcustom.Utils.PermissionsUtil;

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
        //PermissionsUtil.checkAndRequestPermissions(this);
        requestAllPermission();
    }
    private void startAnim(){
        //渐变
        AlphaAnimation animAlpha = new AlphaAnimation(1, 1);
        animAlpha.setDuration(1000);
        animAlpha.setFillAfter(true);
        rlroot.startAnimation(animAlpha);
        animAlpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent;
                // 主页面
                intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);

                finish();// 结束当前页面
            }
        });
    }

    /**
     * 请求获取全部权限，调用权限管理类，然后放进去需要申请的权限，通过接口回调的方法获得权限获取结果
     */
    private void requestAllPermission() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                myHandler.sendEmptyMessage(GETPERMISSION_SUCCESS);
            }

            @Override
            public void onDenied(String permission) {
                myHandler.sendEmptyMessage(GETPERMISSION_FAILER);
            }
        });
//        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(SplashActivity.this, ConstantString.permissions, new PermissionsResultAction() {
//            @Override
//            public void onGranted() {
//                //L.i("onGranted");
//                myHandler.sendEmptyMessage(GETPERMISSION_SUCCESS);
//            }
//
//            @Override
//            public void onDenied(String permission) {
//                //L.i("onDenied");
//                myHandler.sendEmptyMessage(GETPERMISSION_FAILER);
//            }
//        });
    }



    private static final int GETPERMISSION_SUCCESS = 1;//获取权限成功
    private static final int GETPERMISSION_FAILER = 2;//获取权限失败
    private MyHandler myHandler = new MyHandler();

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GETPERMISSION_SUCCESS:
                    startAnim();
                    break;
                case GETPERMISSION_FAILER:
                    //T.showShort(SplashActivity.this, "应用没有获取权限，请重新打开应用，或者到设置页面添加权限以后再从新打开。");
                    Toast.makeText(SplashActivity.this,"应用没有获取权限,请重新打开应用，或者到设置页面添加权限",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    }

    //因为权限管理类无法监听系统，所以需要重写onRequestPermissionResult方法，更新权限管理类，并回调结果。这个是必须要有的。
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
