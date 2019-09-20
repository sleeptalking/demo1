package com.hflh.demo.activity;

import android.content.Intent;
import android.widget.TextView;

import com.hflh.demo.R;
import com.hflh.demo.base.BaseActivity;
import com.hflh.demo.util.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopManagementActivity extends BaseActivity {

    @BindView(R.id.tv_title_center)
    TextView tvTitle;

    private Long shopId;


    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_management;
    }

    @Override
    public void initView() {
        tvTitle.setText("店铺管理");
        shopId = getIntent().getLongExtra("shopId",-1l);
    }


    @OnClick(R.id.bt_shop_management_info)
    public void shopInfo() {
        Intent intent = new Intent(this,AddShopActivity.class);
        intent.putExtra("shopId",shopId);
        IntentUtils.toActivity(this,intent);

    }
    @OnClick(R.id.bt_shop_management)
    public void shopManagement() {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("shopId",shopId);
        IntentUtils.toActivity(this,intent);
    }
    @OnClick(R.id.bt_shop_category_management)
    public void shopCategoryManagement() {
        Intent intent = new Intent(this, ProductCategoryManagementActivity.class);
        intent.putExtra("shopId",shopId);
        IntentUtils.toActivity(this,intent);
    }

    @OnClick({R.id.iv_title_back,R.id.bt_shop_management_back})
    public void back() {
        this.finish();
    }
}
