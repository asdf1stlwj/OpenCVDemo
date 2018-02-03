package com.asdf.activityFrame;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.asdf.opencvdemo.R;
import com.asdf.utils.ViewHolder;


import java.util.List;

/**
 * Created by User on 2017/10/24.
 */

public class ActItemAdapter extends BaseAdapter{
    private List<ActItem> dataList;
    private Context context;

    public ActItemAdapter(List<ActItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder(context, R.layout.actlist_item);
            convertView=viewHolder.getConvertView();
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Button btn_act=viewHolder.get(R.id.btn_act);
        final ActItem data=dataList.get(position);
        btn_act.setText(data.getActName());
        btn_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,data.getActClass());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
