package com.hflh.demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.hflh.demo.R;
import com.hflh.demo.adapter.AreaAdapter;
import com.hflh.demo.adapter.ShopCategoryAdapter;
import com.hflh.demo.base.BaseMvpActivity;
import com.hflh.demo.bean.Area;
import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.Shop;
import com.hflh.demo.bean.ShopCategory;
import com.hflh.demo.bean.ShopInfoBean;
import com.hflh.demo.bean.ShopManagementBean;
import com.hflh.demo.contract.AddShopContract;
import com.hflh.demo.presenter.AddShopPresenter;
import com.hflh.demo.util.FileUtil;
import com.hflh.demo.util.ImageUtils;
import com.hflh.demo.util.JsonUtils;
import com.hflh.demo.util.LogUtils;
import com.hflh.demo.util.StringUtils;
import com.hflh.demo.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddShopActivity extends BaseMvpActivity<AddShopPresenter> implements AddShopContract.View {


    @BindView(R.id.tv_title_center)
    TextView tvTitle;
    @BindView(R.id.iv_add_shop_verification_code)
    ImageView ivCode;
    @BindView(R.id.iv_thumbnail)
    ImageView ivThumbnail;
    @BindView(R.id.spinner_shop_category)
    Spinner spinnerShopCategory;
    @BindView(R.id.spinner_shop_area)
    Spinner spinnerShopArea;

    @BindView(R.id.et_add_shop_name)
    EditText etName;
    @BindView(R.id.et_add_shop_desc)
    EditText etDesc;
    @BindView(R.id.et_add_shop_address)
    EditText etaddress;
    @BindView(R.id.et_add_shop_phone)
    EditText etPhone;
    @BindView(R.id.et_add_shop_code)
    EditText etCode;

    private ShopCategoryAdapter shopCategoryAdapter;
    private AreaAdapter areaAdapter;
    private List<ShopCategory> shopCategoryList = new ArrayList<>();
    private List<Area> areaList = new ArrayList<>();
    private ShopCategory currentShopCategory;
    private Area currentArea;
    private String sessionId = "";
    private String filePath = "";

    private Long shopId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shop;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.shop_info);
        mPresenter = new AddShopPresenter();
        mPresenter.attachView(this);
        mPresenter.getCodeImage();
        initSpinner();

        shopId = getIntent().getLongExtra("shopId",-1);
        if(shopId != -1){
            mPresenter.getShopById(shopId);
        }else{
            mPresenter.getShopInfo();
        }

    }

    private void initSpinner(){
        shopCategoryAdapter = new ShopCategoryAdapter(shopCategoryList,this);
        spinnerShopCategory.setAdapter(shopCategoryAdapter);
        spinnerShopCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentShopCategory = shopCategoryList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        areaAdapter = new AreaAdapter(areaList,this);
        spinnerShopArea.setAdapter(areaAdapter);
        spinnerShopArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentArea = areaList.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onSuccess(ShopInfoBean bean) {
        Log.i("TAG", "onSuccess shopinifobean: " + bean.toString());
        shopCategoryList.clear();
        shopCategoryList.addAll(bean.getShopCategoryList());
        shopCategoryAdapter.notifyDataSetChanged();
        areaList.clear();
        areaList.addAll(bean.getAreaList());
        currentShopCategory = bean.getShopCategoryList().get(0);
        areaAdapter.notifyDataSetChanged();
    }



    @Override
    public void addShopResult(Object str) {

        LogUtils.i("addShopResult: "+str);

    }

    @Override
    public void getShopById(ShopManagementBean bean) {
        etName.setText(bean.getShop().getShopName());
        etDesc.setText(bean.getShop().getShopDesc());
        etaddress.setText(bean.getShop().getShopAddr());
        etPhone.setText(bean.getShop().getPhone());
        etName.setText(bean.getShop().getShopName());
        shopCategoryList.clear();
        shopCategoryList.add(bean.getShop().getShopCategory());
        shopCategoryAdapter.notifyDataSetChanged();
        areaList.clear();
        areaList.addAll(bean.getAreaList());
        areaAdapter.notifyDataSetChanged();
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void getCodeImage(CodeImageBean bean) {
        sessionId = "JSESSIONID="+bean.getSessionId();
        ivCode.setImageBitmap(ImageUtils.stringtoBitmap(bean.getCodeImage()));

    }

    @OnClick(R.id.bt_shop_add_submit)
    public void submit(){
        Shop shop = new Shop();
        String name = etName.getText().toString();
        if(StringUtils.isNullString(name)){
            ToastUtils.showToast(this,R.string.please_input_shop_name,false);
            return;
        }
        String desc = etDesc.getText().toString();
        String address = etaddress.getText().toString();
        if(StringUtils.isNullString(address)){
            ToastUtils.showToast(this,R.string.please_input_shop_address,false);
            return;
        }
        String phone = etPhone.getText().toString();
        if(StringUtils.isNullString(phone)){
            ToastUtils.showToast(this,R.string.please_input_shop_phone,false);
            return;
        }
        shop.setShopName(name);
        shop.setShopDesc(desc);
        shop.setShopAddr(address);
        shop.setPhone(phone);
        shop.setArea(currentArea);
        shop.setShopCategory(currentShopCategory);

        String code = etCode.getText().toString();
        if(StringUtils.isNullString(code)){
            ToastUtils.showToast(this,R.string.please_input_code,false);
            return;
        }

        mPresenter.addShop(sessionId, JsonUtils.serialize(shop),code,new File(filePath));
       // mPresenter.addShop("", "","",new File(filePath));

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


    @OnClick(R.id.iv_add_shop_verification_code)
    public void initCode(){
        mPresenter.getCodeImage();
       // Picasso.with(this).load("http://10.0.2.2:8080/o2o/Kaptcha").memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                //.networkPolicy(NetworkPolicy.NO_CACHE).into(ivCode);
    }


    @OnClick({R.id.iv_title_back,R.id.bt_shop_add_back})
    public void back(){
        this.finish();
    }
    @OnClick(R.id.iv_thumbnail)
    public void getThumbnail(){
        //ImageSelectorUtils.openPhoto(AddShopActivity.this, 101, false, 9);

        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intentToPickPic, 101);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                filePath = FileUtil.getFilePathByUri(this, uri);
                ivThumbnail.setImageURI(uri);
                Log.i("TAG", "onActivityResult: "+filePath);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
