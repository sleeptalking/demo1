package com.hflh.demo.presenter;

import com.hflh.demo.base.BasePresenter;
import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.contract.ProductCategoryManagementContract;
import com.hflh.demo.model.ProductCategoryManagementModel;
import com.hflh.demo.net.RxScheduler;
import com.hflh.demo.util.LogUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductCategoryManagementPresenter extends BasePresenter<ProductCategoryManagementContract.View> implements ProductCategoryManagementContract.Presenter {

    private ProductCategoryManagementContract.Model model;
    public ProductCategoryManagementPresenter() {
        model = new ProductCategoryManagementModel();

    }

    @Override
    public void addProductCategory(String productCategory, long shopId) {
        model.addProductCategory(productCategory,shopId).compose(RxScheduler.<String>Obs_io_main()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mView.addProductCategory(s);
            }

            @Override
            public void onError(Throwable e) {

                LogUtils.i("onError"+e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void removeProductCategory(long productCategoryId, long shopId) {
        model.removeProductCategory(productCategoryId,shopId).compose(RxScheduler.<String>Obs_io_main()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mView.removeProductCategory(s);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    public void getProductCategoryList(long shopId) {
        model.getProductCategoryList(shopId).compose(RxScheduler.<ProductCategoryListBean>Obs_io_main()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductCategoryListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductCategoryListBean productCategories) {
                        mView.getProductCategoryList(productCategories);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.i("onError : "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
