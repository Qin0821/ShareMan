package com.youhu.shareman.shareman.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.youhu.shareman.shareman.MainActivity;
import com.youhu.shareman.shareman.R;
import com.youhu.shareman.shareman.base.BaseActivity;

/**
 * Created by Touch on 2017/9/19.
 */

public class SplashActivity extends BaseActivity{

    @Override
    protected void initBind() {
        setContentView(R.layout.activity_splash);
        super.initBind();
    }

    @Override
    protected void initUI() {
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        new Handler(new Handler.Callback() {
            //处理接收到的消息的方法
            @Override
            public boolean handleMessage(Message arg0) {
                //实现页面跳转
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return false;
            }
        }).sendEmptyMessageDelayed(0, 1000); //表示延时三秒进行任务的执行

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void fillData() {

    }
}
