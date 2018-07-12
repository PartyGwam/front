package com.example.joe.depromeet_partygwam.PartyDetail.Adapter;

import java.util.ArrayList;

public interface RepliesAdapterConstract {
    interface View{
        void notifyAdapter();
    }
    interface Model {
        ArrayList getItems();
        void setItems(ArrayList items);
        void addItems(ArrayList items);
        void clearItem();
    }
}
