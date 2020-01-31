package com.vargancys.vargancysshop.module.home.home.data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public interface GoodsService {
    @GET("goods.html")
    Call<GoodsBaseInfo> getGoodsBaseInfo(int goods_id);
}
