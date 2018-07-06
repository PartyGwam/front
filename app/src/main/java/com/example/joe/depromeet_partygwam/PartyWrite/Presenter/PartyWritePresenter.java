package com.example.joe.depromeet_partygwam.PartyWrite.Presenter;

import com.example.joe.depromeet_partygwam.PartyWrite.Model.PartyWriteModelCallback;
import com.example.joe.depromeet_partygwam.PartyWrite.Model.WriteRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

public class PartyWritePresenter
        implements PartyWriteContract.Presenter, PartyWriteModelCallback.RetrofitModel {

    private WriteRetrofitModel retrofitModel;
    private PartyWriteContract.View view;

    public PartyWritePresenter() {
        retrofitModel = new WriteRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void onSuccess(int code) {
        if (code == ResponseCode.UNAUTHORIZED) {
            view.onAuthorization();
            return;
        }

        if (code == ResponseCode.BAD_REQUEST) {
            view.onBadRequest();
            return;
        }

        view.onSuccess();
    }

    @Override
    public void onFailure() {
        view.onConnectFail();
    }

    @Override
    public void insertParty(String title, String place, String description, String startTime, int maxPeople) {
        retrofitModel.insertParty(title, place, description, startTime, maxPeople);
    }

    @Override
    public void attachView(PartyWriteContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
