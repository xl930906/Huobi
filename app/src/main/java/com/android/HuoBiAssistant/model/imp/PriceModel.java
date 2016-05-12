package com.android.HuoBiAssistant.model.imp;

import android.content.Context;

import com.android.HuoBiAssistant.app.BaseModel;
import com.android.HuoBiAssistant.model.IPrice;
import com.android.HuoBiAssistant.model.bean.BTCBean;
import com.android.HuoBiAssistant.model.rest.ExperApi;
import com.android.HuoBiAssistant.model.rest.PriceApi;

import retrofit.Callback;

/**
 * Created by heyiyong on 2016/4/11.
 */
public class PriceModel extends BaseModel implements IPrice {
    private PriceApi priceApi;

    public PriceModel(Context context) {
        super(context);
        priceApi = createApi(PriceApi.class, context);
    }

    @Override
    public void requestPrice(Callback<BTCBean> callback) {
        priceApi.ticker_btc_json(callback);
    }
}
