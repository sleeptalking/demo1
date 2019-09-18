package com.hflh.demo.bean;

import java.util.List;

public class ShopListBean {

    //结果状态
    private boolean success;

    //店铺列表(查询店铺列表的时候用到)
    private List<Shop> shopList;

    private PersonInfo user;

    public PersonInfo getUser() {
        return user;
    }

    public void setUser(PersonInfo user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
