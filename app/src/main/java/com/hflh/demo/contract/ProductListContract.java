package com.hflh.demo.contract;

import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.Result;

import java.util.List;

import io.reactivex.Observable;

public interface ProductListContract {

    interface Model {
        Observable<Result<List<Product>>> getProductList(String productStr, int pageIndex, int pageSize);
    }

    interface View extends BaseView {
        void getProductListSuccess(Result<List<Product>> productExecution);
        void getProductListFailure(Result<List<Product>> productExecution);
    }

    interface Presenter {
        void getProductList(Product product,int pageIndex,int pageSize);

    }
}
