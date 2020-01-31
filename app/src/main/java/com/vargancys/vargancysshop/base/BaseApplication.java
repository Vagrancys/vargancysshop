package com.vargancys.vargancysshop.base;

import android.app.Application;
import android.os.Handler;


/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/24
 * version:1.0
 */
public class BaseApplication extends Application {
    public Handler mHandler;
    public Handler getHandler(){
        if(mHandler !=null){
            mHandler=new Handler();
        }
        return mHandler;
    }
}
