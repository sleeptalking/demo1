package com.hflh.demo.model;

import com.hflh.demo.bean.YuQingGaoWeiBean;
import com.hflh.demo.contract.MonitoringContract;
import com.hflh.demo.net.RetrofitClient;

import io.reactivex.Observable;

public class MonitoringModel implements MonitoringContract.Model {
    @Override
    public Observable<YuQingGaoWeiBean> getData(String pageSize, String pageNo, String endTime, String startTime, String type, String title, String areaId) {
        return RetrofitClient.getInstance().getApi().getData(pageSize,pageNo,endTime,startTime,type,title,areaId);
    }

}
