package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterConstract;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailModelCallback;
import com.example.joe.depromeet_partygwam.PartyDetail.Model.PartyDetailRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

public class PartyDetailPresenter
        implements PartyDetailContract.Presenter, PartyDetailModelCallback.RetrofitCallback {

    private PartyDetailContract.View view;
    private PartyDetailRetrofitModel retrofitModel;

    public PartyDetailPresenter() {
        retrofitModel = new PartyDetailRetrofitModel();
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
    public void getParty(Integer partyId) {
        //아이디값 받아온 걸로
        //retrofitModel.getParty()로 요청
        retrofitModel.getParty();
    }

    @Override
    public void updateParty() {
        //파티 시간 내용 수정되면
        retrofitModel.updateParty();
    }

    @Override
    public void editParty(String title, String place, String description, String startTime, int maxPeople) {
        retrofitModel.editParty(title, place, description, startTime, maxPeople);
    }

    @Override
    public void setAdapterView(RepliesAdapterConstract.View adapterView) {

    }

    @Override
    public void setAdapterModel(RepliesAdapterConstract.Model adapterModel) {

    }

    @Override
    public void onFailure() {
        view.onConnectFail();
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

