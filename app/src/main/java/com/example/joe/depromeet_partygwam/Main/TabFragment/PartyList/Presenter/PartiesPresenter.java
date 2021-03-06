package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.OnItemClickListener;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapterContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.OnPositionListener;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesModelCallback;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.ArrayList;
import java.util.List;

public class PartiesPresenter
        implements PartiesContract.Presenter, PartiesModelCallback.RetrofitCallback,
        OnItemClickListener, OnPositionListener {
    private static final String TAG = PartiesPresenter.class.getSimpleName();
    private PartiesContract.View view;
    private PartiesRetrofitModel retrofitModel;
    private PartiesAdapterContract.View adapterView;
    private PartiesAdapterContract.Model adapterModel;
    private String search;
    private int sort;
    private int page;

    public PartiesPresenter() {
        retrofitModel = new PartiesRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void getParties(String search, int sort) {
        this.search = search;
        this.sort = sort;
        page = 1;
        adapterModel.clearItem();
        retrofitModel.getParties(search, sort, page);
    }

    @Override
    public void getCreatedParties() {
        page = 1;
        adapterModel.clearItem();
        retrofitModel.getCreatedParties(page);
    }

    @Override
    public void getJoinedParties() {
        page = 1;
        adapterModel.clearItem();
        retrofitModel.getJoinedParties(page);
    }

    @Override
    public void attchView(PartiesContract.View view) {
        this.view = view;
    }

    @Override
    public void setAdapterView(PartiesAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListener(this);
        this.adapterView.setOnPositionListener(this);
    }

    @Override
    public void setAdapterModel(PartiesAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onSuccess(int code, List<Data> data) {

        if (code == ResponseCode.NOT_FOUND && data == null) {
            view.onNotFound();
            return;
        }

        if (code == ResponseCode.UNAUTHORIZED && data == null) {
            view.onUnauthorizedError();
            return;
        }

        if (code == ResponseCode.SUCCESS && data != null) {
            Log.d(TAG, data.get(0).getTitle());
            adapterModel.addItems(new ArrayList(data));
            view.onSuccessGetList();
            return;
        }
        view.onUnknownError();
    }

    @Override
    public void onFailure() {
        view.onConnectFail();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onItemClick(Data item, int position) {
        view.startDetailActivity(item);
    }

    @Override
    public void onLoad(int page) {
        if (this.page == page)
            return;
        this.page = page;
        Log.d(TAG, "page : " + page);
        retrofitModel.getParties(search, sort, page);
    }
}
