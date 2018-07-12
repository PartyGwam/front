package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

public interface PartyDetailContract {
    interface View {
        void toast(String msg);
        void onSuccess();
        void onAuthorization();
        void onBadRequest();
        //void startCommentWriteActivity();
        void onConnectFail();
    }

    interface Presenter {
        void getParty(Integer partyId);
        void attachView(View view);
        void detachView();
        void updateParty();
        void editParty(String title, String place, String description, String startTime, int maxPeople);
        void setAdapterView(
    }
}