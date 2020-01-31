package com.vargancys.vargancysshop.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/24
 * version:1.0
 */
public class Resources {
    public static Drawable getDrawable(Context context, int id){
        return context.getResources().getDrawable(id);
    }

    public static String getString(Context context, int id){
        return context.getResources().getString(id);
    }
}
