package com.example.xw.firstonlineproject.user.register;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by xw on 2016/11/23.
 */

public interface RegisterView extends MvpView {
    void showWait();
    void closeWait();
    void showRegisterFail();
    void showRegisterSuccess();
    void showMsg(String msg);
    void showUserPasswordError(String msg);
}
