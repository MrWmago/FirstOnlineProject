package com.example.xw.firstonlineproject.main.me;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xw.firstonlineproject.R;
import com.example.xw.firstonlineproject.commons.ActivityUtils;
import com.example.xw.firstonlineproject.user.LoginActivity;
import com.example.xw.firstonlineproject.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xw on 2016/11/21.
 */


public class MeFragment extends Fragment {
    @BindView(R.id.civ_user_head)
    CircleImageView iv_user_head;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;
    @BindView(R.id.tv_user_goods)
    TextView tvUserGoods;
    @BindView(R.id.tv_user_upgoods)
    TextView tvUserUpgoods;
    private View view;
    private ActivityUtils activityUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_me, container, false);
            ButterKnife.bind(this, view);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // TODO: 2016/11/21 判断用户是否登陆，更改用户头像并且显示用户名
    }


    @OnClick({R.id.tv_login, R.id.tv_user_info, R.id.tv_user_goods, R.id.tv_user_upgoods})
    public void onClick(View view) {
        activityUtils.startActivity(LoginActivity.class);
    }
}
