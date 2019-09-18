package com.hflh.demo.model;


import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.contract.ProductCategoryManagementContract;
import com.hflh.demo.net.RetrofitClient;

import io.reactivex.Observable;

public class ProductCategoryManagementModel implements ProductCategoryManagementContract.Model {
    @Override
    public Observable<String> addProductCategory(String productCategory, long shopId) {
        return RetrofitClient.getInstance().getApi().addProductCategory(productCategory,shopId);
    }

    @Override
    public Observable<String> removeProductCategory(long productCategoryId, long shopId) {
        return RetrofitClient.getInstance().getApi().removeProductCategory(productCategoryId,shopId);
    }

    @Override
    public Observable<ProductCategoryListBean> getProductCategoryList(long shopId) {
        return RetrofitClient.getInstance().getApi().getProductCategoryList(shopId);
    }
}
