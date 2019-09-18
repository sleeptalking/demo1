package com.hflh.demo.bean;

import java.util.List;

public class ShopInfoBean {

    private Boolean successs;
    private List<ShopCategory> shopCategoryList;
    private List<Area> areaList;

    public Boolean getSuccesss() {
        return successs;
    }

    public void setSuccesss(Boolean successs) {
        this.successs = successs;
    }

    public List<ShopCategory> getShopCategoryList() {
        return shopCategoryList;
    }

    public void setShopCategoryList(List<ShopCategory> shopCategoryList) {
        this.shopCategoryList = shopCategoryList;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
}
