package com.vargancys.vargancysshop.module.home;

import android.os.Bundle;

import com.vargancys.vargancysshop.base.BaseFragment;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/25
 * version:1.0
 */
public class DefaultFragment extends BaseFragment {
    public static DefaultFragment newInstance(){
        return new DefaultFragment();
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void finishCreateView(Bundle save) {

    }
}
