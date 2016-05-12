package com.android.HuoBiAssistant.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.model.bean.History;
import com.android.HuoBiAssistant.model.bean.History_db;
import com.android.HuoBiAssistant.model.bean.NowBean;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;
import com.android.HuoBiAssistant.ui.fragment.HistoryFragment;

import java.util.List;

/**
 * Created by xianling on 2016/4/11.
 */
public class HistoryFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
 //   public static final int  PULLUP_LOAD_MORE=0;
 //   public static final int  LOADING_MORE=1;

    public static final int PULL_TO_MORE = 0;
    public static final int LOADING_MORE = 1;
    public static final int LOAD_NO_MORE = 2;


    private int load_more_status=0;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER =1;
    private List<EntrustDB> list;
    private Context context;
    public HistoryFragmentAdapter(Context context ){
        this.context = context;


    }
    /**
     * recyclerview点击监听的回调接口
     */
    public interface OnItemClickListener{
        void onItemClick(View view,int postion);
        void onItemLongClick(View view,int postion);
    }

    private OnItemClickListener onItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View HistoryLayout = LayoutInflater.from(context).inflate(R.layout.item_history_list, parent, false);
            return new HistoryViewHolder(HistoryLayout);
       } else if(viewType==TYPE_FOOTER){
            View HistoryLoading = LayoutInflater.from(context).inflate(R.layout.item_history_load, parent, false);
            return new HistoryLoadHolder(HistoryLoading);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof HistoryViewHolder){
        if(HuobiApp.currentEntrusts2.get(position).getStatus()==3){
            ((HistoryViewHolder)holder).history_state.setText("已成交");
        }else {
           ((HistoryViewHolder)holder).history_state.setText("未成交");
       }
        ((HistoryViewHolder)holder).history_time.setText(HuobiApp.currentEntrusts2.get(position).getSell_time());
        ((HistoryViewHolder)holder).history_win.setText(String.valueOf(HuobiApp.currentEntrusts2.get(position).getWin_price()));
        ((HistoryViewHolder)holder).history_number.setText(String .valueOf(HuobiApp.currentEntrusts2.get(position).getEntrust_amount()));
        ((HistoryViewHolder)holder).history_loss.setText(String.valueOf(HuobiApp.currentEntrusts2.get(position).getLoss_price()));
        ((HistoryViewHolder)holder).history_avg_price.setText(String.valueOf(HuobiApp.currentEntrusts2.get(position).getBuy_avg_price()));

            }
    else if(holder instanceof HistoryLoadHolder){
            HistoryLoadHolder historyLoadHolder=(HistoryLoadHolder)holder;
            switch (load_more_status) {
                case PULL_TO_MORE:
                    historyLoadHolder.tv_foot_status.setText("上拉加载更多...");
                    historyLoadHolder.pb_foot_status.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    historyLoadHolder.tv_foot_status.setText("正在加载更多数据...");
                    historyLoadHolder.pb_foot_status.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NO_MORE:
                    historyLoadHolder.tv_foot_status.setText("无更多数据...");
                    historyLoadHolder.pb_foot_status.setVisibility(View.GONE);
            }

        }
        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(v, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return HuobiApp.currentEntrusts2.size()+1;


    }
    private class HistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView history_time;
        private TextView history_state;
        private TextView history_win;
        private TextView history_number;
        private TextView history_loss;
        private TextView history_avg_price;
        public HistoryViewHolder(View itemView) {
            super(itemView);
            history_time=(TextView) itemView.findViewById(R.id.item_history_time);
            history_state=(TextView) itemView.findViewById(R.id.item_history_state);
            history_win=(TextView) itemView.findViewById(R.id.history_stop_win);
            history_number=(TextView) itemView.findViewById(R.id.item_history_entrust_number);
            history_loss=(TextView) itemView.findViewById(R.id.item_history_entrust_loss);
            history_avg_price=(TextView) itemView.findViewById(R.id.item_history_avg_pricel);

        }

    }


    private class HistoryLoadHolder extends RecyclerView.ViewHolder{
        private TextView tv_foot_status;
        private ProgressBar pb_foot_status;
        public HistoryLoadHolder(View itemView) {
            super(itemView);
            pb_foot_status = (ProgressBar) itemView.findViewById(R.id.pb_item_foot);
            tv_foot_status = (TextView) itemView.findViewById(R.id.tv_item_foot);
        }
    }


    public int getItemViewType(int position){
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }


    }


    public void changeMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }


    public void addItem(List<EntrustDB> ho) {
       HuobiApp.currentEntrusts2.addAll(ho);
        notifyDataSetChanged();
    }
    public EntrustDB getItem(int pos){
        return HuobiApp.currentEntrusts2.get(pos);
    }
    public void remove(EntrustDB str){
        HuobiApp.currentEntrusts2.remove(str);

        notifyDataSetChanged();
    }

}

