package com.vargancys.vargancysshop.module.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.vargancys.vargancysshop.R;
import com.vargancys.vargancysshop.base.BaseActivity;
import com.vargancys.vargancysshop.utils.Constants;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/24
 * version:1.0
 */
public class WebActivity extends BaseActivity {
    @Override
    public int getLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    public void initView(Bundle save) {

    }

    @Override
    public void initToolBar() {

    }

    public static void launch(Activity activity, String url){
        Intent intent=new Intent(activity,WebActivity.class);
        intent.putExtra(Constants.WEB_URI,url);
        activity.startActivity(intent);
    }
}
