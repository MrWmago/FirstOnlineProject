package com.example.xw.firstonlineproject.user.login;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by xw on 2016/11/23.
 */

public interface LoginView extends MvpView {
    void showPrb();
    void hidePrb();
    void loginFailed();
    void loginSuccess();
    void showMsg(String msg);
}
