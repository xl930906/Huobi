package com.android.HuoBiAssistant.model.bean;

/**
 * Created by xianling on 2016/4/15.
 */
public class MarketPriceBuy {
    private String result;
    private String id;

    @Override
    public String toString() {
        return "MarketPriceBuy{" +
                "result='" + result + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

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
}
