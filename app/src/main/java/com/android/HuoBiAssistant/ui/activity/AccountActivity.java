package com.android.HuoBiAssistant.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.app.BaseActivity;
import com.android.HuoBiAssistant.model.bean.AccountInfoBean;
import com.android.HuoBiAssistant.presenter.AccountActivityPresenter;
import com.android.HuoBiAssistant.ui.adapter.AccountAdapter;
import com.android.HuoBiAssistant.ui.view.IAccountActivityView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountActivity extends AppCompatActivity implements IAccountActivityView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.account_rv)
    RecyclerView account_rv;

    private AccountAdapter accountAdapter;
    private AccountActivityPresenter accountActivityPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        initToolbar();
        accountActivityPresenter = new AccountActivityPresenter(this,AccountActivity.this);

        accountActivityPresenter.loadData();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("我的资产");

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    public void showData(List<AccountInfoBean> data) {
        account_rv.setLayoutManager(new LinearLayoutManager(AccountActivity.this));
        accountAdapter = new AccountAdapter(AccountActivity.this,data);
        account_rv.setAdapter(accountAdapter);
        account_rv.setItemAnimator(new DefaultItemAnimator());

    }
}
