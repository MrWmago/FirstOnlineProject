package com.example.xw.firstonlineproject.network;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by xw on 2016/11/22.
 */


public class MyClient {
    private static MyClient myClient;
    private OkHttpClient okHttpClient;

    private MyClient(){
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)//添加日志拦截器
                .build();
    }

    public static MyClient getMyClient(){
        if(myClient == null)myClient = new MyClient();
        return myClient;
    }

    /**
     * 注册
     * <p>
     *     post
     * </p>
     * @param username 用户名
     * @param password 密码
     * */
    public Call register(String username,String password){
        //表单形式构建请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        //构建请求
        Request request = new Request.Builder()
                .url(MyApi.BASE_URL+MyApi.REGISTER)
                .post(requestBody)//ctrl+p查看参数
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 登录
     * <p>
     * post
     *
     * @param username 用户名
     * @param password 密码
     */
    public Call login(String username,String password){
        //表单形式构建请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        //构建请求
        Request request = new Request.Builder()
                .url(MyApi.BASE_URL+MyApi.LOGIN)
                .post(requestBody)//ctrl+p查看参数
                .build();
        return okHttpClient.newCall(request);
    }
}
