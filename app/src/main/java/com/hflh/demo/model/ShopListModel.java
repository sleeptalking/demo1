package com.hflh.demo.model;

import com.hflh.demo.bean.ShopListBean;
import com.hflh.demo.contract.ShopListContract;
import com.hflh.demo.net.RetrofitClient;

import io.reactivex.Observable;

public class ShopListModel implements ShopListContract.Model {
    @Override
    public Observable<ShopListBean> getShopList() {
        return RetrofitClient.getInstance().getApi().getShopList();
    }
}
