package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.Holder.HistoryViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder>
        implements HistoryAdapterContract.View, HistoryAdapterContract.Model{

    private Context context;
    private List items;
    private OnItemClickListener onItemClickListener;

    public HistoryAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList();
        items.add(null);
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(context, parent, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        if (holder == null)
            return;
        holder.onBind(/*history, position*/);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void setItems(List items) {

    }

    @Override
    public void addItems(List items) {

    }

    @Override
    public List getItems() {
        return null;
    }

    @Override
    public void clearItems() {

    }
}
