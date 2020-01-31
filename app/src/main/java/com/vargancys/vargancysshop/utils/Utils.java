package com.vargancys.vargancysshop.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/24
 * version:1.0
 */
public class Utils {
    public static String getVersion(Context context){
        PackageManager pm=context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
