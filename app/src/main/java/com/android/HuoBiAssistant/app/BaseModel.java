package com.android.HuoBiAssistant.app;

import android.content.Context;

import com.android.HuoBiAssistant.model.rest.ExperApi;
import com.android.HuoBiAssistant.util.RetrofitUtils;

/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public class BaseModel {

    private ExperApi mExperApi;

    public BaseModel(Context context) {
        mExperApi = createApi(ExperApi.class, context);
    }

    public ExperApi getExperApi() {
        return mExperApi;
    }
    /**
     * 创建API实例
     *
     * @param cls Api定义类的类型
     * @param <T> 范型
     * @return API实例
     */
    public  <T> T  createApi(Class<T> cls, Context context) {
        return RetrofitUtils.createApi(context, cls);
    }
}
