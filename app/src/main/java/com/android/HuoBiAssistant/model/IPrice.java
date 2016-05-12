package com.android.HuoBiAssistant.model;

import com.android.HuoBiAssistant.model.bean.BTCBean;
import retrofit.Callback;


/**
 * Created by heyiyong on 2016/4/11.
 */
public interface IPrice {
    void requestPrice(Callback<BTCBean> btcBeanCallback);
}
