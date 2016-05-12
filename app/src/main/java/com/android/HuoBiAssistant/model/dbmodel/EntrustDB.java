package com.android.HuoBiAssistant.model.dbmodel;

import org.litepal.crud.DataSupport;

/**
 * 委托
 * Created by heyiyong on 2016/4/15.
 */
public class EntrustDB extends DataSupport {
    // ——————————————————常量区域——————————————————————
    public static final int TYPE_RESTRIP = 1; // 限价委托
    public static final int TYPE_MARKET = 2; // 市价委托

    public static final int STATUS_WAIT_BUY = 1; // 等待买入
    public static final int STATUS_WAIT_SELL = 2; // 等待卖出
    public static final int STATUS_SUCCESS = 3; // 已成交
    public static final int STATUS_WAIT_CANCEL = 4; // 已取消

    public static final int SUCCESS_TYPE_NOT_COMPLETE = 0; // 未成交
    public static final int SUCCESS_TYPE_WIN = 1; // 止盈成交
    public static final int SUCCESS_TYPE_LOSS = 2; // 止损成交


    // ———————————————————数据库字段—————————————————————
    private Long entrustId; // 火币API的委托ID
    private Long sell_entrustId; // 市价卖出委托ID
    private Integer type; // 委托类型（1、限价委托，2、市价委托）
    private Integer status; // 状态（1、等待买入，2、等待卖出，3、已成交，4、已取消）
    private Integer successType; // 成交类型（0：未成交，1：止盈成交，2：止损成交）

    private Double buy_avg_price; // 买入成交均价
    private Double sell_avg_price; // 卖出成交均价
    private Double win_price; // 止盈价
    private Double loss_price; // 止损价

    private Double entrust_amount; // 买入量,这里指的是比特币的数量

    private String submit_time; // 提交时间
    private String buy_time; // 买入时间（火币的服务器时间）
    private String sell_time; // 卖出时间（火币的服务器时间）

    private Double win_money; // 赚了多少钱或者亏了多少钱，正负数

    /**
     * 获取数据库唯一标识，即主键
     */
    public long getBaseObjId() {
        return super.getBaseObjId();
    }


    public Long getSell_entrustId() {
        return sell_entrustId;
    }

    public void setSell_entrustId(Long sell_entrustId) {
        this.sell_entrustId = sell_entrustId;
    }

    public Double getBuy_avg_price() {
        return buy_avg_price;
    }

    public void setBuy_avg_price(Double buy_avg_price) {
        this.buy_avg_price = buy_avg_price;
    }

    public Double getSell_avg_price() {
        return sell_avg_price;
    }

    public void setSell_avg_price(Double sell_avg_price) {
        this.sell_avg_price = sell_avg_price;
    }

    public Double getWin_money() {
        return win_money;
    }

    public void setWin_money(Double win_money) {
        this.win_money = win_money;
    }

    public Long getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Long entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getSuccessType() {
        return successType;
    }

    public void setSuccessType(Integer successType) {
        this.successType = successType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getWin_price() {
        return win_price;
    }

    public void setWin_price(Double win_price) {
        this.win_price = win_price;
    }

    public Double getLoss_price() {
        return loss_price;
    }

    public void setLoss_price(Double loss_price) {
        this.loss_price = loss_price;
    }

    public Double getEntrust_amount() {
        return entrust_amount;
    }

    public void setEntrust_amount(Double entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public String getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(String submit_time) {
        this.submit_time = submit_time;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }

    public String getSell_time() {
        return sell_time;
    }

    public void setSell_time(String sell_time) {
        this.sell_time = sell_time;
    }

    @Override
    public String toString() {
        return "EntrustDB{" +
                "entrustId=" + entrustId +
                ", sell_entrustId=" + sell_entrustId +
                ", type=" + type +
                ", status=" + status +
                ", successType=" + successType +
                ", buy_avg_price=" + buy_avg_price +
                ", sell_avg_price=" + sell_avg_price +
                ", win_price=" + win_price +
                ", loss_price=" + loss_price +
                ", entrust_amount=" + entrust_amount +
                ", submit_time='" + submit_time + '\'' +
                ", buy_time='" + buy_time + '\'' +
                ", sell_time='" + sell_time + '\'' +
                ", win_money=" + win_money +
                '}';
    }

}
