package com.android.HuoBiAssistant.config;

/**
 * Created by Dragon丶Lz on 2016/3/28.
 */
public class Constants {
    public static final String ACTION_PRICE = "com.android.HuoBiAssistant.price";

    /**
     * 请求方法
     */
    public static final String BUY = "buy";
    public static final String BUY_MARKET = "buy_market";
    public static final String CANCEL_ORDER = "cancel_order";
    public static final String ACCOUNT_INFO = "get_account_info";
    public static final String NEW_DEAL_ORDERS = "get_new_deal_orders";
    public static final String ORDER_ID_BY_TRADE_ID = "get_order_id_by_trade_id";
    public static final String GET_ORDERS = "get_orders";
    public static final String ORDER_INFO = "order_info";
    public static final String SELL = "sell";
    public static final String SELL_MARKET = "sell_market";
    /**
     * 请求地址
     */
    public static final String huobi ="https://api.huobi.com";


    public static final String api = "/apiv3";

    public static final String priceApi = "/staticmarket/ticker_btc_json.js";

    /**
     * 请求头，可加可不加
     */
    public static final String APIHeader = "application/x-www-form-urlencoded";

    /**
     * 这个是测试KEY，先用来做测试
     */
    public static final String HUOBI_ACCESS_KEY = "6ab00724-a11b81cf-fe327d56-1100b";
    public static final String HUOBI_SECRET_KEY = "54f7563e-0be995e2-e349266f-44c3a";

    public static final String RES_SUCCESS = "success";
}
