package com.vargancys.vargancysshop.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.adapter.HomePagerAdapter;
import com.vargancys.vargancysshop.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.main_commonTab)
    CommonTabLayout mainCommonTab;

    private String[] mTitle = {"首页","分类","发现","购物车","我的"};

    private int[] mIconUnSelectId = {
            R.drawable.main_home,R.drawable.main_class,
            R.drawable.main_find,R.drawable.main_car,
            R.drawable.main_my
    };

    private int[] mIconSelectId = {
            R.drawable.main_home_pressed,R.drawable.main_class_pressed,
            R.drawable.main_find_pressed,R.drawable.main_car_pressed,
            R.drawable.main_my_pressed
    };

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private HomePagerAdapter mHomePager;
    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initView(Bundle save) {
        initTab();
        initViewPager();
    }

    public void initViewPager(){
        mHomePager = new HomePagerAdapter(getSupportFragmentManager(),mTitle.length);

        mainViewpager.setOffscreenPageLimit(mTitle.length);
        mainViewpager.setAdapter(mHomePager);
        mainViewpager.setCurrentItem(0);
        mainViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                mainCommonTab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mainCommonTab.setTabData(mTabEntities);
        mainCommonTab.setCurrentTab(0);
        mainCommonTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


    }

    public void initTab(){
        for (int j=0;j<mTitle.length;j++){
            mTabEntities.add(new MainTabEntity(mTitle[j],mIconSelectId[j],mIconUnSelectId[j]));
        }
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public class MainTabEntity implements CustomTabEntity {
        private String title;
        private int selectedIcon;
        private int unSelectedIcon;
        private MainTabEntity(String title,int selectedIcon,int unSelectedIcon){
            this.title=title;
            this.selectedIcon=selectedIcon;
            this.unSelectedIcon=unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

}
