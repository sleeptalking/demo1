package com.hflh.demo.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hflh.demo.R;
import com.hflh.demo.adapter.ProductListAdapter;
import com.hflh.demo.base.BaseMvpActivity;
import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.ProductExecution;
import com.hflh.demo.contract.ProductListContract;
import com.hflh.demo.presenter.ProductListPresenter;
import com.hflh.demo.util.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductListActivity extends BaseMvpActivity<ProductListPresenter> implements ProductListContract.View {


    @BindView(R.id.tv_title_center)
    TextView titleCenter;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rv_product_list)
    RecyclerView recyclerView;

    private ProductListAdapter adapter;

    private List<Product> productList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_list;
    }

    @Override
    public void initView() {
        mPresenter = new ProductListPresenter();
        Product product = new Product();
        mPresenter.getProductList(product,0,100);
        titleCenter.setText("商品列表");
        initRecyclerView();
    }

    @SuppressLint("WrongConstant")
    private void initRecyclerView() {
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //创建适配器
        adapter = new ProductListAdapter(R.layout.item_shop_list, productList, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
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

    @OnClick(R.id.iv_title_back)
    public void back() {
        this.finish();
    }

    @OnClick(R.id.tv_add_shop)
    public void toAddShop() {
        IntentUtils.toActivity(this, AddShopActivity.class);
    }


    @Override
    public void getProductList(ProductExecution productExecution) {
        productList.clear();
        productList.addAll(productExecution.getProductList());
        adapter.notifyDataSetChanged();
    }
}