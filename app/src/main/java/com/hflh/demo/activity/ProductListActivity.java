package com.hflh.demo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hflh.demo.R;
import com.hflh.demo.adapter.ProductListAdapter;
import com.hflh.demo.base.BaseMvpActivity;
import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.Result;
import com.hflh.demo.bean.Shop;
import com.hflh.demo.contract.ProductListContract;
import com.hflh.demo.presenter.ProductListPresenter;
import com.hflh.demo.util.IntentUtils;
import com.hflh.demo.util.LogUtils;
import com.hflh.demo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductListActivity extends BaseMvpActivity<ProductListPresenter> implements ProductListContract.View {


    @BindView(R.id.tv_title_center)
    TextView titleCenter;
    @BindView(R.id.rv_product_list)
    RecyclerView recyclerView;

    private ProductListAdapter adapter;

    private List<Product> productList = new ArrayList<>();

    private Long shopId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_list;
    }

    @Override
    public void initView() {
        shopId = getIntent().getLongExtra("shopId",-1);
        mPresenter = new ProductListPresenter();
        mPresenter.attachView(this);
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        product.setShop(shop);
        mPresenter.getProductList(product,1,100);
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
        adapter = new ProductListAdapter(R.layout.item_product_list, productList, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_product_edit:

                        Intent intent = new Intent(ProductListActivity.this,AddProductActivity.class);
                        intent.putExtra("shopId",shopId);
                        intent.putExtra("productId",productList.get(position).getProductId());
                        IntentUtils.toActivity(ProductListActivity.this,intent);
                        break;
                    case R.id.tv_product_off:  break;
                    case R.id.tv_product_preview:  break;
                }

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
    @OnClick(R.id.bt_product_add)
    public void addProduct() {
        Intent intent = new Intent(this,AddProductActivity.class);
        intent.putExtra("shopId",shopId);
        IntentUtils.toActivity(this,intent);
    }

    @Override
    public void getProductListSuccess(Result<List<Product>> productExecution) {
        LogUtils.i("123//"+productExecution.toString());

        productList.clear();
        productList.addAll(productExecution.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getProductListFailure(Result<List<Product>> productExecution) {
        ToastUtils.showToast(this,"获取数据失败",false);
    }
}