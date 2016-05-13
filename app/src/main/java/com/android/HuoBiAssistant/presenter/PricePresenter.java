package com.android.HuoBiAssistant.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.common.BaseUtils;
import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.IEntrustDetailModel;
import com.android.HuoBiAssistant.model.IEntrustModel;
import com.android.HuoBiAssistant.model.IPrice;
import com.android.HuoBiAssistant.model.bean.BTCBean;
import com.android.HuoBiAssistant.model.bean.EntrustDetail;
import com.android.HuoBiAssistant.model.bean.EntrustRes;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;
import com.android.HuoBiAssistant.model.imp.EntrustDetailModel;
import com.android.HuoBiAssistant.model.imp.EntrustModel;
import com.android.HuoBiAssistant.model.imp.PriceModel;
import com.android.HuoBiAssistant.ui.view.IBuyFragmentView;
import com.android.HuoBiAssistant.util.HuobiUtils;
import com.android.HuoBiAssistant.util.LogUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * 价格请求presenter，并实现价格相应的逻辑
 * Created by heyiyong on 2016/4/11.
 */
public class PricePresenter {
    Context mContext;
    IPrice priceModel;
    IEntrustDetailModel entrustDetailModel;
    IEntrustModel entrustModel;
    IBuyFragmentView iBuyFragmentView;
    private static final boolean RequestPriceLog = false;
    private static final boolean MarketBuyLog = false;
    private static double now_price = 0;
    public int TYPE = TYPE_PRICE;
    public static final int TYPE_PRICE = 2000;//价格
    public static final int TYPE_VOL = 2003;//数量
    public static final int TYPE_STOP_EARNING = 2001;//止盈
    public static final int TYPE_STOP_LOSS = 2002;//止损
    public static final int TYPE_BUY_PRICE = 2004;//止损

    private Handler handler = new Handler();

    public PricePresenter(Context context,IBuyFragmentView view) {
        this.iBuyFragmentView = view;
        priceModel = new PriceModel(context);
        entrustDetailModel = new EntrustDetailModel(context);
        entrustModel = new EntrustModel(context);
        this.mContext = context;
    }

    public PricePresenter(Context context) {

        priceModel = new PriceModel(context);
        entrustDetailModel = new EntrustDetailModel(context);
        entrustModel = new EntrustModel(context);
        this.mContext = context;
    }

