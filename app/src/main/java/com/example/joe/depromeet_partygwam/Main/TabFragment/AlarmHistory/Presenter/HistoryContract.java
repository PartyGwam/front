package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Presenter;

public interface HistoryContract {
    interface View {
        void toast(String msg);
        void onSuccessNotification();
        void onBadRequestNotification();
        void onForbidden(String msg);
        void onAuthorization();
    }

    interface Presenter {
        void attachView(View view);
        void getHistory();
    }
}
