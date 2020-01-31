package com.vargancys.vargancysshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vargancys.vargancysshop.module.home.DefaultFragment;
import com.vargancys.vargancysshop.module.home.car.fragment.CarFragment;
import com.vargancys.vargancysshop.module.home.type.fragment.TypeFragment;
import com.vargancys.vargancysshop.module.home.find.fragment.FindFragment;
import com.vargancys.vargancysshop.module.home.home.fragment.HomeFragment;
import com.vargancys.vargancysshop.module.home.my.fragment.MyFragment;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/25
 * version:1.0
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragment;
    public HomePagerAdapter(FragmentManager fm,int size){
        super(fm);
        mFragment = new Fragment[size];
    }
    @Override
    public Fragment getItem(int position) {
        if(mFragment[position] == null){
            switch (position){
                case 0:
                    mFragment[position] = HomeFragment.newInstance();
                    break;
                case 1:
                    mFragment[position] = TypeFragment.newInstance();
                    break;
                case 2:
                    mFragment[position] = FindFragment.newInstance();
                    break;
                case 3:
                    mFragment[position] = CarFragment.newInstance();
                    break;
                case 4:
                    mFragment[position] = MyFragment.newInstance();
                    break;
                default:
                    mFragment[position] = DefaultFragment.newInstance();
                    break;
            }
        }
        return mFragment[position];
    }

    @Override
    public int getCount() {
        return mFragment.length;
    }
}
