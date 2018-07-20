package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.History.HistoryData;

import java.util.List;

public class HistoryModelCallback {
    public interface RetrofitCallback {
        void onSuccessHistoryLoad(int code, List<HistoryData> items);
        void onFailure();
    }
}
