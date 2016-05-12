package com.android.HuoBiAssistant.presenter;

import com.android.HuoBiAssistant.model.ILeftSlideBean;
import com.android.HuoBiAssistant.model.bean.LeftSlideBean;
import com.android.HuoBiAssistant.model.imp.LeftSlideBeanModel;
import com.android.HuoBiAssistant.ui.view.IMainActivityView;

import java.util.List;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class MainActivityPresenter {

    private IMainActivityView iMainActivityView;
    private ILeftSlideBean iLeftSlideBean;

    public MainActivityPresenter(IMainActivityView v){
        this.iMainActivityView = v;
        iLeftSlideBean = new LeftSlideBeanModel();
    }

    public  void showFragment(int index){
        iMainActivityView.setTabSelection(index);
    }

    public List<LeftSlideBean> loadSlideData(){
        return iLeftSlideBean.LoadData();
    }

}
