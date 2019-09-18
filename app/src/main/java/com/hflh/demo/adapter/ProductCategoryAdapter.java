package com.hflh.demo.adapter;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hflh.demo.R;
import com.hflh.demo.bean.ProductCategory;

import java.util.List;

public class ProductCategoryAdapter extends BaseQuickAdapter<ProductCategory, BaseViewHolder> {
    private Activity context;
    public ProductCategoryAdapter(int layoutResId, @Nullable List<ProductCategory> data, Activity context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductCategory item) {
        helper.setText(R.id.tv_product_category_name, item.getProductCategoryName());
        helper.setText(R.id.tv_product_category_priority, ""+item.getPriority());
        helper.addOnClickListener(R.id.tv_product_category_operation);
    }


}
