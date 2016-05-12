package com.android.HuoBiAssistant.model.bean.req;

/**
 * Created by xianling on 2016/4/15.
 */
public class BuyReqBean extends BaseRequestBean {
    public String coin_type; // 比特币类型
    public String amount; // 如果是限价买入，则是买入数量，如果是市价买入，则是买入金额
    public String price; // 买入价格
}
