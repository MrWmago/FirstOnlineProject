package com.example.xw.firstonlineproject.main.me.userinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xw.firstonlineproject.R;
import com.example.xw.firstonlineproject.commons.ActivityUtils;
import com.example.xw.firstonlineproject.commons.CachePreferences;
import com.example.xw.firstonlineproject.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by xw on 2016/11/24.
 */

public class UserInfoActivity extends AppCompatActivity {

    @BindView(R.id.iv_user_info_headimage)
    CircleImageView ivUserInfoHeadimage;
    @BindView(R.id.tv_user_info_username)
    TextView tvUserInfoUsername;
    @BindView(R.id.tv_user_info_nickname)
    TextView tvUserInfoNickname;
    @BindView(R.id.tv_user_info_hxID)
    TextView tvUserInfoHxID;
    @BindView(R.id.user_info_toolbar)
    Toolbar userInfoToolbar;
    @BindView(R.id.lly_user_info_username)
    LinearLayout llyUserInfoUsername;
    @BindView(R.id.lly_user_info_nickname)
    LinearLayout llyUserInfoNickname;
    @BindView(R.id.lly_user_info_hxID)
    LinearLayout llyUserInfoHxID;

    private Unbinder unbinder;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        unbinder = ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        init();
    }

    private void init() {
        tvUserInfoUsername.setText(CachePreferences.getUser().getName());
        tvUserInfoHxID.setText(CachePreferences.getUser().getHx_ID());
        if (CachePreferences.getUser().getNick_name() != null)
            tvUserInfoNickname.setText(CachePreferences.getUser().getNick_name());
        else tvUserInfoNickname.setText("请输入昵称");

        setSupportActionBar(userInfoToolbar);
        //给左上角加一个返回图标,需要重写菜单点击事件，否则点击无效
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //给左上角加一个返回图标,需要重写菜单点击事件，否则点击无效
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user_info_headimage, R.id.lly_user_info_username, R.id.lly_user_info_nickname, R.id.lly_user_info_hxID, R.id.btn_user_info_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_info_headimage:
                break;
            case R.id.lly_user_info_username:
                activityUtils.showToast("不能更改用户名");
                break;
            case R.id.lly_user_info_nickname:
                activityUtils.startActivity(NickNameActivity.class);
                break;
            case R.id.lly_user_info_hxID:
                activityUtils.showToast("不能更改环信ID");
                break;
            case R.id.btn_user_info_exit:
                CachePreferences.clearAllData();
                finish();
                break;
        }
    }
}
