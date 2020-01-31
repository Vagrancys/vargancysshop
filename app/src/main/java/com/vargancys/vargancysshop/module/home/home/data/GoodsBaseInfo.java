package com.vargancys.vargancysshop.module.home.home.data;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public class GoodsBaseInfo {
    private int state;
    private GoodsItem goodsItem;

    public GoodsItem getGoodsItem() {
        return goodsItem;
    }

    public void setGoodsItem(GoodsItem goodsItem) {
        this.goodsItem = goodsItem;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
