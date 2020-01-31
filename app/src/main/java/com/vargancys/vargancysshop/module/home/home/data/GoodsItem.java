package com.vargancys.vargancysshop.module.home.home.data;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public class GoodsItem {
    private String goods_name;
    private String goods_url;
    private String goods_summary;
    private String goods_money;

    public String getGoods_money() {
        return goods_money;
    }

    public void setGoods_money(String goods_money) {
        this.goods_money = goods_money;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_summary() {
        return goods_summary;
    }

    public void setGoods_summary(String goods_summary) {
        this.goods_summary = goods_summary;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }
}
