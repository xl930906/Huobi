package com.android.HuoBiAssistant.model;

import com.android.HuoBiAssistant.model.bean.AccountInfo;
import com.android.HuoBiAssistant.model.bean.AccountInfoBean;

import java.util.List;

import retrofit.Callback;


/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public interface IAccountInfo {

    void loadAccountinfoData(String method,long created,String access_key,String sign, Callback<AccountInfo> callback);

    List<String> loadTitleData();
}
