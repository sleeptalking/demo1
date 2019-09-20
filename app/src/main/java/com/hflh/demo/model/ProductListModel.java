package com.hflh.demo.model;

import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.Result;
import com.hflh.demo.contract.ProductListContract;
import com.hflh.demo.net.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;

public class ProductListModel implements ProductListContract.Model {
    @Override
    public Observable<Result<List<Product>>> getProductList(String productStr, int pageIndex, int pageSize) {
        return RetrofitClient.getInstance().getApi().getProductList(productStr,pageIndex,pageSize);
    }
}
