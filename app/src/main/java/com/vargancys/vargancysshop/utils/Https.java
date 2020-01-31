package com.vargancys.vargancysshop.utils;

import com.vargancys.vargancysshop.module.home.car.data.CarService;
import com.vargancys.vargancysshop.module.home.home.data.GoodsService;
import com.vargancys.vargancysshop.module.home.home.data.HomeService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/27
 * version:1.0
 */
public class Https {

    public static HomeService getHomeAPI() {
        return createAPI(HomeService.class,Constants.HOME_URL);
    }

    public static GoodsService getGoodsAPI() {
        return createAPI(GoodsService.class,Constants.HOME_URL);
    }

    public static CarService getCarAPI() {
        return createAPI(CarService.class,Constants.HOME_URL);
    }

    private static <T> T createAPI (Class<T> clazz,String baseUrl){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}

