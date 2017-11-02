package com.example.app.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author Tao.ZT.Zhang
 * Date   2017/10/30
 */

public abstract class MyRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    private  List<T> dataList;
    private static final String TAG = "RecyclerViewAdapter";

    public MyRecyclerViewAdapter(Context context, List<T> dataList ) {
        this.dataList = dataList;

    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemViewLayoutID(), parent, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
       convert(holder, dataList.get(position), position);
    }

    public abstract int getItemViewLayoutID();

    public abstract void convert(CommonViewHolder viewHolder, T item, int position);
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
