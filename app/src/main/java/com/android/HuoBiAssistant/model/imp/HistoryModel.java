package com.android.HuoBiAssistant.model.imp;

import android.content.Context;
import android.util.Log;

import com.android.HuoBiAssistant.app.BaseModel;
import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.IHistory;
import com.android.HuoBiAssistant.model.bean.History;
import com.android.HuoBiAssistant.model.bean.NowEntrust;
import com.android.HuoBiAssistant.model.callback.HttpCallbackListener;
import com.android.HuoBiAssistant.model.callback.PresenterCallback;
import com.android.HuoBiAssistant.util.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by xianling on 2016/4/11.
 */
public class HistoryModel extends BaseModel implements IHistory{
    public HistoryModel(Context context) {
        super(context);
    }

    @Override
    public void LoadHistoryDataFromHttp(String method, final PresenterCallback callback) {
        OkHttpUtils.OKHttpBuy(Constants.BUY, new HttpCallbackListener() {
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
    public void LoadHistoryDataFromDB() {

    }

    @Override
    public List<History> praseData(String response) {
       System.out.println(response);
        Gson gson = new Gson();
        List<History> list = gson.fromJson(response,new TypeToken<List<History>>(){}.getType());
        return list;
    }
}
