package com.android.HuoBiAssistant.model.rest;

import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.bean.AccountInfo;
import com.android.HuoBiAssistant.model.bean.EntrustDetail;
import com.android.HuoBiAssistant.model.bean.EntrustRes;
import com.android.HuoBiAssistant.model.bean.NowEntrust;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public interface ExperApi {


    @FormUrlEncoded
    @POST(Constants.api)
    void get_account_info(@Field("method") String method, @Field("created") long created,
                          @Field("access_key") String access_key, @Field("sign") String sign, Callback<AccountInfo> cb);

    @FormUrlEncoded
    @POST(Constants.api)
    void get_now_entrust(@Field("method") String method, @Field("created") long created,
                         @Field("access_key") String access_key, @Field("sign") String sign,
                         @Field("coin_type") String coin_type, Callback<NowEntrust> cb);

    /**
     * 限价买入
     */
    @FormUrlEncoded
    @POST(Constants.api)
    void limitBuy(@Field("method") String method, @Field("created") long created,
                  @Field("access_key") String access_key, @Field("sign") String sign,
                  @Field("coin_type") String coin_type,
                  @Field("amount") String amount, @Field("price") String price, Callback<EntrustRes> cb);

    /**
     * 获取委托详情
     */
    @FormUrlEncoded
    @POST(Constants.api)
    void order_info(@Field("method") String method, @Field("created") long created,
                    @Field("access_key") String access_key, @Field("sign") String sign,
                    @Field("coin_type") String coin_type,
                    @Field("id") String id, Callback<EntrustDetail> cb);

    /**
     * 市价买入
     */
    @FormUrlEncoded
    @POST(Constants.api)
    void marketprice_buy(@Field("method") String method, @Field("created") long created,
                         @Field("access_key") String access_key, @Field("sign") String sign,
                         @Field("coin_type") String coin_type,
                         @Field("amount") String amount, Callback<EntrustRes> cb);

    /**
     * 市价卖出
     */
    @FormUrlEncoded
    @POST(Constants.api)
    void marketprice_sell(@Field("method") String method, @Field("created") long created,
                          @Field("access_key") String access_key, @Field("sign") String sign,
                          @Field("coin_type") String coin_type,
                          @Field("amount") String amount, Callback<EntrustRes> cb);

    /**
     * 限价卖出
     */
    @FormUrlEncoded
    @POST(Constants.api)
    void limitprice_sell(@Field("method") String method, @Field("created") long created,
                         @Field("access_key") String access_key, @Field("sign") String sign,
                         @Field("coin_type") String coin_type,
                         @Field("amount") String amount, @Field("price") String price, Callback<EntrustRes> cb);

}
