package com.android.HuoBiAssistant.presenter;

import android.app.ActionBar;
import android.content.Context;
import android.os.Handler;

import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.model.IHistory_db;
import com.android.HuoBiAssistant.model.bean.History_db;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;
import com.android.HuoBiAssistant.model.imp.History_dbModel;
import com.android.HuoBiAssistant.ui.adapter.HistoryFragmentAdapter;
import com.android.HuoBiAssistant.ui.fragment.HistoryFragment;
import com.android.HuoBiAssistant.ui.view.IHistoryFragmentView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xianling on 2016/4/12.
 */
public class HistoryFragmentPresenter {

    private IHistory_db ihistory_db;
    private IHistoryFragmentView iHistoryFragmentView;
    private Context context;
    private List<History_db> dataLists = new ArrayList<>();


    public HistoryFragmentPresenter(Context context, IHistoryFragmentView v) {
        this.iHistoryFragmentView = v;
        this.context = context;
        ihistory_db = new History_dbModel();
    }

    public void loadData() {

        iHistoryFragmentView.showData(dataLists);
    }


    public void loadMore() {
        final HistoryFragmentAdapter hf = new HistoryFragmentAdapter(context);
        int sum = hf.getItemCount();
        if (sum != EntrustDB.where("status = ? ", "3").find(EntrustDB.class).size() + 1) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hf.addItem(EntrustDB.where("status = ? ", "3").
                            limit(3).offset(new HistoryFragmentAdapter(context).getItemCount() - 1).find(EntrustDB.class));
                    iHistoryFragmentView.showLoadMoreSuccess();
                }
            }, 1000);


        } else {
            System.out.println("2");
            iHistoryFragmentView.showLoadMoreNoMore();

        }

    }


}
