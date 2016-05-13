package com.android.HuoBiAssistant.ui.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.app.BaseActivity;
import com.android.HuoBiAssistant.presenter.MainActivityPresenter;
import com.android.HuoBiAssistant.service.PriceService;
import com.android.HuoBiAssistant.ui.adapter.LeftSlideAdapter;
import com.android.HuoBiAssistant.ui.fragment.BuyFragment;
import com.android.HuoBiAssistant.ui.fragment.HistoryFragment;
import com.android.HuoBiAssistant.ui.fragment.NowFragment;
import com.android.HuoBiAssistant.ui.view.IMainActivityView;
import com.android.HuoBiAssistant.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class MainActivity extends BaseActivity implements IMainActivityView, View.OnClickListener, AdapterView.OnItemClickListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Bind(R.id.buy_Layout)
    RelativeLayout buy_Layout;
    @Bind(R.id.buy_image)
    ImageView buy_image;
    @Bind(R.id.buy_text)
    TextView buy_text;
//
//    @Bind(R.id.sale_Layout)
//    RelativeLayout sale_Layout;
//    @Bind(R.id.sale_image)
//    ImageView sale_image;
//    @Bind(R.id.sale_text)
//    TextView sale_text;

    @Bind(R.id.now_Layout)
    RelativeLayout now_Layout;
    @Bind(R.id.now_image)
    ImageView now_image;
    @Bind(R.id.now_text)
    TextView now_text;

    @Bind(R.id.history_Layout)
    RelativeLayout history_Layout;
    @Bind(R.id.history_image)
    ImageView history_image;
    @Bind(R.id.history_text)
    TextView history_text;

    @Bind(R.id.slide_img)
    CircleImageView silde_img;
    @Bind(R.id.slide_tv)
    TextView slide_tv;
    @Bind(R.id.slide_lv)
    ListView slide_lv;

    private ActionBarDrawerToggle mDrawerToggle;
    private boolean isDrawerOpened;
    private FragmentManager fragmentManager;
    private BuyFragment buyFragment;
    //private SaleFragment saleFragment;
    private NowFragment nowFragment;
    private HistoryFragment historyFragment;
    private MainActivityPresenter mainActivityPresenter;
    private LeftSlideAdapter leftSlideAdapter;
    private Intent serviceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(0);
        ButterKnife.bind(this);
        initToolbar(toolbar, getResources().getString(R.string.app_name));
        initLeftDrawer();        //抽屉
        initDrawToggle();
        serviceIntent = new Intent(MainActivity.this, PriceService.class);
        startService(serviceIntent);
        mainActivityPresenter = new MainActivityPresenter(this);
        leftSlideAdapter = new LeftSlideAdapter(MainActivity.this, R.layout.item_slide_list, mainActivityPresenter.loadSlideData());
        slide_lv.setAdapter(leftSlideAdapter);
        initView();
        fragmentManager = getFragmentManager();
        mainActivityPresenter.showFragment(0);

    }

    private void initView() {
        slide_lv.setOnItemClickListener(this);
        buy_Layout.setOnClickListener(this);
        //sale_Layout.setOnClickListener(this);
        now_Layout.setOnClickListener(this);
        history_Layout.setOnClickListener(this);
        silde_img.setOnClickListener(this);
    }


    private void initLeftDrawer() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {

            @Override
            public void onDrawerOpened(View drawerView) {
                isDrawerOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawerOpened = false;
            }

        });
    }

    private void initDrawToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();//显示开关
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


         return true;



//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }

    }


    @Override
    public void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:

                buy_image.setImageResource(R.mipmap.ic_buy_select);
                buy_text.setTextColor(MainActivity.this.getResources().getColor(R.color.primary_color));
                if (buyFragment == null) {
                    buyFragment = new BuyFragment();
                    transaction.add(R.id.content,buyFragment);
                } else {
                    transaction.show(buyFragment);
                }
                break;

            case 2:

                now_text.setTextColor(MainActivity.this.getResources().getColor(R.color.primary_color));
                now_image.setImageResource(R.mipmap.ic_now_select);
                if (nowFragment == null) {
                    nowFragment = new NowFragment();
                    transaction.add(R.id.content, nowFragment);
                } else {
                    transaction.show(nowFragment);
                }
                break;
            case 3:

                history_image.setImageResource(R.mipmap.ic_history_select);

                history_text.setTextColor(MainActivity.this.getResources().getColor(R.color.primary_color));
                if (historyFragment == null) {
                    historyFragment = new HistoryFragment();
                    transaction.add(R.id.content, historyFragment);

                } else {
                    transaction.show(historyFragment);

                }

                break;

        }
        transaction.commit();
    }


    private void clearSelection() {
        buy_text.setTextColor(Color.parseColor("#82858b"));
        buy_image.setImageResource(R.mipmap.ic_buy);
//        sale_text.setTextColor(Color.parseColor("#82858b"));
//        sale_image.setImageResource(R.mipmap.ic_sale);
        now_text.setTextColor(Color.parseColor("#82858b"));
        now_image.setImageResource(R.mipmap.ic_now);
        history_text.setTextColor(Color.parseColor("#82858b"));
        history_image.setImageResource(R.mipmap.ic_history);
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (buyFragment != null) {
            transaction.hide(buyFragment);
        }
//        if (saleFragment != null) {
//            transaction.hide(saleFragment);
//        }
        if (nowFragment != null) {
            transaction.hide(nowFragment);
        }
        if (historyFragment != null) {
            transaction.hide(historyFragment);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_Layout:
                mainActivityPresenter.showFragment(0);
                break;
            case R.id.sale_Layout:
                mainActivityPresenter.showFragment(1);
                break;
            case R.id.now_Layout:
                mainActivityPresenter.showFragment(2);
                break;
            case R.id.history_Layout:
                mainActivityPresenter.showFragment(3);
                break;
            case R.id.slide_img:
                break;


            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent toAccountActivity = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(toAccountActivity);
                break;
            case 1:

                break;
            case 2:
                Intent toAbountActivity = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(toAbountActivity);
                break;
        }
    }


}
