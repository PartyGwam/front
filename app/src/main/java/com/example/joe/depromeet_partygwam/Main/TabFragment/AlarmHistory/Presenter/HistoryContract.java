package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.History.HistoryData;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.HistoryAdapter;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.HistoryAdapterContract;

import java.util.List;

public interface HistoryContract {
    interface View {
        void toast(String msg);
        void onSuccessHistoryLoad(List<HistoryData> items);
        void onNotFoundHistoryLoad();
        void onForbidden(String msg);
        void onAuthorization();
        void onConnectionFail();
        void startDetailActivity(String slug);
    }

    interface Presenter {
        void attachView(View view);
        void getHistory();
        void setAdapterModel(HistoryAdapterContract.Model adapterModel);
        void setAdapterView(HistoryAdapterContract.View adapterView);
        void setHistroyView(List<HistoryData> items);
    }
}
