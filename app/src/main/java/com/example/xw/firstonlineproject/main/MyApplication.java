package com.example.xw.firstonlineproject.main;

import android.app.Application;

import com.example.xw.firstonlineproject.commons.CachePreferences;

/**
 * Created by xw on 2016/11/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化本地配置
        CachePreferences.init(this);
    }
}
