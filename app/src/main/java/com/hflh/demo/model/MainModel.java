package com.hflh.demo.model;


import com.hflh.demo.bean.LoginBean;
import com.hflh.demo.contract.MainContract;
import com.hflh.demo.net.RetrofitClient;

import io.reactivex.Flowable;

/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public class MainModel  implements MainContract.Model {
    @Override
    public Flowable<LoginBean> login(String username, String password) {
        return RetrofitClient.getInstance().getApi().login(username,password);
    }
}
