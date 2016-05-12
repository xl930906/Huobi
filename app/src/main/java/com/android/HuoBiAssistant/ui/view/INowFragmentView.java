package com.android.HuoBiAssistant.ui.view;

import com.android.HuoBiAssistant.model.bean.NowBean;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;

import java.util.List;

/**
 * Created by Dragon丶Lz on 2016/4/9.
 */
public interface INowFragmentView {

    void showData(List<EntrustDB> dataList);
    void onfresh();
}
