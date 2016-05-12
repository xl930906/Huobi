package com.android.HuoBiAssistant.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.HuoBiAssistant.presenter.PricePresenter;

/**
 * 价格Service
 * Created by heyiyong on 2016/4/11.
 */
public class PriceService extends Service {
    /**
     * 当前一秒钟刷新一次行情
     */
    private static final long REQUEST_INTERVAL = 2000;
    private PricePresenter pricePresenter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            pricePresenter.requestPrice();
            handler.postDelayed(runnable, REQUEST_INTERVAL);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        pricePresenter = new PricePresenter(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 开始请求行情
        handler.post(runnable);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
