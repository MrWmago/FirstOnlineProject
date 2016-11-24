package com.example.xw.firstonlineproject.user.register;

import com.example.xw.firstonlineproject.commons.CachePreferences;
import com.example.xw.firstonlineproject.model.User;
import com.example.xw.firstonlineproject.model.UserResult;
import com.example.xw.firstonlineproject.network.MyClient;
import com.example.xw.firstonlineproject.network.UICallBack;
import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by xw on 2016/11/23.
 */

public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView> {
    private Call call;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call != null) call.cancel();
    }

    public void register(String username,String password){
        getView().showWait();//显示加载动画
        call = MyClient.getMyClient().register(username,password);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().closeWait();//隐藏加载动画
                getView().showMsg(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                //拿到返回的结果
                UserResult userResult = new Gson().fromJson(body,UserResult.class);
                //根据结果码处理不同情况
                if(userResult.getCode() == 1){
                    getView().showMsg("注册成功");
                    //拿到用户的实体类
                    User user = userResult.getData();
                    //将用户信息保存到本地配置里
                    CachePreferences.setUser(user);

                    getView().showRegisterSuccess();

                    // TODO: 2016/11/23 还需要登录环信，待实现
                }else if(userResult.getCode()==2){
                    getView().closeWait();//隐藏加载动画
                    getView().showMsg(userResult.getMessage());//提示错误信息
                    getView().showRegisterFail();
                }else {
                    getView().closeWait();//隐藏加载动画
                    getView().showMsg("未知错误！");//提示错误信息
                }
            }
        });
    }
}
