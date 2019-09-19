package com.hflh.demo.contract;

import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.ProductExecution;

import io.reactivex.Observable;

public interface ProductListContract {

    interface Model {
        Observable<ProductExecution> getProductList(String productStr,int pageIndex,int pageSize);
    }

    interface View extends BaseView {
        void getProductList(ProductExecution productExecution);
    }

    interface Presenter {
        void getProductList(Product product,int pageIndex,int pageSize);

    }
}
