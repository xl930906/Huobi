package com.android.HuoBiAssistant.ui.view;

import com.android.HuoBiAssistant.model.bean.History_db;
import com.android.HuoBiAssistant.model.bean.NowBean;

import java.util.List;

/**
 * Created by xianling on 2016/4/12.
 */
public interface IHistoryFragmentView {
    void showData(List<History_db> list);

    void showLoadMoreSuccess();
    void showLoadMoreNoMore();

}
