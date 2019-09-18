package com.hflh.demo.presenter;


import com.hflh.demo.base.BasePresenter;
import com.hflh.demo.bean.LoginBean;
import com.hflh.demo.contract.MainContract;
import com.hflh.demo.model.MainModel;
import com.hflh.demo.net.RxScheduler;

import io.reactivex.functions.Consumer;

/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MainContract.Model model;

    public MainPresenter() {
        model = (MainContract.Model) new MainModel();
    }

    @Override
    public void login(String username, String password) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        model.login(username, password)
                .compose(RxScheduler.<LoginBean>Flo_io_main())
                .as(mView.<LoginBean>bindAutoDispose())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }
}
