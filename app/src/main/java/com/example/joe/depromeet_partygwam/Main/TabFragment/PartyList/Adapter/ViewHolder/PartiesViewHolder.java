package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.OnItemClickListener;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartiesViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = PartiesViewHolder.class.getSimpleName();
    private OnItemClickListener onItemClickListener;

    @BindView(R.id.list_party_item_title)
    TextView textTitle;

    public PartiesViewHolder(final Context context, ViewGroup parent, OnItemClickListener onItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.list_party_item, parent, false));
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
    }

    public void onBind(Data data, int position) {
        Log.d(TAG, data.getTitle());
        textTitle.setText(data.getTitle());
    }
}
