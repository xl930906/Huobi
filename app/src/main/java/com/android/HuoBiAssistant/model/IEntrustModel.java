package com.android.HuoBiAssistant.model;

import com.android.HuoBiAssistant.model.bean.EntrustRes;

import retrofit.Callback;

/**
 * 交易model
 * Created by xianling on 2016/4/15.
 */
public interface IEntrustModel {
    /**
     * 限价卖出
     * @param amount
     * @param price
     * @param callback
     */
    void getLimitPriceSell(String amount, String price, Callback<EntrustRes> callback);

    /**
     * 限价买入
     */
    void limitPriceBuy(String amount, String price, Callback<EntrustRes> callback);

    /**
     * 市价买入
     * @param amount
     * @param callback
     */
    void marketPriceBuy(String amount, Callback<EntrustRes> callback);

    /**
     * 市价卖出
     * @param amount
     * @param callback
     */
    void marketPriceSell(String amount, Callback<EntrustRes> callback);

}
