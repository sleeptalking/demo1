package com.hflh.demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hflh.demo.R;
import com.hflh.demo.adapter.ProductCategorySpinnerAdapter;
import com.hflh.demo.base.BaseMvpActivity;
import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.Product;
import com.hflh.demo.bean.ProductCategory;
import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.bean.Result;
import com.hflh.demo.contract.AddProductContract;
import com.hflh.demo.presenter.AddProductPresenter;
import com.hflh.demo.util.FileUtil;
import com.hflh.demo.util.ImageUtils;
import com.hflh.demo.util.IntentUtils;
import com.hflh.demo.util.JsonUtils;
import com.hflh.demo.util.LogUtils;
import com.hflh.demo.util.StringUtils;
import com.hflh.demo.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddProductActivity extends BaseMvpActivity<AddProductPresenter> implements AddProductContract.View {


    @BindView(R.id.tv_title_center)
    TextView tvTitle;
    @BindView(R.id.iv_add_product_verification_code)
    ImageView ivCode;
    @BindView(R.id.iv_thumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.iv_details_picture)
    ImageView ivDetailsPicture;
    @BindView(R.id.spinner_product_category)
    Spinner spinnerproductCategory;

    @BindView(R.id.et_add_product_name)
    EditText etName;
    @BindView(R.id.et_add_product_priority)
    EditText etPriority;
    @BindView(R.id.et_add_product_integration)
    EditText etIntegration;
    @BindView(R.id.et_add_product_normal_price)
    EditText etNormal;
    @BindView(R.id.et_add_product_promotion_price)
    EditText etPromotion;
    @BindView(R.id.et_add_product_code)
    EditText etCode;

    private ProductCategorySpinnerAdapter productCategoryAdapter;
    private List<ProductCategory> productCategoryList = new ArrayList<>();
    private ProductCategory currentproductCategory;
    private String sessionId = "";
    private long shopId;
    private String filePath = "";
    private List<String> images = new ArrayList<>();

    private Long productId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_product;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.product_info);
        shopId = getIntent().getLongExtra("shopId",-1);
        productId = getIntent().getLongExtra("productId",-1);

        mPresenter = new AddProductPresenter();
        mPresenter.attachView(this);
        mPresenter.getCodeImage();
        mPresenter.getProductCategoryList(shopId);
        if(productId != -1){
            mPresenter.getProductById(productId);
        }
        initSpinner();
    }

    private void initSpinner(){
        productCategoryAdapter = new ProductCategorySpinnerAdapter(productCategoryList,this);
        spinnerproductCategory.setAdapter(productCategoryAdapter);
        spinnerproductCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentproductCategory = productCategoryList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    @OnClick(R.id.bt_product_add_submit)
    public void submit(){
        Product product = new Product();
        String name = etName.getText().toString();
        String priority = etPriority.getText().toString();
        String code = etCode.getText().toString();
        String integration = etIntegration.getText().toString();
        String normal = etNormal.getText().toString();
        String promotion = etPromotion.getText().toString();

        if(StringUtils.isNullString(name)){
            ToastUtils.showToast(this,"请输入商品名",false);
            return;
        }
        product.setProductName(name);
        if(StringUtils.isNullString(priority)){
            ToastUtils.showToast(this,"请输入优先级",false);
            return;
        }
        product.setPriority(Integer.parseInt(priority));


        if(StringUtils.isNullString(normal)){
            ToastUtils.showToast(this,"请输入原价",false);
            return;
        }
        product.setNormalPrice(normal);
        if(StringUtils.isNullString(promotion)){
            ToastUtils.showToast(this,"请输入现价",false);
            return;
        }
        product.setPromotionPrice(promotion);
        product.setProductCategory(currentproductCategory);
        if(StringUtils.isNullString(code)){
            ToastUtils.showToast(this,"请输入验证码",false);
            return;
        }
        if (StringUtils.isNullString(filePath)){
            ToastUtils.showToast(this,"请选择缩略图",false);
            return;
        }
        if (images == null || images.size() == 0){
            ToastUtils.showToast(this,"请选择详情图片",false);
            return;
        }
        mPresenter.addProduct(sessionId, ""+shopId,JsonUtils.serialize(product),code,filePath,images);

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


    @OnClick(R.id.iv_add_product_verification_code)
    public void initCode(){
        mPresenter.getCodeImage();
    }

    @OnClick({R.id.iv_title_back,R.id.bt_product_add_back})
    public void back(){
        this.finish();
    }



    
    @OnClick(R.id.iv_thumbnail)
    public void getThumbnail(){
      /*  Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, 101);*/
        IntentUtils.toPicture(this,101);

    }
    @OnClick(R.id.iv_details_picture)
    public void getDetailsPicture(){
        /*Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, 102);*/
        IntentUtils.toPicture(this,102);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                switch (requestCode){
                    case 101:

                        filePath = FileUtil.getFilePathByUri(this, uri);
                        ivThumbnail.setImageURI(uri);
                        Log.i("TAG", "onActivityResult: "+filePath);
                        break;
                    case 102:
                        String path = FileUtil.getFilePathByUri(this, uri);
                        images.add(path);
                        ivDetailsPicture.setImageURI(uri);
                        break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void addProduct(String str) {

    }

    @Override
    public void modifyProduct(Result<Product> productResult) {

    }

    @Override
    public void getCodeImage(CodeImageBean bean) {
        sessionId = "JSESSIONID="+bean.getSessionId();
        ivCode.setImageBitmap(ImageUtils.stringtoBitmap(bean.getCodeImage()));

    }

    @Override
    public void getProductCategoryList(ProductCategoryListBean bean) {
        productCategoryList.clear();
        productCategoryList.addAll(bean.getData());
        productCategoryAdapter.notifyDataSetChanged();
        currentproductCategory = bean.getData().get(0);


    }

    @Override
    public void getProductById(Result<Product> productResult) {

        LogUtils.i("getProductById///"+productResult.isSuccess());

    }
}
