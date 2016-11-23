package com.example.xw.firstonlineproject.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.xw.firstonlineproject.R;
import com.example.xw.firstonlineproject.commons.ActivityUtils;
import com.example.xw.firstonlineproject.commons.RegexUtils;
import com.example.xw.firstonlineproject.components.AlertDialogFragment;
import com.example.xw.firstonlineproject.commons.CachePreferences;
import com.example.xw.firstonlineproject.model.User;
import com.example.xw.firstonlineproject.model.UserResult;
import com.example.xw.firstonlineproject.network.MyClient;
import com.example.xw.firstonlineproject.network.UICallBack;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by xw on 2016/11/21.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.et_register_username)
    EditText etRegisterUsername;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.et_register_pwdAgain)
    EditText etRegisterPwdAgain;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.register_toolbar)
    Toolbar registerToolbar;

    private String username;
    private String password;
    private String pwd_again;
    private ActivityUtils activityUtils;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register);
        unbinder = ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        init();
    }

    private void init() {
        etRegisterUsername.addTextChangedListener(textWatcher);
        etRegisterPwd.addTextChangedListener(textWatcher);
        etRegisterPwdAgain.addTextChangedListener(textWatcher);
        setSupportActionBar(registerToolbar);
        //给左上角加一个返回图标，需要重写菜单点击事件，否则点击无效
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //给左上角加一个返回图标，需要重写菜单点击事件，否则点击无效
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            username = etRegisterUsername.getText().toString();
            password = etRegisterPwd.getText().toString();
            pwd_again = etRegisterPwdAgain.getText().toString();
            //判断如果内容有一个为空，则按钮设置为不能点击
            boolean canLogin = !(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(pwd_again));
            btnRegister.setEnabled(canLogin);
        }
    };

    @OnClick(R.id.btn_register)
    public void onClick() {
        if(RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS){
            String msg = getString(R.string.username_rules);
            showUserPasswordError(msg);
            return;
        }else if(RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS){
            String msg = getString(R.string.password_rules);
            showUserPasswordError(msg);
            return;
        }else if(!TextUtils.equals(password,pwd_again)){
            String msg = getString(R.string.password_different_again);
            showUserPasswordError(msg);
            return;
        }

        Call call = MyClient.getMyClient().register(username,password);

        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                activityUtils.showToast(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String body) {
                UserResult userResult = new Gson().fromJson(body,UserResult.class);
                //根据结果码处理不同情况
                if(userResult.getCode() == 1){
                    activityUtils.showToast("注册成功");
                    //拿到用户的实体类
                    User user = userResult.getData();
                    //将用户信息保存到本地配置里
                    CachePreferences.setUser(user);

                    // TODO: 2016/11/23  页面跳转实现，使用eventbus
                    // TODO: 2016/11/23 还需要登录环信，待实现
                }else if(userResult.getCode()==2){
                    activityUtils.showToast(userResult.getMessage());
                }else {
                    activityUtils.showToast("未知错误！");
                }
            }
        });
    }

    //显示错误提示
    public void showUserPasswordError(String msg) {
        AlertDialogFragment fragment = AlertDialogFragment.newInstance(msg);
        fragment.show(getSupportFragmentManager(), getString(R.string.username_pwd_rule));
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
