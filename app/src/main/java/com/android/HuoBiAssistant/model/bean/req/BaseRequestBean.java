package com.android.HuoBiAssistant.model.bean.req;

import com.android.HuoBiAssistant.config.Constants;

/**
 * 请求的参数，用于封装sign
 * Created by heyiyong on 2016/4/15.
 */
public class BaseRequestBean {
    public long created;
    public String method;
    public String access_key  = Constants.HUOBI_ACCESS_KEY;
    public String secret_key = Constants.HUOBI_SECRET_KEY;
}
