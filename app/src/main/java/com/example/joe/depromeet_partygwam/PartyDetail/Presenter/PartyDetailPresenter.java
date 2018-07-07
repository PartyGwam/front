package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailModelCallback;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailRetrofitModel;

public class PartyDetailPresenter implements PartyDetailContract.Presenter,
        PartyDetailModelCallback, PartyDetailModelCallback.RetrofitCallback{

    private PartyDetailContract.View view;
    private PartyDetailRetrofitModel retrofitModel;

    public PartyDetailPresenter(){
        retrofitModel = new PartyDetailRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void getParty(Integer partyId) {
        //아이디값 받아온 걸로
        //retrofitModel.getParty()로 요청
        retrofitModel.getParty();
    }

    @Override
    public void attachView(PartyDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void editParty() {

    }

    @Override
    public void updateParty() {
        //파티 시간 내용 수정되면
        retrofitModel.updateParty();
    }
}
