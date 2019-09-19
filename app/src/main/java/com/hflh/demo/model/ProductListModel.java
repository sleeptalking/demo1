package com.hflh.demo.model;

import com.hflh.demo.bean.ProductExecution;
import com.hflh.demo.contract.ProductListContract;
import com.hflh.demo.net.RetrofitClient;

import io.reactivex.Observable;

public class ProductListModel implements ProductListContract.Model {
    @Override
    public Observable<ProductExecution> getProductList(String productStr, int pageIndex, int pageSize) {
        return RetrofitClient.getInstance().getApi().getProductList(productStr,pageIndex,pageSize);
    }
}
