package com.android.HuoBiAssistant.model.bean;


/**
 * Created by Dragon丶Lz on 2016/4/5.
 */
public class NowEntrust {


        private long id;

        private int type;

        private String order_price;

        private String order_amount;

        private String processed_amount;

        private long order_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getProcessed_amount() {
        return processed_amount;
    }

    public void setProcessed_amount(String processed_amount) {
        this.processed_amount = processed_amount;
    }

    public long getOrder_time() {
        return order_time;
    }

    public void setOrder_time(long order_time) {
        this.order_time = order_time;
    }
}
