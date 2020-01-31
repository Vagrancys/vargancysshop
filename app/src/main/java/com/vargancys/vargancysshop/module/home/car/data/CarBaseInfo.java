package com.vargancys.vargancysshop.module.home.car.data;

import java.util.List;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public class CarBaseInfo {
    private int state;
    private List<ShoppingCarInfo> shoppingCarInfos;

    public List<ShoppingCarInfo> getShoppingCarInfos() {
        return shoppingCarInfos;
    }

    public void setShoppingCarInfos(List<ShoppingCarInfo> shoppingCarInfos) {
        this.shoppingCarInfos = shoppingCarInfos;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
