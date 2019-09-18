package com.hflh.demo.model;

import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.contract.AddProductContract;
import com.hflh.demo.net.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddProductModel implements AddProductContract.Model {
    @Override
    public Observable<String> addProduct(String header, RequestBody shopId,RequestBody productStr, RequestBody verifyCodeActual, MultipartBody.Part thumbnail, List<MultipartBody.Part> list) {
        return RetrofitClient.getInstance().getApi().addProduct(header,shopId,productStr,thumbnail,verifyCodeActual,list);
    }

    @Override
    public Observable<ProductCategoryListBean> getProductCategoryList(long shopId) {
        return RetrofitClient.getInstance().getApi().getProductCategoryList(shopId);
    }

    @Override
    public Observable<CodeImageBean> getCodeImage() {
        return RetrofitClient.getInstance().getApi().getCodeImage();
    }

}
