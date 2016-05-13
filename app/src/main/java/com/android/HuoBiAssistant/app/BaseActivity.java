package com.android.HuoBiAssistant.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;


import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.service.PriceService;
import com.android.HuoBiAssistant.util.ActivityCollectorUtils;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class BaseActivity extends AppCompatActivity {
    private Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceIntent = new Intent(this, PriceService.class);
        ActivityCollectorUtils.addActivity(this);
    }


    public void initToolbar(Toolbar mToolBar, String activityName){

            mToolBar.setTitle(activityName);

            initToolbar(mToolBar);

    }

    private void initToolbar(Toolbar mToolBar) {
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        mToolBar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_refresh:
                    //System.out.println("666");
                    //做检查更新操作等等
                    startService(serviceIntent);
                    break;
                case R.id.action_refresh2:
                    stopService(serviceIntent);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtils.removeActivity(this);
    }
}
