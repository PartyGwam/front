package com.example.joe.depromeet_partygwam.PartyDetail.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.ViewHolder.RepliesViewHolder;
import com.example.joe.depromeet_partygwam.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepliesAdapter extends RecyclerView.Adapter<RepliesViewHolder>
implements RepliesAdapterConstract.View, RepliesAdapterConstract.Model{
    private static final String TAG = RepliesViewHolder.class.getSimpleName();

    private ArrayList<CommentSet> items;
    private Context context;

    public RepliesAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public RepliesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RepliesViewHolder holder, int position) {
        if(holder == null)
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



