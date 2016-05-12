package com.android.HuoBiAssistant.model;

import com.android.HuoBiAssistant.model.bean.History;
import com.android.HuoBiAssistant.model.bean.NowEntrust;
import com.android.HuoBiAssistant.model.callback.PresenterCallback;

import java.util.List;

/**
 * Created by xianling on 2016/4/11.
 */
public interface IHistory {
    void LoadHistoryDataFromHttp(String method,PresenterCallback callback);

    void LoadHistoryDataFromDB();


    List<History> praseData(String response);
}
