package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.Holder;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Data.Parties.History.HistoryData;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.OnItemClickListener;
import com.example.joe.depromeet_partygwam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_history_item)
    ConstraintLayout history;
    @BindView(R.id.list_history_item_content)
    TextView textContent;
    @BindView(R.id.list_history_item_date)
    TextView textDate;

    private Context context;
    private OnItemClickListener onItemClickListener;

    public HistoryViewHolder(Context context , ViewGroup parent, OnItemClickListener onItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.list_history_item, parent, false));
        ButterKnife.bind(this, itemView);
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public void onBind(HistoryData item, int position) {
        textContent.setText(item.getBody());
        textDate.setText(item.getCreatedAt().split("T")[0] + " "
                + item.getCreatedAt().split("T")[1].substring(0, 5));
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(item.getParty());
            }
        });
    }
}
