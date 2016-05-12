package com.android.HuoBiAssistant.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.HuoBiAssistant.R;
import com.android.HuoBiAssistant.model.bean.LeftSlideBean;

import java.util.List;

/**
 * Created by Dragon丶Lz on 2016/4/1.
 */
public class LeftSlideAdapter extends ArrayAdapter<LeftSlideBean> {
    private int resourceId;

    public LeftSlideAdapter(Context context, int resource, List<LeftSlideBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftSlideBean item = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_item_silde = (TextView) convertView.findViewById(R.id.item_slide_tv);
            viewHolder.img_item_silde = (ImageView) convertView.findViewById(R.id.item_slide_img);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item_silde.setText(item.getListName());
        viewHolder.img_item_silde.setImageResource(item.getImgId());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_item_silde;
        ImageView img_item_silde;
    }

}
