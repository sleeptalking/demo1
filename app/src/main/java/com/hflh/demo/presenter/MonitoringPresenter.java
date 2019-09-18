package com.hflh.demo.presenter;

import com.hflh.demo.base.BasePresenter;
import com.hflh.demo.bean.YuQingGaoWeiBean;
import com.hflh.demo.contract.MonitoringContract;
import com.hflh.demo.model.MonitoringModel;
import com.hflh.demo.net.RxScheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MonitoringPresenter extends BasePresenter<MonitoringContract.View> implements MonitoringContract.Presenter {

    private MonitoringContract.Model model;

    public MonitoringPresenter() {
        model = new MonitoringModel();
    }

    @Override
    public void getData(String pageSize, String pageNo, String title) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = formatter.format(new Date());
        long l = System.currentTimeMillis() - 604800000l;
        String startTime = formatter.format(new Date(l));


        model.getData(pageSize, pageNo, endTime, startTime, "0", title, "9c8681e730d13cab0130d14a81760004")
                .compose(RxScheduler.<YuQingGaoWeiBean>Obs_io_main())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YuQingGaoWeiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YuQingGaoWeiBean bean) {
                        mView.onSuccess(bean);
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
}
