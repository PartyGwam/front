package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Model;

public class HistoryModelCallback {
    interface RetrofitCallback {
        void onSuccessHistoryLoad(int code);
        void onFailure();
    }
}
