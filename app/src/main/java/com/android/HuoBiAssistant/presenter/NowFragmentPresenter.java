package com.android.HuoBiAssistant.presenter;

import android.content.Context;
import android.os.Handler;

import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.model.IEntrustDetailModel;
import com.android.HuoBiAssistant.model.INowEntrust;
import com.android.HuoBiAssistant.model.IPrice;
import com.android.HuoBiAssistant.model.bean.NowEntrust;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;


import com.android.HuoBiAssistant.model.imp.EntrustDetailModel;

import com.android.HuoBiAssistant.model.imp.NowEntrustModel;
import com.android.HuoBiAssistant.model.imp.PriceModel;
import com.android.HuoBiAssistant.ui.fragment.NowFragment;
import com.android.HuoBiAssistant.ui.view.INowFragmentView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dragon丶Lz on 2016/4/9.
 */
public class NowFragmentPresenter {

    private INowFragmentView iNowFragmentView;
    //private INowEntrust iNowEntrust;
    private Context context;
    private EntrustDB entrustDB;
    //private List<EntrustDB> dataLists = new ArrayList<>();

//    private IPrice priceModel;
//    private IEntrustDetailModel entrustDetailModel;
    // private List<NowEntrust> entrustList = new ArrayList<>();
    //private List<EntrustDB> dataLists = new ArrayList<>();

    //private static  final int COMPLETED = 0;

    public NowFragmentPresenter(Context context, INowFragmentView view) {
        this.iNowFragmentView = view;
        this.context = context;

        //iNowEntrust = new NowEntrustModel(context);

//        priceModel = new PriceModel(context);
        //iNowEntrust = new NowEntrustModel(context);
        //entrustDetailModel = new EntrustDetailModel(context);

    }

    public void loadData() {
//        entrustDB=new EntrustDB();
//        entrustDB.setStatus(HuobiApp.currentEntrusts.get(0).getStatus());
//        entrustDB.setSubmit_time(entrustDB.getSubmit_time());
//        entrustDB.setBuy_avg_price(entrustDB.getBuy_avg_price());
//        entrustDB.setEntrust_amount(entrustDB.getEntrust_amount());
//        entrustDB.setLoss_price(entrustDB.getLoss_price());
//        HuobiApp.currentEntrusts.add(entrustDB);
        iNowFragmentView.showData(HuobiApp.currentEntrusts);
    }
}





