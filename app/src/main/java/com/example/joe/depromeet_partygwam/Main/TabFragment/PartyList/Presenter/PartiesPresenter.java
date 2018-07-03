package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapterContract;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesModelCallback;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

import java.util.ArrayList;
import java.util.List;

public class PartiesPresenter
        implements PartiesContract.Presenter, PartiesModelCallback.RetrofitCallback {
    private static final String TAG = PartiesPresenter.class.getSimpleName();
    private PartiesContract.View view;
    private PartiesRetrofitModel retrofitModel;
    private PartiesAdapterContract.View adapterView;
    private PartiesAdapterContract.Model adapterModel;

    public PartiesPresenter() {
        retrofitModel = new PartiesRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void getParties(int sort, int page) {
        retrofitModel.getParties(sort, page);
    }

    @Override
    public void attchView(PartiesContract.View view) {
        this.view = view;
    }

    @Override
    public void setAdapterView(PartiesAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setAdapterModel(PartiesAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onSuccess(int code, List<Data> data) {
        if (code == ResponseCode.UNAUTHORIZED && data == null) {
            view.onUnauthorizedError();
            return;
        }

        if (code == ResponseCode.SUCCESS && data != null) {
            Log.d(TAG, data.get(0).getTitle());
            adapterModel.setItems(new ArrayList(data));
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
}
