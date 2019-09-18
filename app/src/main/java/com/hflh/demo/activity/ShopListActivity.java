package com.hflh.demo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hflh.demo.R;
import com.hflh.demo.adapter.ShopListAdapter;
import com.hflh.demo.base.BaseMvpActivity;
import com.hflh.demo.bean.Shop;
import com.hflh.demo.bean.ShopListBean;
import com.hflh.demo.contract.ShopListContract;
import com.hflh.demo.presenter.ShopListPresenter;
import com.hflh.demo.util.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopListActivity extends BaseMvpActivity<ShopListPresenter> implements ShopListContract.View {


    @BindView(R.id.tv_title_center)
    TextView titleCenter;
    @BindView(R.id.tv_title_right)
    TextView titleRight;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rv_shop_list)
    RecyclerView recyclerView;

    private ShopListAdapter adapter;

    private List<Shop> shopList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_list;
    }

    @Override
    public void initView() {
        mPresenter = new ShopListPresenter();
        mPresenter.attachView(this);
        mPresenter.getShopList();
        titleCenter.setText("店铺列表");
        initRecyclerView();
    }

    @SuppressLint("WrongConstant")
    private void initRecyclerView() {
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //创建适配器
        adapter = new ShopListAdapter(R.layout.item_shop_list, shopList, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ShopListActivity.this,ShopManagementActivity.class);
                intent.putExtra("shopId",shopList.get(position).getShopId());
                IntentUtils.toActivity(ShopListActivity.this, intent);
            }
        });
    }

    @Override
    public void onSuccess(ShopListBean bean) {

        tvName.setText("你好，" + bean.getUser().getName());
        shopList.clear();
        shopList.addAll(bean.getShopList());
        adapter.notifyDataSetChanged();

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



}