package com.android.HuoBiAssistant.model.bean;

/**
 * Created by Dragon丶Lz on 2016/4/9.
 */
public class NowBean {
    private String now_bean_time;

    private String now_bean_entrust_price;

    private String now_bean_entrust_num;

    private String now_bean_loss;

    private String id;

    /**
     * true 表示买
     * false 表示卖
     */
    private boolean business;

    public NowBean(String now_bean_time,String now_bean_entrust_price,String now_bean_entrust_num,String now_bean_loss,Boolean business,String id){
        this.now_bean_entrust_num = now_bean_entrust_num;
        this.now_bean_entrust_price = now_bean_entrust_price;
        this.now_bean_loss = now_bean_loss;
        this.now_bean_time = now_bean_time;
        this.business =business;
        this.id =id;
    }

    public String getNow_bean_time() {
        return now_bean_time;
    }

    public void setNow_bean_time(String now_bean_time) {
        this.now_bean_time = now_bean_time;
    }

    public String getNow_bean_entrust_price() {
        return now_bean_entrust_price;
    }

    public void setNow_bean_entrust_price(String now_bean_entrust_price) {
        this.now_bean_entrust_price = now_bean_entrust_price;
    }

    public String getNow_bean_entrust_num() {
        return now_bean_entrust_num;
    }

    public void setNow_bean_entrust_num(String now_bean_entrust_num) {
        this.now_bean_entrust_num = now_bean_entrust_num;
    }

    public String getNow_bean_loss() {
        return now_bean_loss;
    }

    public void setNow_bean_loss(String now_bean_loss) {
        this.now_bean_loss = now_bean_loss;
    }

    public boolean isBusiness() {
        return business;
    }

    public void setBusiness(boolean business) {
        this.business = business;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
