package com.android.HuoBiAssistant.model.bean;

/**
 * 买入、卖出的返回值
 * Created by xianling on 2016/4/15.
 */
public class EntrustRes {
    private String result;
    private String id;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LimitPriceSellInfo{" +
                "result='" + result + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
