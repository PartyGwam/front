package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter;

import java.util.ArrayList;

public interface PartyListAdapterContract {
    interface View {
        void setOnClickListener();
        void notifyAdapter();
    }

    interface Model {
        ArrayList getItems();
        void setItems(ArrayList items);
    }
}
