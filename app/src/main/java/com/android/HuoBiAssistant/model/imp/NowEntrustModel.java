package com.android.HuoBiAssistant.model.imp;

import android.content.Context;
import android.util.Log;

import com.android.HuoBiAssistant.app.BaseModel;
import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.INowEntrust;
import com.android.HuoBiAssistant.model.bean.NowEntrust;
import com.android.HuoBiAssistant.model.callback.HttpCallbackListener;
import com.android.HuoBiAssistant.model.callback.PresenterCallback;
import com.android.HuoBiAssistant.util.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;

/**
 * Created by Dragon丶Lz on 2016/4/5.
 */
public class NowEntrustModel extends BaseModel implements INowEntrust{


    public NowEntrustModel(Context context) {
        super(context);
    }

    @Override
    public void LoadNowEntrustDataFromHttp(String method, final PresenterCallback callback) {

        OkHttpUtils.OKHttpNowEntrust(Constants.GET_ORDERS, new HttpCallbackListener() {
            @Override
                public void onSuccess(String response) {
                callback.onSuccess(response);
                }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void LoadNowEntrustDataFromDB() {

    }

    @Override
    public List<NowEntrust> praseData(String response) {
        Log.d("TAG---->>",response);
        Gson gson = new Gson();
        List<NowEntrust> dataList = gson.fromJson(response,new TypeToken<List<NowEntrust>>(){}.getType());

        return dataList;

    }
}
