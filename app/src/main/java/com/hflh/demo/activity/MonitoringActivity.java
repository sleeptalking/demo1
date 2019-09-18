package com.hflh.demo.activity;


import com.hflh.demo.R;
import com.hflh.demo.base.BaseMvpActivity;
import com.hflh.demo.bean.YuQingGaoWeiBean;
import com.hflh.demo.contract.MonitoringContract;
import com.hflh.demo.presenter.MonitoringPresenter;

public class MonitoringActivity extends BaseMvpActivity<MonitoringPresenter> implements MonitoringContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.activity_monitoring;
    }

    @Override
    public void initView() {
        mPresenter = new MonitoringPresenter();
        mPresenter.attachView(this);
        mPresenter.getData("3","1","");

    }


    @Override
    public void onSuccess(YuQingGaoWeiBean bean) {

        //Log.i("TAG","onSuccess"+bean.toString());

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
