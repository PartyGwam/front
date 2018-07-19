package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Presenter;

import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.HistoryAdapter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.HistoryAdapterContract;

public interface HistoryContract {
    interface View {
        void toast(String msg);
        void onSuccessHistoryLoad();
        void onBadRequestHistoryLoad();
        void onForbidden(String msg);
        void onAuthorization();
    }

    interface Presenter {
        void attachView(View view);
        void getHistory();
        void setAdapterModel(HistoryAdapterContract.Model adapterModel);
        void setAdapterView(HistoryAdapterContract.View adapterView);
    }
}
