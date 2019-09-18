package com.hflh.demo.presenter;

import com.hflh.demo.base.BasePresenter;
import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.contract.AddProductContract;
import com.hflh.demo.model.AddProductModel;
import com.hflh.demo.net.RxScheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddProductPresenter extends BasePresenter<AddProductContract.View> implements AddProductContract.Presenter {

    private AddProductContract.Model model;

    public AddProductPresenter() {
        model = new AddProductModel();
    }

    @Override
    public void addProduct(String header, String shopId,String productStr, String verifyCodeActual, String path, List<String> images) {
        List<MultipartBody.Part> list = new ArrayList<>();

        RequestBody product = RequestBody.create(MediaType.parse("multipart/form-data"),productStr);
        RequestBody code = RequestBody.create(MediaType.parse("multipart/form-data"),verifyCodeActual);
        RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"),shopId);
        File file = new File(path);
        RequestBody rb1 = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part thumbnail = MultipartBody.Part.createFormData("thumbnail", file.getName(), rb1);

        for(int i=0;i<images.size();i++){
            File f = new File(path);
            RequestBody imagePath = RequestBody.create(MediaType.parse("multipart/form-data"),f);
            MultipartBody.Part image = MultipartBody.Part.createFormData("productImg"+i, f.getName(), rb1);
            list.add(image);
        }

        model.addProduct(header,id,product,code,thumbnail,list).compose(RxScheduler.<String>Obs_io_main()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        mView.addProduct(s);
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
                    public void onNext(ProductCategoryListBean s) {
                        mView.getProductCategoryList(s);
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
                    public void onNext(CodeImageBean s) {
                        mView.getCodeImage(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
