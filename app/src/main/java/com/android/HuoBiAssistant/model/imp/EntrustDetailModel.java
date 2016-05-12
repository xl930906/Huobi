package com.android.HuoBiAssistant.model.imp;

import android.content.Context;

import com.android.HuoBiAssistant.app.BaseModel;
import com.android.HuoBiAssistant.common.BaseUtils;
import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.IEntrustDetailModel;
import com.android.HuoBiAssistant.model.bean.EntrustDetail;
import com.android.HuoBiAssistant.model.bean.req.OrderInfoReqBean;

import retrofit.Callback;

/**
 * Created by heyiyong on 2016/4/15.
 */
public class EntrustDetailModel extends BaseModel implements IEntrustDetailModel {
    public EntrustDetailModel(Context context) {
        super(context);
    }

    @Override
    public void getDetail(String id, Callback<EntrustDetail> callback) {
        long timestamp = BaseUtils.getTimestamp();
        OrderInfoReqBean reqBean = new OrderInfoReqBean();
        reqBean.created = timestamp;
        reqBean.method = Constants.ORDER_INFO;
        reqBean.id = id;
        reqBean.coin_type = "1";
        getExperApi().order_info(Constants.ORDER_INFO, timestamp, Constants.HUOBI_ACCESS_KEY, BaseUtils.getSignBody(reqBean), "1", String.valueOf(id), callback);
    }

}
