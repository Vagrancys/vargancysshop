package com.vargancys.vargancysshop.module.home.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vargancys.vargancysshop.module.home.home.data.BannerInfo;
import com.youth.banner.loader.ImageLoader;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/28
 * version:1.0
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(((BannerInfo) path).getBanner_url()).into(imageView);
    }
}
