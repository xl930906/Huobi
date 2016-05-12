package com.android.HuoBiAssistant.model.imp;

import android.content.Context;

import com.android.HuoBiAssistant.app.BaseModel;
import com.android.HuoBiAssistant.model.IAccountInfo;
import com.android.HuoBiAssistant.model.bean.AccountInfo;
import com.android.HuoBiAssistant.model.bean.AccountInfoBean;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public class AccountInfoModel extends BaseModel implements IAccountInfo{

    private List<String> dataList = new ArrayList<>();
    public AccountInfoModel(Context context) {
        super(context);
    }

    @Override
    public void loadAccountinfoData(String method, long created, String access_key, String sign, Callback<AccountInfo> callback) {
        getExperApi().get_account_info(method,created,access_key,sign,callback);
    }

    @Override
    public List<String> loadTitleData() {
        dataList.add("总资产:");
        dataList.add("净资产:");
        dataList.add("可用人民币:");
        dataList.add("可用比特币:");
        dataList.add("冻结人民币:");
        dataList.add("冻结比特币:");
        dataList.add("申请人民币数量:");
        dataList.add("申请比特币数量：");

        return dataList;
    }


}
