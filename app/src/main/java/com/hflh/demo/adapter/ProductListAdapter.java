package com.hflh.demo.adapter;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hflh.demo.R;
import com.hflh.demo.bean.Product;

import java.util.List;

public class ProductListAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    private Activity context;

    public ProductListAdapter(int layoutResId, @Nullable List<Product> data, Activity context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        helper.setText(R.id.tv_product_name, item.getProductName());
        int i = item.getPriority();
        helper.setText(R.id.tv_product_priority, ""+i);
        helper.addOnClickListener(R.id.tv_product_edit);
        helper.addOnClickListener(R.id.tv_product_preview);
        helper.addOnClickListener(R.id.tv_product_off);
    }

}
