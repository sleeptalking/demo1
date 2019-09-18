package com.hflh.demo.contract;

import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.YuQingGaoWeiBean;

import io.reactivex.Observable;

public interface MonitoringContract {

    interface Model {
        Observable<YuQingGaoWeiBean> getData(String pageSize, String pageNo, String endTime, String startTime, String type, String title, String areaId);
    }

    interface View extends BaseView {
        void onSuccess(YuQingGaoWeiBean bean);
    }

    interface Presenter {
       void getData(String pageSize, String pageNo,String title);
    }
}
