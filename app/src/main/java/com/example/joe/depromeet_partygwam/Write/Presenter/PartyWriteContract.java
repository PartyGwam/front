package com.example.joe.depromeet_partygwam.Write.Presenter;

public interface PartyWriteContract {
    interface View {
        void toast(String msg);
        void onAuthorization();
        void onBadRequest();
        void onSuccess();
        void onConnectFail();
    }

    interface Presenter {
        void insertParty(String title, String place, String description, String startTime, int maxPeople);
        void attachView(View view);
        void detachView();
    }
}
