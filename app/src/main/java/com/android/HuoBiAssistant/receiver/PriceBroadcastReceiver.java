package com.android.HuoBiAssistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.bean.BTCBean;

/**
 * Created by heyiyong on 2016/4/11.
 */
public class PriceBroadcastReceiver extends BroadcastReceiver {
    private PriceListener listener;

    public PriceBroadcastReceiver() {
    }

    public PriceBroadcastReceiver(PriceListener listener) {
        this.listener = listener;
    }

    public interface PriceListener {
        public void onPriceChange(BTCBean btcBean);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Constants.ACTION_PRICE.equals(intent.getAction())) {
            BTCBean btcBean = (BTCBean) intent.getSerializableExtra("price");
            if (listener != null) {
                listener.onPriceChange(btcBean);
            }
        }
    }
}
