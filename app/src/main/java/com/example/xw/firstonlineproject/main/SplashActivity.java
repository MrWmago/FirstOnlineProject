package com.example.xw.firstonlineproject.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xw.firstonlineproject.R;
import com.example.xw.firstonlineproject.commons.ActivityUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xw on 2016/11/21.
 */

public class SplashActivity extends AppCompatActivity {
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activityUtils = new ActivityUtils(this);
        // TODO: 2016/11/21 登录账号冲突
        // TODO: 2016/11/21 判断用户是否登录

        Timer timer = new Timer();
        //1.5秒后跳转到主页面并且finish
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activityUtils.startActivity(MainActivity.class);
                finish();
            }
        },1500);
    }
}
