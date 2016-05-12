package com.android.HuoBiAssistant.common;

import android.text.TextUtils;

import com.android.HuoBiAssistant.model.bean.req.BaseRequestBean;
import com.android.HuoBiAssistant.util.HuobiUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基础工具类
 * Created by heyiyong on 2016/4/15.
 */
public class BaseUtils {

    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getSignBody(BaseRequestBean baseRequestBean) {
        try {
            Map<String, String> map = convertToMap(baseRequestBean);
            return sign(map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sign(Map<String, String> map) {
        StringBuilder inputStr = new StringBuilder();
        for (Map.Entry<String, String> me : map.entrySet()) {
            inputStr.append(me.getKey()).append("=").append(me.getValue()).append("&");
        }
        String request = inputStr.substring(0, inputStr.length() - 1);
        System.out.println("request = " + request);
        String requestMD5 = HuobiUtils.MD5(request).toLowerCase();
        System.out.println("requestMD5 = " + requestMD5);
        return requestMD5;
    }

    public static Map<String, String> convertToMap(BaseRequestBean obj) throws IllegalAccessException {
        Map<String, String> map = new TreeMap<>();
        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object o = field.get(obj);
            if (o != null && !TextUtils.isEmpty(o.toString())) {
                map.put(fieldName, o.toString());
            }
        }
        return map;
    }

     /**
     * 获取当前系统时间
     * @return 格式如：2016-04-15 15:33:22
     */
    public static String getCurentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
