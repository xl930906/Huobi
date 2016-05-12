package com.android.HuoBiAssistant.ui.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.model.bean.History_db;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;
import com.android.HuoBiAssistant.presenter.HistoryFragmentPresenter;
import com.android.HuoBiAssistant.ui.activity.MainActivity;
import com.android.HuoBiAssistant.ui.adapter.HistoryFragmentAdapter;
import com.android.HuoBiAssistant.ui.view.IHistoryFragmentView;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class HistoryFragment extends Fragment implements IHistoryFragmentView,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.history_rv)
    RecyclerView history_rv;
    @Bind(R.id.swipe_history_refresh)
    SwipeRefreshLayout sf;
    private HistoryFragmentAdapter historyFragmentAdapter;
    private HistoryFragmentPresenter historyFragmentPresenter;
    private int lastVisibleItem;
    private LinearLayoutManager manager;
    private ActionMode actionMode;
    public Set<Integer> positionSet = new HashSet<>();
    private View history_details;
    private ActionMode.Callback callback=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (actionMode == null) {
                actionMode =mode;
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.long_delete, menu);
                return true;
            } else {
                return false;
            }
        }


        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_delete:
                    Set<EntrustDB> valueSet = new HashSet<>();
                    for (int position : positionSet) {
                        valueSet.add(historyFragmentAdapter.getItem(position));
                        EntrustDB.where("status = ? ", "3").find(EntrustDB.class).get(position).delete();
                    }

                    for (EntrustDB val : valueSet) {
                        historyFragmentAdapter.remove(val);

                    }
                    mode.finish();
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            positionSet.clear();
            historyFragmentAdapter.notifyDataSetChanged();
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View historyView = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, historyView);
        historyFragmentPresenter = new HistoryFragmentPresenter(getActivity(),this);
        historyFragmentPresenter.loadData();
        sf.setOnRefreshListener(this);
        sf.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        history_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == historyFragmentAdapter.getItemCount()) {
                    historyFragmentAdapter.changeMoreStatus(HistoryFragmentAdapter.LOADING_MORE);
                    historyFragmentPresenter.loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });

        return historyView;
    }


    @Override
    public void showData(List<History_db> list) {
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        history_rv.setLayoutManager(manager);
        historyFragmentAdapter = new HistoryFragmentAdapter(getActivity());
        history_rv.setAdapter(historyFragmentAdapter);
        history_rv.setItemAnimator(new DefaultItemAnimator());
        historyFragmentAdapter.setOnItemClickListener(new HistoryFragmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if (actionMode != null) {
                    addOrRemove(postion);
                } else {
                    MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(getActivity());
                    materialDialog.title("历史详情");
                    history_details = LayoutInflater.from(getActivity()).inflate(R.layout.item_history_details, null);
                    materialDialog.customView(history_details, false);
                    materialDialog.show();

                }
            }

            @Override
            public void onItemLongClick(View view, int postion) {
                if (actionMode == null) actionMode = getActivity().startActionMode(callback);
            }
        });
    }
    @Override
    public void showLoadMoreSuccess() {
        historyFragmentAdapter.changeMoreStatus(HistoryFragmentAdapter.PULL_TO_MORE);
    }

    @Override
    public void showLoadMoreNoMore() {
        historyFragmentAdapter.changeMoreStatus(HistoryFragmentAdapter.LOAD_NO_MORE);

    }

    @Override
    public void onRefresh() {
        HuobiApp.currentEntrusts2.clear();
        HuobiApp.currentEntrusts2 = Collections.synchronizedList(EntrustDB.where("status = ?", "3").find(EntrustDB.class));
        sf.setRefreshing(false);
        historyFragmentAdapter.notifyDataSetChanged();

    }
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        onRefresh();
    }


    private void addOrRemove(int position) {
        if (positionSet.contains(position)) {

            // 如果包含，则撤销选择
            positionSet.remove(position);
        } else {
            // 如果不包含，则添加
            positionSet.add(position);
        }
        if (positionSet.size() == 0) {
            // 如果没有选中任何的item，则退出多选模式
            actionMode.finish();
        } else {
            // 设置ActionMode标题
            actionMode.setTitle(positionSet.size() + " 已选择");
            // 更新列表界面，否则无法显示已选的item
            historyFragmentAdapter.notifyDataSetChanged();
        }

    }

}
