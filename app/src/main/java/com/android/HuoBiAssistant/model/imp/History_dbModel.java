package com.android.HuoBiAssistant.model.imp;

import com.android.HuoBiAssistant.model.IHistory_db;
import com.android.HuoBiAssistant.model.bean.History_db;
import com.android.HuoBiAssistant.model.bean.LeftSlideBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianling on 2016/4/12.
 */
public class History_dbModel implements IHistory_db{
    private List<History_db> list = new ArrayList<>();
    @Override
    public void LoadHistoryDataFromDB() {

    }
}
