package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.party_list_item_people_num)
    ConstraintLayout pplBackground;
    @BindView(R.id.party_list_item_closed_flag)
    FrameLayout closedFlag;
    @BindView(R.id.list_party_item_date2)
    TextView textDate2;
    @BindView(R.id.list_party_item_location_icon)
    ImageView locationIcon;
    private SimpleDateFormat date;
    private SimpleDateFormat time;
    private Context context;

    public PartiesViewHolder(final Context context, ViewGroup parent, OnItemClickListener onItemClickListener, OnPositionListener onPositionListener) {
        super(LayoutInflater.from(context).inflate(R.layout.list_party_item, parent, false));
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        this.onPositionListener = onPositionListener;
        this.context = context;

        date = new SimpleDateFormat("yyyy-MM-dd");
        time = new SimpleDateFormat("HH:mm");
    }

    public void onBind(Data data, int position, int listSize) {
        String startDate = data.getStartTime().split("T")[0];
        String startTime = data.getStartTime().split("T")[1].substring(0, 5);
        String createDate = data.getCreatedAt().split("T")[0];
        String createTime = data.getCreatedAt().split("T")[1].substring(0, 5);
        String info  = data.getPartyOwner().getUsername() + " | "
                + createTime + " | 조회 ";

        if (data.getIsNew()){
            imgNew.setVisibility(View.VISIBLE);
        }else{
            imgNew.setVisibility(View.GONE);
        }

        if (data.getHasStarted() == true) {
            textDate2.setText(startDate.split("-")[1] + "." + startDate.split("-")[2]);
            textDate2.setTextColor(0xAAb7b7b7);

            pplBackground.setBackground(context.getDrawable(R.drawable.people_gray2));

            main.setOnClickListener((v) -> {
                Toast.makeText(context, "마감된 파티 입니다.", Toast.LENGTH_LONG).show();
            });

            closedFlag.setVisibility(View.VISIBLE);

            textTime.setText(startTime);
            textTime.setTextColor(0xAAb7b7b7);

            locationIcon.setBackground(context.getDrawable(R.drawable.ic_place_white2));

            textPlace.setText(data.getPlace());
            textPlace.setTextColor(0xAA4b4b4b);

            textInfo.setText(info);

            textPeopleNum.setText(data.getCurrentPeople() + "");
            textPeopleNum.setTextColor(0xAAffffff);

            textPeopleMax.setText(data.getMaxPeople() + "");
            textPeopleMax.setTextColor(0xAAffffff);
        }else {
            if(startDate.equals(date)){
                textDate.setText("오늘");
            }else{
                textDate.setText(startDate.split("-")[1] + "." + startDate.split("-")[2]);
            }
            main.setOnClickListener((v) -> {
                onItemClickListener.onItemClick(data, position);
            });
            textTime.setText(startTime);
            textPlace.setText(data.getPlace());

            textInfo.setText(info);
            textPeopleNum.setText(data.getCurrentPeople() + "");
            textPeopleMax.setText(data.getMaxPeople() + "");
        }

        if (data.getTitle().length() > 13) {
            textTitle.setText(data.getTitle().substring(0, 13) + "...");
        } else {
            textTitle.setText(data.getTitle());
        }

        if (position == listSize - 1) {
            int page = (listSize / 20) + 1;
            onPositionListener.onLoad(page);
        }

        Log.d(TAG, "position " + position + "/ max " + listSize);
    }
}