package com.cozing.rxjava2retrofit2hybrid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cozing.rxjava2retrofit2hybrid.R;
import com.cozing.rxjava2retrofit2hybrid.javabean.Operator;

import java.util.List;

/**
 * desc:Recycleview 适配器
 * <p>
 * Author: Cozing
 * GitHub: https://github.com/Cozing
 * Date: 2018/6/15
 */

public class MRVAdapter extends RecyclerView.Adapter<MRVAdapter.ViewHolder>{

    private List<Operator> dataList;

    private OnItemClickListener onItemClickListener;

    public MRVAdapter(List<Operator> dataList, OnItemClickListener onItemClickListener) {
        this.dataList = dataList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycleview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Operator operator = dataList.get(position);
        holder.titleView.setText(operator.title);
        holder.bodyView.setText(operator.desc);

        if(onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titleView;
        private TextView bodyView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.title);
            bodyView = itemView.findViewById(R.id.body);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
