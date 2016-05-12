package com.android.HuoBiAssistant.model.callback;

/**
 * Created by Dragon丶Lz on 2016/4/11.
 */
public interface PresenterCallback {

    void onSuccess(String response);

    void onFailure(Exception e);
}
