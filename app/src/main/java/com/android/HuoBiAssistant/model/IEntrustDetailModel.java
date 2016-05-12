package com.android.HuoBiAssistant.model;

import com.android.HuoBiAssistant.model.bean.EntrustDetail;

import retrofit.Callback;

/**
 * Created by heyiyong on 2016/4/15.
 */
public interface IEntrustDetailModel {
    /**
     * 获取价格详情
     *
     * @param id
     * @param callback
     */
    public void getDetail(String  id, Callback<EntrustDetail> callback);
}
