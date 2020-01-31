package com.vargancys.vargancysshop.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.trello.rxlifecycle.components.RxActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/24
 * version:1.0
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    public Unbinder bind;
    public abstract int getLayoutID();
    public abstract void initToolBar();
    public abstract void initView(Bundle save);
    public Context getContext(){
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        bind = ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
