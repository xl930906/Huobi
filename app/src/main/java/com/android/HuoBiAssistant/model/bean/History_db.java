package com.android.HuoBiAssistant.model.bean;

/**
 * Created by xianling on 2016/4/12.
 */
public class History_db {
    private String db__entrust_time;
    private String db_entrust_state;
    private String db_entrust_price;
    private String db__entrust_count;
    private String db_entrust_loss;
    private String db_avg_price;

    public String getDb_avg_price() {
        return db_avg_price;
    }

    public void setDb_avg_price(String db_avg_price) {
        this.db_avg_price = db_avg_price;
    }

    public String getDb__entrust_count() {
        return db__entrust_count;
    }

    public void setDb__entrust_count(String db__entrust_count) {
        this.db__entrust_count = db__entrust_count;
    }

    public String getDb_entrust_price() {
        return db_entrust_price;
    }

    public void setDb_entrust_price(String db_entrust_price) {
        this.db_entrust_price = db_entrust_price;
    }

    public String getDb_entrust_state() {
        return db_entrust_state;
    }

    public void setDb_entrust_state(String db_entrust_state) {
        this.db_entrust_state = db_entrust_state;
    }

    public String getDb__entrust_time() {
        return db__entrust_time;
    }

    public void setDb__entrust_time(String db__entrust_time) {
        this.db__entrust_time = db__entrust_time;
    }

    public String getDb_entrust_loss() {
        return db_entrust_loss;
    }

    public void setDb_entrust_loss(String db_entrust_loss) {
        this.db_entrust_loss = db_entrust_loss;
    }
}
