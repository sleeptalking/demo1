package com.hflh.demo.presenter;

import android.util.Log;

import com.hflh.demo.base.BasePresenter;
import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.ShopInfoBean;
import com.hflh.demo.bean.ShopManagementBean;
import com.hflh.demo.contract.AddShopContract;
import com.hflh.demo.model.AddShopModel;
import com.hflh.demo.net.RxScheduler;
import com.hflh.demo.util.LogUtils;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddShopPresenter extends BasePresenter<AddShopContract.View> implements AddShopContract.Presenter {

    private AddShopContract.Model model;

    public AddShopPresenter() {
        model = new AddShopModel();
    }

    @Override
    public void getShopInfo() {
        model.getShopInfo().compose(RxScheduler.<ShopInfoBean>Obs_io_main()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopInfoBean shopInfoBean) {
                        mView.onSuccess(shopInfoBean);
                        mView.hideLoading();
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
    public void getCodeImage() {

        model.getCodeImage().compose(RxScheduler.<CodeImageBean>Obs_io_main()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CodeImageBean codeImageBean) {
                        mView.getCodeImage(codeImageBean);
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
    public void getShopById(Long shopId) {

        model.getShopById(shopId).compose(RxScheduler.<ShopManagementBean>Obs_io_main()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopManagementBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopManagementBean shopInfoBean) {
                        LogUtils.i("onNext");
                        mView.getShopById(shopInfoBean);
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
    public void addShop(String header,String shopStr, String vCode, File file) {

        LogUtils.i("file exists :"+file.exists());

        RequestBody b1 = RequestBody.create(MediaType.parse("multipart/form-data"), shopStr);
        RequestBody b2 = RequestBody.create(MediaType.parse("multipart/form-data"), vCode);
        RequestBody b3 = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part shopImg = MultipartBody.Part.createFormData("shopImg", file.getName(), b3);

        model.addShop(header,b1,b2,shopImg).compose(RxScheduler.<Object>Obs_io_main()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("TAG", "onSubscribe: ");

                    }

                    @Override
                    public void onNext(Object str) {
                        Log.i("TAG", "onNext: "+str);
                        mView.addShopResult(str);
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
