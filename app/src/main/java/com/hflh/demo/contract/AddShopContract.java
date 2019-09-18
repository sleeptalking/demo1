package com.hflh.demo.contract;

import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.ShopInfoBean;
import com.hflh.demo.bean.ShopManagementBean;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface AddShopContract {
    interface Model {
        Observable<ShopInfoBean> getShopInfo();
        Observable<CodeImageBean> getCodeImage();
        Observable<ShopManagementBean> getShopById(Long shopId);
        Observable<Object> addShop(String header, RequestBody shopStr, RequestBody verifyCodeActual, MultipartBody.Part shopImg);

    }

    interface View extends BaseView {
        void onSuccess(ShopInfoBean bean);
        void addShopResult(Object str);
        void getShopById(ShopManagementBean bean);
        void getCodeImage(CodeImageBean bean);
    }

    interface Presenter {
        void getShopInfo();
        void getCodeImage();
        void getShopById(Long shopId);
        void addShop(String header,String shopStr, String vCode, File file);
    }
}
