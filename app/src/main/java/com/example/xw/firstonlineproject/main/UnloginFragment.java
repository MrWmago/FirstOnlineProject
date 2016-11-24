package com.example.xw.firstonlineproject.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xw.firstonlineproject.R;
import com.example.xw.firstonlineproject.commons.CachePreferences;

/**
 * Created by xw on 2016/11/21.
 */

public class UnloginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //判断用户是否登录
        if(CachePreferences.getUser().getName() != null)
            return inflater.inflate(R.layout.fragment_login,container,false);
        return inflater.inflate(R.layout.fragment_un_login,container,false);
    }
}
