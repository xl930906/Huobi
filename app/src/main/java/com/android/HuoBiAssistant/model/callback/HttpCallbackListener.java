package com.android.HuoBiAssistant.model.callback;

/**
 * Created by Dragon丶Lz on 2016/3/28.
 */
public interface HttpCallbackListener {

    void onSuccess(String response);
    void onFailure(Exception e);
}
