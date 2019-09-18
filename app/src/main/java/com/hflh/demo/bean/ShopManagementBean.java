package com.hflh.demo.bean;

import java.util.List;

public class ShopManagementBean {

    private Boolean successs;
    private Shop shop;
    private List<Area> areaList;

    public Boolean getSuccesss() {
        return successs;
    }

    public void setSuccesss(Boolean successs) {
        this.successs = successs;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
}
