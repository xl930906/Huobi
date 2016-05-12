package com.android.HuoBiAssistant.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;
import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.common.HuobiApp;
import com.android.HuoBiAssistant.config.Constants;
import com.android.HuoBiAssistant.model.bean.NowEntrust;
import com.android.HuoBiAssistant.model.bean.NowBean;
import com.android.HuoBiAssistant.model.callback.HttpCallbackListener;
import com.android.HuoBiAssistant.model.callback.OnItemClickListener;
import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;
import com.android.HuoBiAssistant.ui.fragment.NowFragment;
import com.android.HuoBiAssistant.ui.view.INowFragmentView;
import com.android.HuoBiAssistant.util.OkHttpUtils;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dragon丶Lz on 2016/4/9.
 */
public class NowFragmentAdaper  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    //private OnItemClickListener<EntrustDB> mListener;
    private Context context;
    //private List<EntrustDB> dataList;
    private static final int Success = 0;
    private static final int Failure = 1;
    public static final int STATUS_WAIT_SELL = 2; // 等待卖出
    public static final int STATUS_WAIT_BUY=1;//等地买入
    private AppCompatEditText edittext_win;
    private AppCompatEditText edittext_loss;
    private String edittext_win_context;
    private String edittext_loss_context;
    private INowFragmentView view;
    public static final int STATUS_SUCCESS = 3;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == Success) {
//                Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();
//
//            }
//
//            if (msg.what == Failure){
//                Toast.makeText(context,"出现错误",Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    };


//    public void setOnItemClickListener(OnItemClickListener<EntrustDB> listener){
//        mListener = listener;
//    }

    public NowFragmentAdaper(Context context,INowFragmentView view){
        this.context = context;
        this.view = view;
        //this.dataList= dataList;

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

        View NowLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now_list, parent, false);
        return new NowHolder(NowLayout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final EntrustDB entrustDB = HuobiApp.currentEntrusts.get(position);
        if (entrustDB.getStatus()==STATUS_WAIT_BUY) {
            ((NowHolder) holder).item_now_image.setImageResource(R.mipmap.ic_item_now_buy);
            ((NowHolder) holder).item_now_business.setText(R.string.WaitBuy);
            ((NowHolder) holder).item_now_business.setTextColor(context.getResources().getColor(R.color.item_now_green));

        }else  {
            ((NowHolder) holder).item_now_image.setImageResource(R.mipmap.ic_item_now_sale);
            ((NowHolder) holder).item_now_business.setText(R.string.WaitSale);
            ((NowHolder) holder).item_now_business.setTextColor(context.getResources().getColor(R.color.item_now_red));
        }

        ((NowHolder) holder).item_now_time.setText(entrustDB.getSubmit_time());
        ((NowHolder) holder).item_now_entrust_win.setText(String.valueOf(entrustDB.getWin_price()));
        ((NowHolder) holder).item_now_entrust_number.setText(String .valueOf(entrustDB.getEntrust_amount()));
        ((NowHolder) holder).item_now_entrust_loss.setText(String.valueOf(entrustDB.getLoss_price()));
        ((NowHolder) holder).item_now_entrust_avg_price.setText(String.valueOf(entrustDB.getBuy_avg_price()));
        ((NowHolder) holder).item_now_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog.Builder materialDialog=new MaterialDialog.Builder(context);
                materialDialog.title("修改委托");
                View changeview=LayoutInflater.from(context).inflate(R.layout.item_change,null);
                materialDialog.customView(changeview, false);
                edittext_win=(AppCompatEditText) changeview.findViewById(R.id.change_win);
                edittext_loss=(AppCompatEditText) changeview.findViewById(R.id.change_loss);
                materialDialog.negativeText("cancel").callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                });
                materialDialog.positiveText("ok").callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        initEdittextassing();
                        if(HuobiApp.currentEntrusts.get(position).getStatus()==STATUS_SUCCESS){
                            Toast.makeText(context,"此单委托已成交,无法进行修改",Toast.LENGTH_SHORT).show();
                        }else{
                        ContentValues values = new ContentValues();
                        if(!(edittext_win_context.equals(""))){
                        values.put("win_price", edittext_win_context);}
                        if(!(edittext_loss_context.equals(""))){
                        values.put("loss_price", edittext_loss_context);}
                        DataSupport.updateAll(EntrustDB.class, values, "entrustId=?", String.valueOf(HuobiApp.currentEntrusts.get(position).getEntrustId()));
                        view.onfresh();
                    }}
                });


                materialDialog.show();
            }
        });
//        ((NowHolder) holder).item_now_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OkHttpUtils.CancleEntrust(entrustDB.getEntrustId().toString(), Constants.CANCEL_ORDER, new HttpCallbackListener() {
//                    @Override
//                    public void onSuccess(String response) {
//
//                        Log.d("TAG--->>",response);
//                        Message msg = new Message();
//                        msg.what = Success;
//                        handler.sendMessage(msg);
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//                        Message msg = new Message();
//                        msg.what = Failure;
//                        handler.sendMessage(msg);
//                    }
//                });
//            }
//        });


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

    private void initEdittextassing() {
        edittext_win_context=edittext_win.getText().toString();
        edittext_loss_context=edittext_loss.getText().toString();
    }




    @Override
    public int getItemCount() {
        return HuobiApp.currentEntrusts.size();
    }


    private class NowHolder extends RecyclerView.ViewHolder{

        private ImageView item_now_image;
        private TextView item_now_business;
        private TextView item_now_time;
        private TextView item_now_entrust_win;
        private TextView item_now_entrust_number;
        private TextView item_now_entrust_loss;
        private TextView item_now_change;
        private TextView item_now_cancel;
        private TextView item_now_entrust_avg_price;


        public NowHolder(View itemView) {
            super(itemView);
            item_now_image = (ImageView) itemView.findViewById(R.id.item_now_image);
            item_now_business = (TextView) itemView.findViewById(R.id.item_now_business);
            item_now_time = (TextView) itemView.findViewById(R.id.item_now_time);
            item_now_entrust_win = (TextView) itemView.findViewById(R.id.item_now_entrust_win);
            item_now_entrust_number = (TextView) itemView.findViewById(R.id.item_now_entrust_number);
            item_now_entrust_loss = (TextView) itemView.findViewById(R.id.item_now_entrust_loss);
            item_now_change = (TextView) itemView.findViewById(R.id.item_now_change);
            item_now_cancel = (TextView) itemView.findViewById(R.id.item_now_cancel);
            item_now_entrust_avg_price=(TextView) itemView.findViewById(R.id.item_now_avg_price);

        }
    }

}
