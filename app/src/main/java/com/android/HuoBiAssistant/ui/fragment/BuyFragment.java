package com.android.HuoBiAssistant.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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

    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.tv_all_entrust)
    TextView mTvEntrust; // 全部委托
    @Bind(R.id.et_buy)
    EditText mEtBuy; // 委托价格输入框

    private PriceBroadcastReceiver receiver;
    private Intent serviceIntent;
    private IEntrustModel limitPriceEntrust; // 交易model
    IEntrustDetailModel entrustDetailModel;
    private PricePresenter pricePresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new PriceBroadcastReceiver(this);
        serviceIntent = new Intent(getActivity(), PriceService.class);
        pricePresenter = new PricePresenter(getActivity(),this);
        limitPriceEntrust = new EntrustModel(getActivity());
        entrustDetailModel = new EntrustDetailModel(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 开启服务
     */
    @OnClick(R.id.btn_start_service)
    public void start() {
        getActivity().startService(serviceIntent);
    }

    /**
     * 关闭服务
     */
    @OnClick(R.id.btn_close_service)
    public void close() {
        getActivity().stopService(serviceIntent);
    }

    /**
     * 提交委托
     */
    @OnClick(R.id.btn_submit)
    public void submitEntrust() {
        String priceStr = mEtBuy.getText().toString();
        // 进行解析
        String args[] = priceStr.split(",");
        if (args.length == 4) {
            String entrustPrice = args[0];
            if ("0".equals(entrustPrice)) { // 委托价格，如果为0，则为市价交易
                pricePresenter.markPriceBuy(args);
           //     marketBuy(args);
            } else { // 限价交易
                restrictBuy(args);
            }
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
    }

    @Override
    public void showEndrustAll() {
        mTvEntrust.setText(HuobiApp.currentEntrusts.size() + "个，" + HuobiApp.currentEntrusts.toString());
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

    /**
     * 获取所有委托详情
     */
    @OnClick(R.id.btn_get_all_entrust)
    public void showAll() {

        mTvEntrust.setText(HuobiApp.currentEntrusts.size() + "个，" + HuobiApp.currentEntrusts.toString());

    }

    /**
     * 清除所有委托
     */
    @OnClick(R.id.btn_clear_all_entrust)
    public void clearAll() {
        DataSupport.deleteAll(EntrustDB.class);
        HuobiApp.currentEntrusts = Collections.synchronizedList(EntrustDB.where("status = ? or status = ?", "1", "2").find(EntrustDB.class));
        showAll();
    }





}
