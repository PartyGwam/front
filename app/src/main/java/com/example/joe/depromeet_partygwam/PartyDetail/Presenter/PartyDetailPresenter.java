package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailModelCallback;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailRetrofitModel;

public class PartyDetailPresenter implements PartyDetailContract.Presenter,
        PartyDetailModelCallback, PartyDetailModelCallback.RetrofitCallback{

    private PartyDetailContract.View view;
    private PartyDetailRetrofitModel retrofitModel;

    public PartyDetailPresenter(){

    }

    @Override
    public void attachView(PartyDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
