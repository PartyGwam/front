package com.example.joe.depromeet_partygwam.Login.Presenter;

import com.example.joe.depromeet_partygwam.Data.LoginResponse.LoginResponse;

public interface LoginContract {
    interface View {
        void toast(String msg);
        void startMainActivity(int code, LoginResponse response);
        void connectFail();
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void login(String email, String password);
    }
}
