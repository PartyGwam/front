package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter;

public class PartyListPresenter implements PartyListContract.Presenter {

    private PartyListContract.View view;

    public PartyListPresenter() {
        
    }
    @Override
    public void getParties(int sort) {

    }

    @Override
    public void attchView(PartyListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
