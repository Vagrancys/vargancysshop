package com.vargancys.vargancysshop.module.home.car.data;

/**
 * author: Vagrancy
 * e-mail: 18050829067@163.com
 * time  : 2020/01/29
 * version:1.0
 */
public class ShoppingCarInfo {
    private String title;
    private String url;
    private String money;
    private int number;

    private boolean isSelected = true;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
