package com.hflh.demo.adapter;

import android.app.Activity;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hflh.demo.R;
import com.hflh.demo.bean.Shop;

import java.util.List;

public class ShopListAdapter extends BaseQuickAdapter<Shop, BaseViewHolder> {
    private Activity context;
    public ShopListAdapter(int layoutResId, @Nullable List<Shop> data, Activity context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Shop item) {
        helper.setText(R.id.tv_shop_name, item.getShopName());
        helper.setText(R.id.tv_shop_status, item.getAdvice());
        if (item.getEnableStatus() == 1) {
            helper.<TextView>getView(R.id.tv_shop_operation).setTextColor(context.getResources().getColor(R.color.blue));
            helper.addOnClickListener(R.id.tv_shop_operation);
        }
    }

}
