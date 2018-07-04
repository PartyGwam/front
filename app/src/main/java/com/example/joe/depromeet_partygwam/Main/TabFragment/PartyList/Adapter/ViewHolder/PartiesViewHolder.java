package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.ViewHolder;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.OnItemClickListener;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.OnPositionListener;
import com.example.joe.depromeet_partygwam.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartiesViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = PartiesViewHolder.class.getSimpleName();
    private OnItemClickListener onItemClickListener;
    private OnPositionListener onPositionListener;

    @BindView(R.id.list_party_item)
    ConstraintLayout main;
    @BindView(R.id.list_party_item_title)
    TextView textTitle;
    @BindView(R.id.list_party_item_today)
    ImageView imgNew;
    @BindView(R.id.list_party_item_date)
    TextView textDate;
    @BindView(R.id.list_party_item_time)
    TextView textTime;
    @BindView(R.id.list_party_item_place)
    TextView textPlace;
    @BindView(R.id.list_party_item_info)
    TextView textInfo;
    @BindView(R.id.list_party_item_people_num)
    TextView textPeopleNum;
    @BindView(R.id.list_party_item_people_max)
    TextView textPeopleMax;
    private Date today;
    private SimpleDateFormat date;

    public PartiesViewHolder(final Context context, ViewGroup parent, OnItemClickListener onItemClickListener, OnPositionListener onPositionListener) {
        super(LayoutInflater.from(context).inflate(R.layout.list_party_item, parent, false));
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        this.onPositionListener = onPositionListener;
        today = new Date();
        date = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void onBind(Data data, int position, int listSize) {
        String startDate = data.getStartTime().split("T")[0];
        String startTime = data.getStartTime().split("T")[1].substring(0, 5);
        String createDate = data.getCreatedAt().split("T")[0];
        String createTime = data.getCreatedAt().split("T")[1].substring(0, 5);
        String info  = data.getPartyOwner().getUsername() + " | "
                + createTime + " | 조회 ";

        if (data.getIsNew())
            imgNew.setVisibility(View.VISIBLE);

        if (startDate.equals(date)) {
            textDate.setText("오늘");
            info = data.getPartyOwner().getUsername() + " | "
                    + createDate + " | 조회 ";
        } else {
            textDate.setText(startDate.split("-")[1] + "." + startDate.split("-")[2]);
        }

        if (data.getTitle().length() > 13) {
            textTitle.setText(data.getTitle().substring(0, 13) + "...");
        } else {
            textTitle.setText(data.getTitle());
        }

        textTime.setText(startTime);
        textPlace.setText(data.getPlace());

        textInfo.setText(info);
        textPeopleNum.setText(data.getCurrentPeople() + "");
        textPeopleMax.setText(data.getMaxPeople() + "");

        main.setOnClickListener((v) -> {
            onItemClickListener.onItemClick(data, position);
        });

        if (position == listSize - 1) {
            int page = listSize % 20;
            onPositionListener.onLoad(page);
        }
        Log.d(TAG, "position " + position + "/ max " + listSize);
    }
}
