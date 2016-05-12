package com.android.HuoBiAssistant.model.imp;



import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.model.ILeftSlideBean;
import com.android.HuoBiAssistant.model.bean.LeftSlideBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class LeftSlideBeanModel implements ILeftSlideBean {

    private List<LeftSlideBean> leftSlideBeanList = new ArrayList<>();


    @Override
    public List<LeftSlideBean> LoadData() {
        leftSlideBeanList.add(new LeftSlideBean(R.mipmap.ic_account,"个人信息"));
        leftSlideBeanList.add(new LeftSlideBean(R.mipmap.ic_setting,"个性设置"));
        leftSlideBeanList.add(new LeftSlideBean(R.mipmap.ic_about,"关于我们"));
        return leftSlideBeanList;
    }
}
