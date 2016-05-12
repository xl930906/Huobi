package com.android.HuoBiAssistant.model.rest;

import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.bean.BTCBean;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * 实时行情请求
 * Created by heyiyong on 2016/4/11.
 */
public interface PriceApi {

    @GET(Constants.priceApi)
    void ticker_btc_json(Callback<BTCBean> btcBeanCallback);

}
