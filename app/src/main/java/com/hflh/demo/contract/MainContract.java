package com.hflh.demo.contract;


import com.hflh.demo.base.BaseView;
import com.hflh.demo.bean.LoginBean;

import io.reactivex.Flowable;


/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public interface MainContract {
    interface Model {
        Flowable<LoginBean> login(String username, String password);
    }

    interface View extends BaseView {
        void onSuccess(LoginBean bean);
    }

    interface Presenter {
        /**
         * 登陆
         *
         * @param username
         * @param password
         */
        void login(String username, String password);
    }
}
