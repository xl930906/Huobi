package com.android.HuoBiAssistant.util;

import android.content.Context;
import android.util.Log;


import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.callback.HttpCallbackListener;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;





/**
 * Created by Dragon丶Lz on 2016/4/2.
 */
public class OkHttpUtils {

    private static OkHttpClient singleton;

    public static OkHttpClient getInstance(Context context) {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    File cacheDir = new File(context.getCacheDir().toString());

                    singleton = new OkHttpClient();
                    singleton.setCache(new Cache(cacheDir, Runtime.getRuntime().maxMemory() / 1024 / 8));
                    singleton.setConnectTimeout(30000, TimeUnit.MILLISECONDS);
                    singleton.setReadTimeout(30000, TimeUnit.MILLISECONDS);
                }
            }
        }
        return singleton;
    }


    public static Response execute(Request request) throws IOException {
        return singleton.newCall(request).execute();
    }

    public static void enqueue(Request request, Callback responseCallback){
        singleton.newCall(request).enqueue(responseCallback);
    }

    public static void enqueue(Request request){
        singleton.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Response arg0) throws IOException {

            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }
        });
    }

    public static String getStringFromServer(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = execute(request);
        if (response.isSuccessful()) {
            String responseUrl = response.body().string();
            return responseUrl;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 用于请求当前委托的方法
     * @param method
     * @param listener
     */
    public static void OKHttpNowEntrust(final String method,final HttpCallbackListener listener){
        singleton = new OkHttpClient();

        String url_post_body = "access_key=" + Constants.HUOBI_ACCESS_KEY +"&coin_type=1"+"&created=" + HuobiUtils.getTimestamp() + "&method=" + method + "&secret_key=" + Constants.HUOBI_SECRET_KEY;
        final String signLater = HuobiUtils.MD5(url_post_body).toLowerCase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody formBody = new FormEncodingBuilder()
                            .add("method", method)
                            .add("created", String.valueOf(HuobiUtils.getTimestamp()))
                            .add("access_key", Constants.HUOBI_ACCESS_KEY)
                            .add("coin_type","1")
                            .add("sign", signLater)
                            .build();
                    Log.d("created:", String.valueOf(HuobiUtils.getTimestamp()));
                    Log.d("sign:",signLater);

                    Request request = new Request.Builder()
                            .url(Constants.huobi+Constants.api)
                            .post(formBody)
                            .build();


                    Response response = null;

                    response = singleton.newCall(request).execute();

                    if (response.isSuccessful()) {
                        listener.onSuccess(response.body().string());
                    }


                } catch (IOException e) {
                    e.printStackTrace();

                    listener.onFailure(e);
                }
            }
        }).start();
    }

    /**
     * 用于请求取消当前委托
     */

    public static void CancleEntrust(final String id, final String method,final HttpCallbackListener listener){
        singleton = new OkHttpClient();

        String url_post_body = "access_key=" + Constants.HUOBI_ACCESS_KEY +"&coin_type=1"+"&created=" + HuobiUtils.getTimestamp() +"&id="+id+ "&method=" + method + "&secret_key=" + Constants.HUOBI_SECRET_KEY;
        final String signLater = HuobiUtils.MD5(url_post_body).toLowerCase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody formBody = new FormEncodingBuilder()
                            .add("method", method)
                            .add("created", String.valueOf(HuobiUtils.getTimestamp()))
                            .add("access_key", Constants.HUOBI_ACCESS_KEY)
                            .add("coin_type","1")
                            .add("sign", signLater)
                            .add("id",id)
                            .build();


                    Request request = new Request.Builder()
                            .url(Constants.huobi+Constants.api)
                            .post(formBody)
                            .build();


                    Response response = null;

                    response = singleton.newCall(request).execute();

                    if (response.isSuccessful()) {
                        listener.onSuccess(response.body().string());
                    }


                } catch (IOException e) {
                    e.printStackTrace();

                    listener.onFailure(e);
                }
            }
        }).start();

    }
/**
 * 用于进行买入请求
 */
    public static void OKHttpBuy(final String method,final HttpCallbackListener listener) {
    singleton = new OkHttpClient();

    String url_post_body = "access_key=" + Constants.HUOBI_ACCESS_KEY + "&amount=0.001" + "&coin_type=1" + "&created=" + HuobiUtils.getTimestamp() + "&method=" + method + "&price=2600" + "&secret_key=" + Constants.HUOBI_SECRET_KEY;
    final String signLater = HuobiUtils.MD5(url_post_body).toLowerCase();
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                RequestBody formBody = new FormEncodingBuilder()
                        .add("method", method)
                        .add("created", String.valueOf(HuobiUtils.getTimestamp()))
                        .add("access_key", Constants.HUOBI_ACCESS_KEY)
                        .add("coin_type", "1")
                        .add("sign", signLater)
                        .add("amount", "0.001")
                        .add("price", "2600")
                        .build();


                Request request = new Request.Builder()
                        .url(Constants.huobi + Constants.api)
                        .post(formBody)
                        .build();


                Response response = null;

                response = singleton.newCall(request).execute();

                if (response.isSuccessful()) {
                    listener.onSuccess(response.body().string());
                }


            } catch (IOException e) {
                e.printStackTrace();

                listener.onFailure(e);
            }
        }
    }).start();
}
}
