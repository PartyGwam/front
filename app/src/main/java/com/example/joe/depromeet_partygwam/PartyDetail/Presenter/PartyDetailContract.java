package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

public interface PartyDetailContract {
    interface View {
        void toast(String msg);
        //void onSuccessGetParty();
        //void startCommentWriteActivity();
        //void connectFail();
    }

    interface Presenter {
        void getParty(Integer partyId);
        void attachView(View view);
        void detachView();
        void updateParty();
        void editParty();
    }
}
