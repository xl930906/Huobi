package com.android.HuoBiAssistant.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.model.bean.AccountInfo;
import com.android.HuoBiAssistant.model.bean.AccountInfoBean;

import java.util.List;

/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHold>{

    private List<AccountInfoBean> accountInfoList;
    private Context mContext;


    public AccountAdapter(Context context,List<AccountInfoBean> accountInfoList){
        this.mContext =context;
        this.accountInfoList = accountInfoList;

    }

    @Override
    public AccountViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        AccountViewHold hold = new AccountViewHold(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_account,parent,false));

        return hold;
    }

    @Override
    public void onBindViewHolder(AccountViewHold holder, int position) {

        holder.title_tv.setText(accountInfoList.get(position).getAccount_title());
        holder.content_tv.setText(accountInfoList.get(position).getAccount_content());

    }

    @Override
    public int getItemCount() {
        return accountInfoList.size();
    }

    class AccountViewHold extends RecyclerView.ViewHolder{

        TextView title_tv;
        TextView content_tv;

        public AccountViewHold(View itemView) {
            super(itemView);
            title_tv = (TextView) itemView.findViewById(R.id.item_account_title);
            content_tv = (TextView) itemView.findViewById(R.id.item_account_content);

        }
    }
}
