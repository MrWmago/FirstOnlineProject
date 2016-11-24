package com.example.xw.firstonlineproject.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.xw.firstonlineproject.R;
import com.example.xw.firstonlineproject.commons.ActivityUtils;
import com.example.xw.firstonlineproject.commons.CachePreferences;
import com.example.xw.firstonlineproject.main.me.MeFragment;
import com.example.xw.firstonlineproject.main.shop.ShopFragment;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private Unbinder unbinder;//解绑
    @BindViews({R.id.tv_shop, R.id.tv_message, R.id.tv_mail_list, R.id.tv_me})
    TextView[] menu;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    //activity工具 可快捷使用activity的部分功能
    private ActivityUtils activityUtils;

    //点击两次返回，退出程序
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定当前activity
        unbinder = ButterKnife.bind(this);//黄油刀
        activityUtils = new ActivityUtils(this);//辅助工具类

        //设置一下actionbar
        setSupportActionBar(toolbar);
        //设置一下ActionBar标题为空，否则默认显示应用名
        getSupportActionBar().setTitle("");

        init();
    }

    //进入页面数据初始化，默认显示为市场页面
    private void init() {
        //刚进来默认选择市场
        menu[0].setSelected(true);//将第一个textView设置为选中状态，从而有选中状态的效果
        // 判断用户是否登陆，从而选择不同的适配器
        if(CachePreferences.getUser().getName()==null)
            viewpager.setAdapter(unLoginAdapter);
        else
            viewpager.setAdapter(loginAdapter);

        viewpager.setCurrentItem(0);//默认选择第一个页面

        //viewpager添加滑动事件
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //textView选择处理
                for(TextView textView : menu){//先全部给false了  初始化
                    textView.setSelected(false);
                }
                toolbarTitle.setText(menu[position].getText());
                menu[position].setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private FragmentStatePagerAdapter unLoginAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ShopFragment();
                case 1:
                    return new UnloginFragment();
                case 2:
                    return new UnloginFragment();
                case 3:
                    return new MeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return menu.length;
        }
    };

    private FragmentStatePagerAdapter loginAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ShopFragment();
                case 1:
                    // TODO: 2016/11/23 环信消息fragment
                    return new UnloginFragment();
                case 2:
                    // TODO: 2016/11/23 环信的通讯录fragment
                    return new UnloginFragment();
                case 3:
                    return new MeFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return menu.length;
        }
    };

    @OnClick({R.id.tv_shop,R.id.tv_message,R.id.tv_mail_list,R.id.tv_me})
    public void onClick(TextView view){
        for (int i = 0;i<menu.length;i++){//初始化
            menu[i].setSelected(false);
            menu[i].setTag(i);//给view设置一个tag代表id，以后根据view就可以找到id
        }
        //设置选择效果
        view.setSelected(true);
        //参数false代表瞬间切换，而不是平滑过度
        viewpager.setCurrentItem((Integer) view.getTag(),false);
        //设置一下title
        toolbarTitle.setText(menu[(Integer) view.getTag()].getText());
    }

    //点击两次返回，退出程序
    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            activityUtils.showToast("再按一次退出程序");
            //两秒内再次点击返回退出
            viewpager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(CachePreferences.getUser().getName() != null) viewpager.setCurrentItem(0);
        else {
            viewpager.setAdapter(unLoginAdapter);//选择未登录适配器
            viewpager.setCurrentItem(3);//跳转到我的页面
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();//黄油刀解绑
    }
}
