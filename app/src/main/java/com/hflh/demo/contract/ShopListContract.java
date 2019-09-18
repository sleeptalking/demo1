package com.hflh.demo.contract;

import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.ShopListBean;

import io.reactivex.Observable;


public interface ShopListContract {
    interface Model {
        Observable<ShopListBean> getShopList();
    }

    interface View extends BaseView {
        void onSuccess(ShopListBean bean);
    }

    interface Presenter {
        void getShopList();
    }
}
