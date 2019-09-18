package com.hflh.demo.contract;


import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.ProductCategoryListBean;

import io.reactivex.Observable;


public interface ProductCategoryManagementContract {
    interface Model {
        Observable<String> addProductCategory(String productCategory, long shopId);
        Observable<String> removeProductCategory(long productCategoryId, long shopId);
        Observable<ProductCategoryListBean> getProductCategoryList(long shopId);

    }

    interface View extends BaseView {
        void addProductCategory(String str);
        void removeProductCategory(String str);
        void getProductCategoryList(ProductCategoryListBean list);
    }

    interface Presenter {
        void addProductCategory(String productCategory,long shopId);
        void removeProductCategory(long productCategoryId,long shopId);
        void getProductCategoryList(long shopId);


    }
}
