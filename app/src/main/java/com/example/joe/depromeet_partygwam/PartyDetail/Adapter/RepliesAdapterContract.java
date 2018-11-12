package com.example.joe.depromeet_partygwam.PartyDetail.Adapter;

import java.util.ArrayList;

public interface RepliesAdapterContract {
    interface View{
        void notifyAdapter();
        void setOnItemClickListener(OnItemClickLIstener onItemClickListener);
    }
    interface Model {
        ArrayList getItems();
        void setItems(ArrayList items);
        void addItems(ArrayList items);
        void clearItem();
    }
}
