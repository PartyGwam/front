package com.example.joe.depromeet_partygwam.PartyDetail.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.ViewHolder.RepliesViewHolder;

import java.util.ArrayList;

public class RepliesAdapter extends RecyclerView.Adapter<RepliesViewHolder>
implements RepliesAdapterContract.View, RepliesAdapterContract.Model{
    private static final String TAG = RepliesViewHolder.class.getSimpleName();

    private ArrayList<CommentSet> items;
    private Context context;

    public RepliesAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public RepliesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepliesViewHolder(context, parent);
    }

    @Override
    public void onBindViewHolder(RepliesViewHolder holder, int position) {
        if(holder == null)
            return;
        holder.onBind(items.get(position));
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

    @Override
    public void clearItem() {
        items.clear();
        notifyDataSetChanged();
    }
}



