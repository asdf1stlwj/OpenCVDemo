package com.asdf.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by User on 2017/10/24.
 */

public class ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private int mLayoutId ;

    public ViewHolder(Context context, int layoutId) {
        this.mViews = new SparseArray<View>();
        mLayoutId = layoutId ;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        mConvertView.setTag(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T get(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getLayoutId(){
        return mLayoutId ;
    }

}
