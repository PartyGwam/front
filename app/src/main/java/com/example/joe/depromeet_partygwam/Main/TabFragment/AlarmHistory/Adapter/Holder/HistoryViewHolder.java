package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.Holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.OnItemClickListener;
import com.example.joe.depromeet_partygwam.R;

import butterknife.ButterKnife;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private OnItemClickListener onItemClickListener;

    public HistoryViewHolder(Context context , ViewGroup parent, OnItemClickListener onItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.list_history_item, parent, false));
        ButterKnife.bind(this, itemView);
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public void onBind(/*History history, int position*/) {

    }
}
