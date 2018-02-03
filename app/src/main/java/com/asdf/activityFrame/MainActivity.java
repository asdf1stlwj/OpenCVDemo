package com.asdf.activityFrame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.asdf.activityFrame.ActItem;
import com.asdf.activityFrame.ActItemAdapter;
import com.asdf.opencvdemo.GrayActivity;
import com.asdf.opencvdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView lv_actitems;
    private List<ActItem> dataList=new ArrayList<>();
    private ActItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        lv_actitems= (ListView) findViewById(R.id.lv_items);
        adapter=new ActItemAdapter(dataList,this);
        lv_actitems.setAdapter(adapter);
    }

    private void initData() {
        dataList.add(new ActItem(GrayActivity.class,"灰度化"));
        adapter.notifyDataSetChanged();
    }
}
