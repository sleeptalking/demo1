package com.hflh.demo.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hflh.demo.R;
import com.hflh.demo.adapter.ProductCategoryAdapter;
import com.hflh.demo.base.BaseMvpActivity;
import com.hflh.demo.bean.ProductCategory;
import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.contract.ProductCategoryManagementContract;
import com.hflh.demo.presenter.ProductCategoryManagementPresenter;
import com.hflh.demo.ui.AddCategoryDialog;
import com.hflh.demo.util.JsonUtils;
import com.hflh.demo.util.LogUtils;
import com.hflh.demo.util.StringUtils;
import com.hflh.demo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductCategoryManagementActivity extends BaseMvpActivity<ProductCategoryManagementPresenter> implements ProductCategoryManagementContract.View {

    @BindView(R.id.tv_title_center)
    TextView tvTitle;
    @BindView(R.id.rv_product_category_list)
    RecyclerView recyclerView;

    private long shopId;
    private ProductCategoryAdapter adapter;
    private List<ProductCategory> productCategoryList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_category_management;
    }



    @SuppressLint("WrongConstant")
    @Override
    public void initView() {
        tvTitle.setText("商品分类管理");
        shopId = getIntent().getLongExtra("shopId",-1);

        LogUtils.i(shopId+"");
        mPresenter = new ProductCategoryManagementPresenter();
        mPresenter.attachView(this);
        mPresenter.getProductCategoryList(shopId);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ProductCategoryAdapter(R.layout.item_product_category_list,productCategoryList,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                LogUtils.i("id:"+productCategoryList.get(position).getProductCategoryId()+shopId);
                mPresenter.removeProductCategory(productCategoryList.get(position).getProductCategoryId(),shopId);
            }
        });

    }

    @Override
    public void addProductCategory(String str) {


        LogUtils.i("addProductCategory"+str);


    }

    @Override
    public void removeProductCategory(String str) {

        LogUtils.i(str);

    }

    @Override
    public void getProductCategoryList(ProductCategoryListBean productCategoryListBean) {
        productCategoryList.clear();
        productCategoryList.addAll(productCategoryListBean.getData());
        adapter.notifyDataSetChanged();
        LogUtils.i("getProductCategoryList : "+productCategoryListBean.getData().size());
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

    @OnClick(R.id.bt_shop_category_add)
    public void addCategory() {

        AddCategoryDialog addCategoryDialog = new AddCategoryDialog(this);
        addCategoryDialog.setCancel(new AddCategoryDialog.OnBackListener() {
            @Override
            public void onBack() {
                LogUtils.i("back");
            }
        });
        addCategoryDialog.setConfirm(new AddCategoryDialog.OnSubmitListener() {
            @Override
            public void onSubmit(AddCategoryDialog dialog,String categoryName,String priority) {
                if(StringUtils.isNullString(categoryName)){
                    ToastUtils.showToast(ProductCategoryManagementActivity.this,"请输入分类名",false);
                    return;
                }
                if(StringUtils.isNullString(priority)){
                    ToastUtils.showToast(ProductCategoryManagementActivity.this,"请输入优先级",false);
                    return;
                }

                dialog.dismiss();
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryName(categoryName);
                productCategory.setPriority(Integer.parseInt(priority));

                mPresenter.addProductCategory(JsonUtils.serialize(productCategory),shopId);

                LogUtils.i("submit");
            }
        });

        addCategoryDialog.show();

    }

}
