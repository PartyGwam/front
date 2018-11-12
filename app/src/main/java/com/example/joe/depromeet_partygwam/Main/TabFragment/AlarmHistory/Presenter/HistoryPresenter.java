package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.History.HistoryData;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.HistoryAdapterContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Adapter.OnItemClickListener;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Model.HistoryModelCallback;
import com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Model.HistoryRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.List;

public class HistoryPresenter implements HistoryContract.Presenter, OnItemClickListener,
        HistoryModelCallback.RetrofitCallback {

    private HistoryContract.View view;
    private HistoryAdapterContract.Model adapterModel;
    private HistoryAdapterContract.View adapterView;
    private HistoryRetrofitModel retrofitModel;

    public HistoryPresenter() {
        retrofitModel = new HistoryRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void onItemClick(String slug) {
        view.startDetailActivity(slug);
    }

    @Override
    public void attachView(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void getHistory() {
        retrofitModel.getHistory();
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

    @Override
    public void onSuccessHistoryLoad(int code, List<HistoryData> items) {
        if (code == ResponseCode.SUCCESS) {
            view.onSuccessHistoryLoad(items);
            return;
        }

        if (code == ResponseCode.NOT_FOUND) {
            view.onNotFoundHistoryLoad();
            return;
        }

        if (code == ResponseCode.UNAUTHORIZED) {
            view.onAuthorization();
            return;
        }
    }

    @Override
    public void setHistroyView(List<HistoryData> items) {
        adapterModel.setItems(items);
    }

    @Override
    public void onFailure() {
        view.onConnectionFail();
    }
}
