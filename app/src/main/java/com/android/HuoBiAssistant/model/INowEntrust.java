package com.android.HuoBiAssistant.model;



import com.android.HuoBiAssistant.model.bean.NowEntrust;
import com.android.HuoBiAssistant.model.callback.PresenterCallback;

import java.util.List;

import retrofit.Callback;

/**
 * Created by Dragon丶Lz on 2016/4/5.
 */
public interface INowEntrust {

    void LoadNowEntrustDataFromHttp(String method,PresenterCallback callback);

    void LoadNowEntrustDataFromDB();

    List<NowEntrust> praseData(String response);
}
