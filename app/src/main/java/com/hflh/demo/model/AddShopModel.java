package com.hflh.demo.model;

import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.ShopInfoBean;
import com.hflh.demo.bean.ShopManagementBean;
import com.hflh.demo.contract.AddShopContract;
import com.hflh.demo.net.RetrofitClient;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddShopModel implements AddShopContract.Model {
    @Override
    public Observable<ShopInfoBean> getShopInfo() {
        return RetrofitClient.getInstance().getApi().getShopInfo();
    }

    @Override
    public Observable<CodeImageBean> getCodeImage() {
        return RetrofitClient.getInstance().getApi().getCodeImage();
    }

    @Override
    public Observable<ShopManagementBean> getShopById(Long shopId) {
        return RetrofitClient.getInstance().getApi().getShopById(shopId);

    }

    @Override
    public Observable<Object> addShop(String header, RequestBody shopStr, RequestBody verifyCodeActual, MultipartBody.Part shopImg) {
        return RetrofitClient.getInstance().getApi().addShop(header,shopStr,verifyCodeActual,shopImg);
    }
}
