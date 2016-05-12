package com.android.HuoBiAssistant.model.bean;

/**
 * 火币API 委托详情 Bean
 * Created by heyiyong on 2016/4/15.
 */
public class EntrustDetail {
    private String id; // 委托ID
    private int type; // 1限价买　2限价卖　3市价买　4市价卖
    private Double processed_price; // 委托均价
    private int status; // 状态（0未成交　1部分成交　2已完成　3已取消  5异常 6部分成交已取消 7队列中）
    private Double total;
    @Override
    public String toString() {
        return "EntrustDetail{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", processed_price=" + processed_price +
                ", status=" + status +
                '}';
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getProcessed_price() {
        return processed_price;
    }

    public void setProcessed_price(Double processed_price) {
        this.processed_price = processed_price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
