package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter;

import java.util.ArrayList;

public interface PartiesAdapterContract {
    interface View {
        void setOnClickListener(OnItemClickListener onClickListener);
        void setOnPositionListener(OnPositionListener onPositionListener);
        void notifyAdapter();
    }

    interface Model {
        ArrayList getItems();
        void setItems(ArrayList items);
        void addItems(ArrayList items);
        void clearItem();
    }
}
