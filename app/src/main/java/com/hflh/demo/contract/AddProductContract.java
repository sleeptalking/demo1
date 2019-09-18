package com.hflh.demo.contract;

import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.ProductCategoryListBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface AddProductContract {
    interface Model {
        Observable<ProductCategoryListBean> getProductCategoryList(long shopId);
        Observable<CodeImageBean> getCodeImage();
        Observable<String> addProduct(String header, RequestBody shopId,RequestBody productStr, RequestBody verifyCodeActual, MultipartBody.Part thumbnail, List<MultipartBody.Part> list);
    }

    interface View extends BaseView {
        void addProduct(String str);
        void getCodeImage(CodeImageBean bean);
        void getProductCategoryList(ProductCategoryListBean bean);
    }

    interface Presenter {
        void addProduct(String header, String shopId,String productStr, String verifyCodeActual, String path, List<String> images);
        void getProductCategoryList(long shopId);
        void getCodeImage();
    }
}
