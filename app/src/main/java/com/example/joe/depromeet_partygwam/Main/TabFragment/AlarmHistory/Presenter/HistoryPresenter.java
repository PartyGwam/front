package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Presenter;

import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.HistoryAdapterContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.OnItemClickListener;

public class HistoryPresenter implements HistoryContract.Presenter, OnItemClickListener {
    private HistoryContract.View view;
    private HistoryAdapterContract.Model adapterModel;
    private HistoryAdapterContract.View adapterView;

    @Override
    public void attachView(HistoryContract.View view) {

    }

    @Override
    public void getHistory() {

    }

    @Override
    public void setAdapterModel(HistoryAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setAdapterView(HistoryAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(this);
    }
}
