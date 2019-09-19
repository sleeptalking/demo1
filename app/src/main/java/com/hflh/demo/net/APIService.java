package com.hflh.demo.net;


import com.hflh.demo.bean.CodeImageBean;
import com.hflh.demo.bean.LoginBean;
import com.hflh.demo.bean.ProductCategoryListBean;
import com.hflh.demo.bean.ProductExecution;
import com.hflh.demo.bean.ShopInfoBean;
import com.hflh.demo.bean.ShopListBean;
import com.hflh.demo.bean.ShopManagementBean;
import com.hflh.demo.bean.YuQingGaoWeiBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * @author azheng
 * @date 2018/4/24.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public interface APIService {

    /**
     * 登陆
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String password);
    /**
     * @return
     */
    @FormUrlEncoded
    @POST("datasync/dataNews/queryNews.do")
    Observable<YuQingGaoWeiBean> getData(@Field("pageSize") String pageSize, @Field("pageNo") String pageNo, @Field("endTime") String endTime,
                                          @Field("startTime") String startTime, @Field("type") String type, @Field("title") String title, @Field("areaId") String areaId
    );
    /**
     *
     * @return
     */
    @GET("o2o/shopadmin/getshoplist")
    Observable<ShopListBean> getShopList();
    /**
     *
     * @return
     */
    @GET("o2o/shopadmin/getshopinitinfo")
    Observable<ShopInfoBean> getShopInfo();
    /**
     * @return
     */
    @Multipart
    @POST("o2o/shopadmin/registershop")
    Observable<Object> addShop(@Header("Cookie") String lang, @Part("shopStr")RequestBody shopStr,@Part("verifyCodeActual")RequestBody verifyCodeActual
            ,@Part MultipartBody.Part shopImg);
    /**
     * @return
     */
    @Multipart
    @POST("o2o/shopadmin/addproduct")
    Observable<String> addProduct(@Header("Cookie") String lang, @Part("productStr")RequestBody productStr, @Part("thumbnail")RequestBody thumbnail,
                                  @Part("verifyCodeActual")RequestBody verifyCodeActual, @PartMap Map<String,RequestBody> map);
    /**
    /**
     * @return
     */
    @Multipart
    @POST("o2o/shopadmin/addproduct")
    Observable<String> addProduct(@Header("Cookie") String lang, @Part("shopId")RequestBody shopId, @Part("productStr")RequestBody productStr, @Part MultipartBody.Part thumbnail,
                                  @Part("verifyCodeActual")RequestBody verifyCodeActual, @Part List<MultipartBody.Part> list);
    /**
     * @return
     */
    @GET("o2o/code/kaptcha")
    Observable<CodeImageBean> getCodeImage();
    /**
     * @return
     */
    @FormUrlEncoded
    @POST("/o2o/shopadmin/getshopbyid")
    Observable<ShopManagementBean> getShopById(@Field("shopId") Long shopId);
    /**
     * @return
     */
    @FormUrlEncoded
    @POST("/o2o/shopadmin/addproductcatrogy")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    Observable<String> addProductCategory(@Field("productCategory") String productCategory,@Field("shopId") Long shopId);
    /**
     * @return
     */
    @FormUrlEncoded
    @POST("/o2o/shopadmin/removeproductcategory")
    Observable<String> removeProductCategory(@Field("productCategoryId") Long productCategoryId,@Field("shopId") Long shopId);

    @FormUrlEncoded
    @POST("/o2o/shopadmin/getproductcategorylist")
    Observable<ProductCategoryListBean> getProductCategoryList(@Field("shopId") Long shopId);

    @FormUrlEncoded
    @POST("/o2o/shopadmin/getproductlist")
    Observable<ProductExecution> getProductList(@Field("productStr") String productStr,@Field("pageIndex")int pageIndex,@Field("pageSize")int pageSize);
}
