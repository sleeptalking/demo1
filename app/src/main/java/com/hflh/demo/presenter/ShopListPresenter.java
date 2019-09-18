package com.hflh.demo.presenter;

import android.util.Log;

import com.hflh.demo.base.BasePresenter;
import com.hflh.demo.bean.ShopListBean;
import com.hflh.demo.contract.ShopListContract;
import com.hflh.demo.model.ShopListModel;
import com.hflh.demo.net.RxScheduler;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopListPresenter extends BasePresenter<ShopListContract.View> implements ShopListContract.Presenter {

    private ShopListContract.Model shopListModel;

    public ShopListPresenter() {
        shopListModel = new ShopListModel();

    }

    @Override
    public void getShopList() {
        shopListModel.getShopList().compose(RxScheduler.<ShopListBean>Obs_io_main())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("TAG", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(ShopListBean bean) {

                        Log.i("TAG", "onNext: ");
                        mView.onSuccess(bean);
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("TAG", "onComplete: ");

                    }
                });

    }
}
