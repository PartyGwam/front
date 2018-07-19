package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter;

import java.util.List;

public interface HistoryAdapterContract {
    interface View {
        void notifyAdapter();
        void setOnItemClickListener(OnItemClickListener onItemClickListener);
    }

    interface Model {
        void setItems(List items);
        void addItems(List items);
        List getItems();
        void clearItems();
    }
}
