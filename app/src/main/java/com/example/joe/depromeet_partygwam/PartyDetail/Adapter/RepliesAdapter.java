package com.example.joe.depromeet_partygwam.PartyDetail.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.ViewHolder.RepliesViewHolder;

import java.util.ArrayList;

public class RepliesAdapter extends RecyclerView.Adapter<RepliesViewHolder>
    implements RepliesAdapterContract.View, RepliesAdapterContract.Model {
    private static final String TAG = RepliesAdapter.class.getSimpleName();

    private ArrayList<CommentSet> items;
    private Context context;
    private String userName;
    private OnItemClickLIstener onItemClickLIstener;

    public RepliesAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
        this.userName = SharePreferenceManager.getString("Username");
    }

    @Override
    public void setOnItemClickListener(OnItemClickLIstener onItemClickListener) {
        this.onItemClickLIstener = onItemClickListener;
    }

    @Override
    public RepliesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepliesViewHolder(context, parent, onItemClickLIstener, userName);
    }

    @Override
    public void onBindViewHolder(RepliesViewHolder holder, int position) {
        if(holder == null)
            return;
        holder.onBind(items.get(holder.getAdapterPosition()), position);
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
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList items) {
        this.items.addAll(items);
        notifyDataSetChanged();
        //notifyItemRangeInserted(0, items.size());
    }

    @Override
    public void clearItem() {
        items.clear();
        notifyDataSetChanged();
    }
}



