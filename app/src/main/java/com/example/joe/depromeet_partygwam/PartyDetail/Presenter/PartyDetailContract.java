package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.LoginResponse.LoginResponse;

public interface PartyDetailContract {
    interface View {
        void toast(String msg);
        //void startMainActivity(int code, LoginResponse response);
        //void connectFail();
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        //void login(String email, String password);
    }
}
