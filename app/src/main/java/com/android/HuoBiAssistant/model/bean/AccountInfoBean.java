package com.android.HuoBiAssistant.model.bean;

import android.content.Context;
import android.widget.Toast;

import com.android.HuoBiAssistant.ui.activity.MainActivity;

/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public class AccountInfoBean {

    private String account_title;
    private String account_content;
    public AccountInfoBean(String account_title,String account_content){
        this.account_content =account_content;
        this.account_title = account_title;
    }



    public String getAccount_title() {
        return account_title;
    }

    public void setAccount_title(String account_title) {
        this.account_title = account_title;
    }

    public String getAccount_content() {
        return account_content;
    }

    public void setAccount_content(String account_content) {
        this.account_content = account_content;
    }
}
