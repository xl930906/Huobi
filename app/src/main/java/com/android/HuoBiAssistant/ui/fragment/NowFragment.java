package com.android.HuoBiAssistant.ui.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.model.bean.NowBean;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;
import com.android.HuoBiAssistant.presenter.NowFragmentPresenter;
import com.android.HuoBiAssistant.ui.adapter.AccountAdapter;
import com.android.HuoBiAssistant.ui.adapter.NowFragmentAdaper;
import com.android.HuoBiAssistant.ui.view.INowFragmentView;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class NowFragment extends Fragment implements INowFragmentView,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.now_rv)
    RecyclerView now_rv;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    private NowFragmentAdaper nowFragmentAdaper;
    private NowFragmentPresenter nowFragmentPresenter;
    private View entrust_details;
    private TextView entrust_type;
    private TextView buy_time;
    private TextView win_price;
    private TextView loss_price;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View nowView = inflater.inflate(R.layout.fragment_now, container, false);
        ButterKnife.bind(this, nowView);
        nowFragmentPresenter = new NowFragmentPresenter(getActivity(),this);
        nowFragmentPresenter.loadData();
        swipe_refresh.setOnRefreshListener(this);
        swipe_refresh.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        return nowView;
    }


    @Override
    public void showData(List<EntrustDB> dataList) {
        now_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        nowFragmentAdaper = new NowFragmentAdaper(getActivity(),this);
        now_rv.setAdapter(nowFragmentAdaper);
        now_rv.setItemAnimator(new DefaultItemAnimator());
        nowFragmentAdaper.setOnItemClickListener(new NowFragmentAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                MaterialDialog.Builder materialDialog= new MaterialDialog.Builder(getActivity());
                materialDialog.title("委托详情");
                entrust_details = LayoutInflater.from(getActivity()).inflate(R.layout.item_entrust_the_details,null);
                materialDialog.customView(entrust_details,false);
                initEntursuDetails();
                show(postion);
                if (HuobiApp.currentEntrusts.get(postion).getBuy_time() != null)
                    materialDialog.show();
            }


            @Override
            public void onItemLongClick(View view, int postion) {
            }
        });
}

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        onRefresh();
    }

    @Override
    public void onfresh() {
        onRefresh();
    }

    private void initEntursuDetails() {
        entrust_type=(TextView) entrust_details.findViewById(R.id.entrust_type);
        buy_time=(TextView) entrust_details.findViewById(R.id.buy_time);
        win_price=(TextView) entrust_details.findViewById(R.id.win_price);
        loss_price=(TextView) entrust_details.findViewById(R.id.loss_price);
    }
    private void show(int postion) {

        if (HuobiApp.currentEntrusts.get(postion).getBuy_time() == null) {
            Toast.makeText(getActivity(), "暂无详情", Toast.LENGTH_SHORT).show();
        }else {
            if (HuobiApp.currentEntrusts.get(postion).getType() == 2) {
                entrust_type.setText("市价委托");
            } else {
                entrust_type.setText("限价委托");
            }
            buy_time.setText(HuobiApp.currentEntrusts.get(postion).getBuy_time());

            win_price.setText(String.valueOf(String.format("%.2f", HuobiApp.currentEntrusts.get(postion).getWin_price() -
                    HuobiApp.currentEntrusts.get(postion).getBuy_avg_price())));


            loss_price.setText(String.valueOf(String.format("%.2f", HuobiApp.currentEntrusts.get(postion).getLoss_price() -
                    HuobiApp.currentEntrusts.get(postion).getBuy_avg_price())));
        }
    }

    @Override
    public void onRefresh() {
                HuobiApp.currentEntrusts.clear();
                HuobiApp.currentEntrusts = Collections.synchronizedList(EntrustDB.where("status = ? or status = ?", "1", "2").find(EntrustDB.class));
                swipe_refresh.setRefreshing(false) ;
                nowFragmentAdaper.notifyDataSetChanged();

}


}
