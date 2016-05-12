package com.android.HuoBiAssistant.model.imp;

import android.content.Context;

import com.android.HuoBiAssistant.app.BaseModel;
import com.android.HuoBiAssistant.common.BaseUtils;
import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.IEntrustModel;
import com.android.HuoBiAssistant.model.bean.EntrustRes;
import com.android.HuoBiAssistant.model.bean.req.BuyReqBean;
import com.android.HuoBiAssistant.model.bean.req.LimitPriceSellInfo;
import com.android.HuoBiAssistant.model.bean.req.MarketPriceSellInfo;

import retrofit.Callback;

/**
 * 限价交易
 * Created by xianling on 2016/4/15.
 */
public class EntrustModel extends BaseModel implements IEntrustModel {
    public EntrustModel(Context context) {
        super(context);
    }

    /**
     * 限价卖出
     *
     * @param amount
     * @param price
     * @param callback
     */
    @Override
    public void getLimitPriceSell(String amount, String price, Callback<EntrustRes> callback) {
        long timestamp = BaseUtils.getTimestamp();
        LimitPriceSellInfo reqBean = new LimitPriceSellInfo();
        reqBean.created = timestamp;
        reqBean.method = Constants.SELL;
        reqBean.coin_type = "1";
        reqBean.amount = amount;
        reqBean.price = price;
        getExperApi().limitprice_sell(Constants.SELL, timestamp, Constants.HUOBI_ACCESS_KEY, BaseUtils.getSignBody(reqBean), "1", amount, price, callback);
    }

    /**
     * 限价买入
     *
     * @param amount 买入数量
     * @param price  买入价格
     */
    @Override
    public void limitPriceBuy(String amount, String price, Callback<EntrustRes> callback) {
        long timestamp = BaseUtils.getTimestamp();
        BuyReqBean reqBean = new BuyReqBean();
        reqBean.created = timestamp;
        reqBean.method = Constants.BUY;
        reqBean.coin_type = "1";
        reqBean.amount = amount;
        reqBean.price = price;
        getExperApi().limitBuy(Constants.BUY, timestamp, Constants.HUOBI_ACCESS_KEY, BaseUtils.getSignBody(reqBean), "1", amount, price, callback);
    }



    @Override
    public void marketPriceBuy(String amount, Callback<EntrustRes> callback) {
        long timestamp = BaseUtils.getTimestamp();
        BuyReqBean reqBean = new BuyReqBean();
        reqBean.created = timestamp;
        reqBean.method = Constants.BUY_MARKET;
        reqBean.coin_type = "1";
        reqBean.amount = amount;
        getExperApi().marketprice_buy(Constants.BUY_MARKET, timestamp, Constants.HUOBI_ACCESS_KEY, BaseUtils.getSignBody(reqBean), "1", amount, callback);
    }

    @Override
    public void marketPriceSell(String amount, Callback<EntrustRes> callback) {
        long timestamp = BaseUtils.getTimestamp();
        MarketPriceSellInfo reqBean = new MarketPriceSellInfo();
        reqBean.created = timestamp;
        reqBean.method = Constants.SELL_MARKET;
        reqBean.coin_type = "1";
        reqBean.amount = amount;
        getExperApi().marketprice_sell(Constants.SELL_MARKET, timestamp, Constants.HUOBI_ACCESS_KEY, BaseUtils.getSignBody(reqBean), "1", amount, callback);
    }
}
