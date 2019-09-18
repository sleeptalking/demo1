package com.hflh.demo.bean;

import java.util.List;

public class ProductCategoryListBean {


    //结果状态
    private boolean success;
    private int errorCode;
    private String errorMsg;

    private List<ProductCategory> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<ProductCategory> getData() {
        return data;
    }

    public void setData(List<ProductCategory> data) {
        this.data = data;
    }
}
