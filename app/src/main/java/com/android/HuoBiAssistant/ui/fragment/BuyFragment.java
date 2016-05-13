package com.android.HuoBiAssistant.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.common.BaseUtils;
import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.IEntrustDetailModel;
import com.android.HuoBiAssistant.model.IEntrustModel;
import com.android.HuoBiAssistant.model.bean.BTCBean;
import com.android.HuoBiAssistant.model.bean.EntrustDetail;
import com.android.HuoBiAssistant.model.bean.EntrustRes;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;
import com.android.HuoBiAssistant.model.imp.EntrustDetailModel;
import com.android.HuoBiAssistant.model.imp.EntrustModel;
import com.android.HuoBiAssistant.presenter.PricePresenter;
import com.android.HuoBiAssistant.receiver.PriceBroadcastReceiver;
import com.android.HuoBiAssistant.service.PriceService;
import com.android.HuoBiAssistant.ui.view.IBuyFragmentView;

import org.litepal.crud.DataSupport;

import java.text.DecimalFormat;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BuyFragment extends Fragment implements PriceBroadcastReceiver.PriceListener ,IBuyFragmentView{
    private int progress = 0;
    @Bind(R.id.et_price)//价格
            EditText et_price;
    @Bind(R.id.et_vol)//数量
            EditText et_vol;
    @Bind(R.id.et_buy_price)//市价买入金额
            EditText et_buy_price;
    @Bind(R.id.tv_limit_amt)
    TextView tv_limit_amt;//交易额
    @Bind(R.id.et_stop_earning)
    EditText et_stop_earning;//止盈
    @Bind(R.id.et_stop_loss)
    EditText et_stop_loss;//止损
    @Bind(R.id.tv_percent)
    TextView tv_percent;


    /*键盘*/
    @Bind(R.id.sb_change_percent)
    SeekBar sb_change_percent;

    @OnClick(R.id.bt_delete)
    void bt_delete() {
        presenter.bt_delete();
    }
    @OnClick(R.id.et_buy_price)
    void et_buy_price(){
        presenter.et_buy_price();
    }
    @OnClick(R.id.et_price)
    void et_price() {
        presenter.et_price();
    }

    @OnClick(R.id.et_stop_earning)
    void et_stop_earning() {
        presenter.et_stop_earning();
    }

    @OnClick(R.id.et_stop_loss)
    void et_stop_loss() {
        presenter.et_stop_loss();
    }

    @OnClick(R.id.et_vol)
    void et_vol() {
        presenter.et_vol();
    }

    @OnClick(R.id.bt_clear)
    void bt_clear() {
        presenter.bt_clear();
    }

    @OnClick(R.id.bt_one)
    void bt_one() {
        presenter.bt_one();
    }

    @OnClick(R.id.bt_two)
    void bt_two() {
        presenter.bt_two();
    }

    @OnClick(R.id.bt_three)
    void bt_three() {
        presenter.bt_three();
    }

    @OnClick(R.id.bt_four)
    void bt_four() {
        presenter.bt_four();
    }

    @OnClick(R.id.bt_five)
    void bt_five() {
        presenter.bt_five();
    }

    @OnClick(R.id.bt_six)
    void bt_six() {
        presenter.bt_six();
    }

    @OnClick(R.id.bt_seven)
    void bt_seven() {
        presenter.bt_seven();
    }

    @OnClick(R.id.bt_eight)
    void bt_eight() {
        presenter.bt_eight();
    }

    @OnClick(R.id.bt_nine)
    void bt_nine() {
        presenter.bt_nine();
    }

    @OnClick(R.id.bt_vol)
    void bt_vol() {
        presenter.bt_vol();
    }

    @OnClick(R.id.bt_zero)
    void bt_zero() {
        presenter.bt_zero();
    }

    @Bind(R.id.tv_percent_up)
    TextView tv_percent_up;
    @Bind(R.id.tv_percent_down)
    TextView tv_percent_down;
    @Bind(R.id.ll_market)
    LinearLayout ll_market;
    //市价
    @Bind(R.id.ll_limit)
    LinearLayout ll_limit;
    //限价
    @Bind(R.id.bt_limit)
    Button bt_limit;
//    //点击买入
//    @OnClick(R.id.bt_buy)
//    void bt_buy(){
//        presenter.markPriceBuy();
//    }
    @OnClick(R.id.bt_limit)
    void bt_limit() {
        presenter.bt_limit();
    }

    //市价
    @Bind(R.id.bt_market)
    Button bt_market;

    @OnClick(R.id.bt_market)
    void bt_market() {
        presenter.bt_market();
    }
    @Bind(R.id.show_time_data)
    TextView mTvPrice;
    @Bind(R.id.iv_arrows)
    ImageView iv_arrows;

    private PriceBroadcastReceiver receiver;
    //private Intent serviceIntent;
    private IEntrustModel limitPriceEntrust; // 交易model
    IEntrustDetailModel entrustDetailModel;
    private PricePresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new PriceBroadcastReceiver(this);
        //serviceIntent = new Intent(getActivity(), PriceService.class);
        presenter = new PricePresenter(getActivity(),this);
        limitPriceEntrust = new EntrustModel(getActivity());
        entrustDetailModel = new EntrustDetailModel(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_new, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }
//
//    /**
//     * 开启服务
//     */
//    @OnClick(R.id.btn_start_service)
//    public void start() {
//        getActivity().startService(serviceIntent);
//    }
//
//    /**
//     * 关闭服务
//     */
//    @OnClick(R.id.btn_close_service)
//    public void close() {
//        getActivity().stopService(serviceIntent);
//    }

    /**
     * 提交委托
     */
    @OnClick(R.id.bt_buy)
    public void submitEntrust() {
//        String priceStr = mEtBuy.getText().toString();
//        // 进行解析
//        String args[] = priceStr.split(",");
//        if (args.length == 4) {
//            String entrustPrice = args[0];
//            if ("0".equals(entrustPrice)) { // 委托价格，如果为0，则为市价交易
//                presenter.markPriceBuy(args);
//           //     marketBuy(args);
//            } else { // 限价交易
//                restrictBuy(args);
//            }
//        }
        if(!(this.get_buy_price().equals(""))
                && !(this.get_stop_earning().equals(""))
        &&!(this.get_stop_loss().equals(""))){
            presenter.markPriceBuy();
        }else {
            Toast.makeText(getActivity(),"交易失败,为输入完整信息",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 限价买入
     */
    private void restrictBuy(final String[] strArr) {
        String amountStr = strArr[3]; // 用户想买的金额
        double money = Double.parseDouble(amountStr);
        double currentPrice = getCurrentPrice();
        DecimalFormat decimalFormat = new DecimalFormat("##0.0000");
        String amount = decimalFormat.format(money / currentPrice);
        System.out.println("限价买入：amount = " + amount);
        limitPriceEntrust.limitPriceBuy(amount, strArr[0], new Callback<EntrustRes>() {
            @Override
            public void success(EntrustRes entrustRes, Response response) {
                if (Constants.RES_SUCCESS.equals(entrustRes.getResult())) { // 成功


                    //       saveEntrust(entrustRes, EntrustDB.TYPE_RESTRIP, strArr, EntrustDB.STATUS_WAIT_BUY);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    /**
     * 市价交易（市价交易由于不知道成交价格是多少，所以在提交委托之后，仍需根据委托id获取到成交均价是多少）

    private void marketBuy(final String[] strArr) {
        limitPriceEntrust.marketPriceBuy(strArr[3], new Callback<EntrustRes>() {
            @Override
            public void success(EntrustRes marketPriceBuy, Response response) {


                if (Constants.RES_SUCCESS.equals(marketPriceBuy.getResult())) {

               //     saveEntrust(marketPriceBuy, EntrustDB.TYPE_MARKET, strArr, EntrustDB.STATUS_WAIT_SELL);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
     */


    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Constants.ACTION_PRICE);
        getActivity().registerReceiver(receiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onPriceChange(BTCBean btcBean) {
        mTvPrice.setText(String.valueOf(btcBean.getTicker().getLast()));
        Double lastprice=btcBean.getTicker().getLast();
        Double buyprice=btcBean.getTicker().getBuy();
        if(lastprice>buyprice){
            iv_arrows.setImageResource(R.mipmap.arrows_read);
       }else {
           iv_arrows.setImageResource(R.mipmap.arrow_green2);
       }

        }



    /**
     * 获取当前的市场价格
     *
     * @return -1：代表当前没有数据
     */
    private double getCurrentPrice() {
        String price = mTvPrice.getText().toString();
        double parseDouble;
        try {
            parseDouble = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseDouble = -1;
        }
        return parseDouble;
    }





    private void initView() {
        sb_change_percent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress1, boolean fromUser) {
                progress = progress1;
                tv_percent.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tv_percent_down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                progress--;
                sb_change_percent.setProgress(progress);
                return true;
            }
        });
        tv_percent_up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress++;
                sb_change_percent.setProgress(progress);
                return true;
            }
        });
        et_price.setFocusable(false);
        et_stop_loss.setFocusable(false);
        et_vol.setFocusable(false);
        et_stop_earning.setFocusable(false);
        et_buy_price.setFocusable(false);
    }

    @Override
    public void show_ll_limit(int visibility) {
        ll_limit.setVisibility(visibility);
    }

    @Override
    public void show_ll_market(int visibility) {
        ll_market.setVisibility(visibility);
    }

    @Override
    public void limit_back(@DrawableRes int resid) {
        bt_limit.setBackgroundResource(resid);
    }

    @Override
    public void market_back(@DrawableRes int resid) {
        bt_market.setBackgroundResource(resid);
    }

    @Override
    public void et_price(String s) {
        et_price.setText("");
        et_price.setText(et_price.getText().toString() + s);
    }


    @Override
    public void et_stop_earning(String s) {
        et_stop_earning.setText("");
        et_stop_earning.setText(s);
    }

    @Override
    public void et_stop_loss(String s) {
        et_stop_loss.setText("");
        et_stop_loss.setText(s);
    }

    @Override
    public void et_vol(String s) {
        et_vol.setText("");
        et_vol.setText(s);
    }

    @Override
    public void et_buy_price(String s) {
        et_buy_price.setText("");
        et_buy_price.setText(s);
    }

    @Override
    public String get_price() {
        return et_price.getText().toString();
    }

    @Override
    public String get_stop_earning() {
        return et_stop_earning.getText().toString();
    }

    @Override
    public String get_vol()  {
        return et_vol.getText().toString();
    }

    @Override
    public String get_stop_loss() {
        return et_stop_loss.getText().toString();
    }

    @Override
    public String get_buy_price() {
        return et_buy_price.getText().toString();
    }

    @Override
    public void price_focus(@DrawableRes int resId) {
        et_price.setBackgroundResource(resId);
    }

    @Override
    public void stop_loss_focus(@DrawableRes int resId) {
        et_stop_loss.setBackgroundResource(resId);
    }

    @Override
    public void stop_earning_focus(@DrawableRes int resId) {
        et_stop_earning.setBackgroundResource(resId);
    }

    @Override
    public void vol_focus(@DrawableRes int resId) {
        et_vol.setBackgroundResource(resId);
    }

    @Override
    public void buy_price_focus(@DrawableRes int resId) {
        et_buy_price.setBackgroundResource(resId);
    }


//    @Override
//    public void onPriceChange(BTCBean btcBean) {
//
//    }


}