    /**
     * 不断的获取当前的价格,并且做判断
     */
    public void requestPrice() {

        LogUtils.d("-----------------------请求价格------------------------", RequestPriceLog);
        priceModel.requestPrice(new Callback<BTCBean>() {
            @Override
            public void success(BTCBean btcBean, Response response) {
                now_price = btcBean.getTicker().getLast();
                // 1、判断当前行情是否需要止盈或者止损
                monitor(btcBean);
                LogUtils.d("我已经执行到了requestPrice的success", RequestPriceLog);

                // 2、发送广播，当需要行情的界面刷新当前的行情
                sendBroadcast(btcBean);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    /**
     * 发送广播
     */
    private void sendBroadcast(BTCBean btcBean) {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_PRICE);
        intent.putExtra("price", btcBean);
        mContext.sendBroadcast(intent);
    }

    /**
     * 监控当前价格，并作出相应操作
     */
    private void monitor(BTCBean btcBean) {
        LogUtils.d("我已经执行到了monitor",RequestPriceLog);
        System.out.println("monitor btcBean = " + btcBean);
        double currentPrice = btcBean.getTicker().getLast();
        for (EntrustDB entrust : HuobiApp.currentEntrusts) { // 遍历所有委托，判断委托价和当前价的关系
            LogUtils.d("我已经执行到了monitor的for循环",RequestPriceLog);
            if (entrust.getStatus() == EntrustDB.STATUS_WAIT_BUY) { // 当前状态是：等待买入的
                LogUtils.d("我已经执行到了monitor的等待买入",RequestPriceLog);
                handleBuy(currentPrice, entrust);
            } else if (entrust.getStatus() == EntrustDB.STATUS_WAIT_SELL) { // 当前状态是：等待卖出的
                LogUtils.d("我已经执行到了monitor的等待卖出部分",RequestPriceLog);
                handleSell(currentPrice, entrust);
            }
        }
        btcBean.getTicker().getLast();
    }

    /**
     * 处理等待买入的委托
     */
    private void handleBuy(double currentPrice, final EntrustDB entrust) {
        if (entrust.getBuy_avg_price() <= currentPrice) { // 当前价大于委托价，TODO 查询买入是否成交（查看当前委托的进度）
            getEntrustDetail(entrust, EntrustDB.STATUS_WAIT_SELL, EntrustDB.SUCCESS_TYPE_NOT_COMPLETE);
        }
    }

    /**
     * 处理等待卖出的委托
     */
    private void handleSell(double currentPrice, final EntrustDB entrust) {
            if (entrust.getEntrust_amount()!= null) {
                if (entrust.getLoss_price() >= currentPrice) { // 当前价小于止损价，TODO
                    if (entrust.getEntrust_amount() <= 0.001) {

                    } else {
                        LogUtils.d("我已经执行到了handleSell的止损 ", RequestPriceLog);

                        entrustModel.marketPriceSell(entrust.getEntrust_amount() + "", new Callback<EntrustRes>() {
                            @Override
                            public void success(EntrustRes entrustRes, Response response) {
                                if (Constants.RES_SUCCESS.equals(entrustRes.getResult())) {

                                    HuobiUtils.ShowNotification(mContext, "完成止损服务", "您有订单完成了止损服务");
                                    Toast.makeText(mContext, "我已经止损卖出", Toast.LENGTH_SHORT).show();
                                    getEntrustDetail(entrust, EntrustDB.STATUS_SUCCESS, EntrustDB.SUCCESS_TYPE_LOSS);
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                error.printStackTrace();
                            }
                        });
                    }
                }else if (entrust.getWin_price() <= currentPrice) {
                    if (entrust.getEntrust_amount() <= 0.001) {

                    } else {
                        LogUtils.d("我已经执行到了handleSell的止盈利", RequestPriceLog);
                        // 当前价大于止盈价，TODO 该止盈（查看当前委托的进度）
                        entrustModel.marketPriceSell(entrust.getEntrust_amount() + "", new Callback<EntrustRes>() {
                            @Override
                            public void success(EntrustRes entrustRes, Response response) {
                                if (Constants.RES_SUCCESS.equals(entrustRes.getResult())) {
                                    HuobiUtils.ShowNotification(mContext, "完成止盈服务", "您有订单完成了止盈服务");
                                    Toast.makeText(mContext, "我已经止盈卖出", Toast.LENGTH_SHORT).show();
                                    getEntrustDetail(entrust, EntrustDB.STATUS_SUCCESS, EntrustDB.SUCCESS_TYPE_WIN);
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                error.printStackTrace();
                            }
                        });
                    }
                }
            }
    }

    /**
     * 查看委托详情。并对其成交情况，设置该委托的下一个阶段的状态。
     * @param entrust 委托对象
     * @param status 下一个状态
     * @param successType 成功状态
     */
    private void getEntrustDetail(final EntrustDB entrust, final int status, final int successType) {
        entrustDetailModel.getDetail(entrust.getEntrustId().toString(), new Callback<EntrustDetail>() {
            @Override
            public void success(EntrustDetail entrustDetail, Response response) {
                if (2 == entrustDetail.getStatus()) { // 已成交
                    System.out.println("该订单已成交 status = " + status);
                    entrust.setStatus(status);
                    entrust.setSuccessType(successType); // 设置为等待卖出
                    entrust.save(); // 改变了状态还要保存到数据库
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }



    /**
     * 市价买入,买完后需要两秒后查询买入的比特币的数量
     * @param strArr 这个界面来了需要修改
     */

    public void markPriceBuy(){
        LogUtils.d("----------------------开始市价买入-------------------------", MarketBuyLog);
        //double amount = Double.parseDouble(strArr[3])/now_price;
        double amount = Double.parseDouble(iBuyFragmentView.get_buy_price())/now_price;
       if(HuobiUtils.DealDouble(amount,4)<0.001){
           Toast.makeText(mContext,"必须购买超过0.001个比特币才能提供止盈止损服务",Toast.LENGTH_SHORT).show();
       }else {

           entrustModel.marketPriceBuy(iBuyFragmentView.get_buy_price(), new Callback<EntrustRes>() {
               @Override
               public void success(final EntrustRes marketPriceBuy, Response response) {
                   LogUtils.d("买入成功,正在计算购买的数量", MarketBuyLog);
                   if (Constants.RES_SUCCESS.equals(marketPriceBuy.getResult())) {
                       saveEntrust(marketPriceBuy, EntrustDB.TYPE_MARKET, EntrustDB.STATUS_WAIT_SELL);
                   }
               }

               @Override
               public void failure(RetrofitError error) {
                   error.printStackTrace();
               }
           });
       }
    }


    /**
     * 保存委托到数据库和缓存
     *
     * @param entrustRes 火币API 委托结果
     * @param type       1：限价， 2：市价
     * @param strArr     价格string
     * @param status     保存的状态，等待买入还是等待卖出
     */
    private void saveEntrust(EntrustRes entrustRes, int type, int status) {
        final EntrustDB entrustDB = new EntrustDB();

        entrustDB.setEntrustId(Long.parseLong(entrustRes.getId()));
        entrustDB.setSubmit_time(BaseUtils.getCurentTime()); // 设置一下本地提交委托时间
        entrustDB.setType(type); // 市价或者限价交易
        // 设置委托的止盈止损价
        entrustDB.setWin_price(Double.parseDouble(iBuyFragmentView.get_stop_earning())); // 设置止盈价
        entrustDB.setLoss_price(Double.parseDouble(iBuyFragmentView.get_stop_loss())); // 设置止损价
        // 设置委托的当前状态
        entrustDB.setStatus(status);
        entrustDB.setEntrust_amount(Double.parseDouble(iBuyFragmentView.get_buy_price()));
        entrustDB.setSuccessType(EntrustDB.SUCCESS_TYPE_NOT_COMPLETE);
        HuobiApp.currentEntrusts.add(entrustDB);
        // 保存到数据库
        entrustDB.save();

        if (type == EntrustDB.TYPE_MARKET) { // 市价交易还需获取成交均价，否则不知道买入价是多少
            // 2秒后获取详情，得出成交均价（为什么要延迟获取，因为不是下单后马上就能获取到的）
            handler.postDelayed(new GetDetailRunnable(entrustDB, Long.parseLong(entrustRes.getId())), 2000);
        }

    }


    class GetDetailRunnable implements Runnable {

        private EntrustDB entrustDB;
        private long entrustId;

        public GetDetailRunnable(EntrustDB entrustDB, long entrustId) {
            this.entrustDB = entrustDB;
            this.entrustId = entrustId;
        }

        @Override
        public void run() {
            // 查询成交均价
            entrustDetailModel.getDetail(entrustId+"", new retrofit.Callback<EntrustDetail>() {
                @Override
                public void success(EntrustDetail entrustDetail, Response response) {
                    System.out.println("entrustDetail = " + entrustDetail);
                    if (entrustDetail.getStatus() == 2) { // 成交
                        entrustDB.setBuy_avg_price(entrustDetail.getProcessed_price());
                        entrustDB.setBuy_time(BaseUtils.getCurentTime()); // 设置一下买入时间
                        double amount = entrustDetail.getTotal()/entrustDetail.getProcessed_price();
                        amount = HuobiUtils.DealDouble(amount,4);
                        LogUtils.d("我获取到了amount :"+amount, MarketBuyLog);
                        entrustDB.setEntrust_amount(amount);
                        entrustDB.update(entrustDB.getBaseObjId());
                        // 再次刷新一下

                        handler.removeCallbacks(GetDetailRunnable.this);
                    } else { // 未成交，则2秒后继续获取，不能放弃
                        handler.postDelayed(GetDetailRunnable.this, 2000);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }
    }
    public void bt_market() {
        iBuyFragmentView.show_ll_market(View.VISIBLE);
        iBuyFragmentView.show_ll_limit(View.GONE);
        iBuyFragmentView.market_back(R.drawable.btn_click);
        iBuyFragmentView.limit_back(R.drawable.btn_not_click);
    }

    public void bt_limit() {
        iBuyFragmentView.show_ll_market(View.GONE);
        iBuyFragmentView.show_ll_limit(View.VISIBLE);
        iBuyFragmentView.market_back(R.drawable.btn_not_click);
        iBuyFragmentView.limit_back(R.drawable.btn_click);
    }

    public void bt_clear() {
        clear();
    }

    private void clear() {
        switch (TYPE) {
            case TYPE_PRICE:
                iBuyFragmentView.et_price("");
                break;
            case TYPE_STOP_EARNING:
                iBuyFragmentView.et_stop_earning("");
                break;
            case TYPE_STOP_LOSS:
                iBuyFragmentView.et_stop_loss("");
                break;
            case TYPE_VOL:
                iBuyFragmentView.et_vol("");
                break;
            case TYPE_BUY_PRICE:
                iBuyFragmentView.et_buy_price("");
                break;

        }
    }

    public void bt_one() {
        add(1);
    }

    private void add(int s) {
        switch (TYPE) {
            case TYPE_PRICE:
                if (!(iBuyFragmentView.get_price().length() == 0 && s == 0))
                    iBuyFragmentView.et_price(iBuyFragmentView.get_price()+s);
                break;
            case TYPE_STOP_EARNING:
                if (!(iBuyFragmentView.get_stop_earning().length() == 0 && s == 0))
                    iBuyFragmentView.et_stop_earning(iBuyFragmentView.get_stop_earning()+s);
                break;
            case TYPE_STOP_LOSS:
                if (!(iBuyFragmentView.get_stop_loss().length() == 0 && s == 0))
                    iBuyFragmentView.et_stop_loss(iBuyFragmentView.get_stop_loss()+s);
                break;
            case TYPE_VOL:
                if (!(iBuyFragmentView.get_vol().length() == 0 && s == 0))
                    iBuyFragmentView.et_vol(iBuyFragmentView.get_vol()+s);
                break;
            case TYPE_BUY_PRICE:
                if(!(iBuyFragmentView.get_buy_price().length()==0 && s==0))
                    iBuyFragmentView.et_buy_price(iBuyFragmentView.get_buy_price()+s);
                break;
        }
    }

    public void bt_zero() {
        add(0);
    }

    public void bt_two() {
        add(2);
    }

    public void bt_three() {
        add(3);
    }

    public void bt_four() {
        add(4);
    }

    public void bt_five() {
        add(5);
    }

    public void bt_six() {
        add(6);
    }

    public void bt_seven() {
        add(7);
    }

    public void bt_eight() {
        add(8);
    }

    public void bt_nine() {
        add(9);
    }

    public void bt_vol() {

    }

    public void et_stop_loss() {
        iBuyFragmentView.stop_loss_focus(R.drawable.show_data);
        iBuyFragmentView.stop_earning_focus(R.drawable.not_show_data);
        iBuyFragmentView.vol_focus(R.drawable.btn_not_click);
        iBuyFragmentView.price_focus(R.drawable.not_show_data);
        iBuyFragmentView.buy_price_focus(R.drawable.not_show_data);
        TYPE = TYPE_STOP_LOSS;
    }

    public void et_vol() {
        iBuyFragmentView.stop_loss_focus(R.drawable.not_show_data);
        iBuyFragmentView.stop_earning_focus(R.drawable.not_show_data);
        iBuyFragmentView.vol_focus(R.drawable.show_data);
        iBuyFragmentView.price_focus(R.drawable.not_show_data);
        TYPE = TYPE_VOL;
    }
    public void et_buy_price() {
        iBuyFragmentView.stop_loss_focus(R.drawable.not_show_data);
        iBuyFragmentView.stop_earning_focus(R.drawable.not_show_data);
        iBuyFragmentView.buy_price_focus(R.drawable.show_data);

        TYPE = TYPE_BUY_PRICE;
    }

    public void et_stop_earning() {
        iBuyFragmentView.stop_loss_focus(R.drawable.not_show_data);
        iBuyFragmentView.stop_earning_focus(R.drawable.show_data);
        iBuyFragmentView.vol_focus(R.drawable.btn_not_click);
        iBuyFragmentView.price_focus(R.drawable.not_show_data);
        iBuyFragmentView.buy_price_focus(R.drawable.not_show_data);
        TYPE = TYPE_STOP_EARNING;
    }

    public void et_price() {
        iBuyFragmentView.stop_loss_focus(R.drawable.not_show_data);
        iBuyFragmentView.stop_earning_focus(R.drawable.not_show_data);
        iBuyFragmentView.vol_focus(R.drawable.btn_not_click);
        iBuyFragmentView.price_focus(R.drawable.show_data);
        TYPE = TYPE_PRICE;
    }

    public void bt_delete() {
        switch (TYPE) {
            case TYPE_PRICE:
                if (iBuyFragmentView.get_price().length() != 0)
                    iBuyFragmentView.et_price(iBuyFragmentView.get_price().substring(0, iBuyFragmentView.get_price().length() - 1));
                break;
            case TYPE_STOP_EARNING:
                if (iBuyFragmentView.get_stop_earning().length() != 0)
                    iBuyFragmentView.et_stop_earning(iBuyFragmentView.get_stop_earning().substring(0, iBuyFragmentView.get_stop_earning().length() - 1));
                break;
            case TYPE_STOP_LOSS:
                if (iBuyFragmentView.get_stop_loss().length() != 0)
                    iBuyFragmentView.et_stop_loss(iBuyFragmentView.get_stop_loss().substring(0, iBuyFragmentView.get_stop_loss().length() - 1));
                break;
            case TYPE_VOL:
                if (iBuyFragmentView.get_vol().length() != 0)
                    iBuyFragmentView.et_vol(iBuyFragmentView.get_vol().substring(0, iBuyFragmentView.get_vol().length() - 1));
                break;
        }

    }
}
