package com.hflh.demo.contract;

import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface AddProductContract {
    interface Model {
        Observable<ProductCategoryListBean> getProductCategoryList(long shopId);
        Observable<CodeImageBean> getCodeImage();
        Observable<String> addProduct(String header, RequestBody shopId,RequestBody productStr, RequestBody verifyCodeActual, MultipartBody.Part thumbnail, List<MultipartBody.Part> list);
        Observable<Result<Product>> modifyProduct(String header, RequestBody shopId,RequestBody productStr, RequestBody verifyCodeActual, MultipartBody.Part thumbnail, List<MultipartBody.Part> list);
        Observable<Result<Product>> getProductById(Long productId);
    }

    interface View extends BaseView {
        void addProduct(String str);
        void modifyProduct(Result<Product> productResult);
        void getCodeImage(CodeImageBean bean);
        void getProductCategoryList(ProductCategoryListBean bean);
        void getProductById(Result<Product> productResult);
    }

    interface Presenter {
        void addProduct(String header, String shopId,String productStr, String verifyCodeActual, String path, List<String> images);
        void modifyProduct(String header,Product product, String verifyCodeActual, String path, List<String> images);
        void getProductCategoryList(long shopId);
        void getCodeImage();
        void getProductById(Long productId);
    }
}
