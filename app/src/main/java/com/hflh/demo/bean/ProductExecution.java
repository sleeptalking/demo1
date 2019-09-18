package com.hflh.demo.bean;

import java.util.List;

public class ProductExecution {

    //结果状态
    private int state;
    //状态标识
    private String stateInfo;

    //店铺数量
    private int count;
    //操作的shop(增删改店铺的时候用到)
    private Product product;

    //店铺列表(查询店铺列表的时候用到)
    private List<Product> productList;

    public ProductExecution(){

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
