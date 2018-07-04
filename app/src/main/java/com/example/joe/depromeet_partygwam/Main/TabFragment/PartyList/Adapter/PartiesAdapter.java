package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.ViewHolder.PartiesViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartiesAdapter extends RecyclerView.Adapter<PartiesViewHolder>
        implements PartiesAdapterContract.View, PartiesAdapterContract.Model {
    private static final String TAG = PartiesAdapter.class.getSimpleName();
    private ArrayList<Data> items;
    private OnItemClickListener onItemClickListener;
    private OnPositionListener onPositionListener;
    private Context context;

    public PartiesAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public PartiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PartiesViewHolder(context, parent, onItemClickListener, onPositionListener);
    }

    @Override
    public void onBindViewHolder(PartiesViewHolder holder, int position) {
        if (holder == null)
            return;
        holder.onBind(items.get(position), position, getItemCount());
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    @Override
    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }

    @Override
    public void setOnPositionListener(OnPositionListener onPositionListener) {
        this.onPositionListener = onPositionListener;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public ArrayList getItems() {
        return items;
    }

    @Override
    public void setItems(ArrayList items) {
        Log.d(TAG, "setItems");
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
