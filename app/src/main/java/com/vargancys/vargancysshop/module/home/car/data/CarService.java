package com.vargancys.vargancysshop.module.home.car.data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public interface CarService {
    @GET("car.html")
    Call<CarBaseInfo> getCarBaseInfo(String name);
}
