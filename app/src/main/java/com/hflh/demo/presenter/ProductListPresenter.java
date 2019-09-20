package com.hflh.demo.presenter;

import android.util.Log;

import com.hflh.demo.base.BasePresenter;
import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.Result;
import com.hflh.demo.contract.ProductListContract;
import com.hflh.demo.model.ProductListModel;
import com.hflh.demo.net.RxScheduler;
import com.hflh.demo.util.JsonUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductListPresenter extends BasePresenter<ProductListContract.View>implements ProductListContract.Presenter {

    private ProductListContract.Model model;
    public ProductListPresenter() {
        model = new ProductListModel();
    }

    @Override
    public void getProductList(Product product, int pageIndex, int pageSize) {

        String str = JsonUtils.serialize(product);

        model.getProductList(str,pageIndex,pageSize).compose(RxScheduler.<Result<List<Product>>>Obs_io_main()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Result<List<Product>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<List<Product>> productExecution) {
                Log.i("TAG", "onNext: "+productExecution.getMsg());
                if(productExecution.isSuccess()){
                    mView.getProductListSuccess(productExecution);
                }else{
                    mView.getProductListFailure(productExecution);
                }
            }

            @Override
            public void onError(Throwable e) {

                Log.i("TAG", "onError: "+e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
