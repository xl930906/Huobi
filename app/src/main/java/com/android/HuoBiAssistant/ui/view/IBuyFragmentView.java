package com.android.HuoBiAssistant.ui.view;

/**
 * Created by Dragon丶Lz on 2016/4/17.
 */
public interface IBuyFragmentView {


    void show_ll_limit(int visibility);

    void show_ll_market(int visibility);

    void limit_back(int btn_not_click);

    void market_back(int btn_click);

    void et_price(String s);

    void et_stop_earning(String s);

    void et_stop_loss(String s);

    void et_vol(String s);

    void et_buy_price(String s);

    String get_price();

    String get_stop_earning();

    String get_vol();

    String get_stop_loss();

    String get_buy_price();

    void price_focus(int btn_not_click);

    void stop_loss_focus(int btn_click);

    void stop_earning_focus(int btn_not_click);

    void vol_focus(int btn_not_click);

    void buy_price_focus(int btn_not_click);
}
