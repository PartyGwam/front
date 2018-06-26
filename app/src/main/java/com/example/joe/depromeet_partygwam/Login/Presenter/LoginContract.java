package com.example.joe.depromeet_partygwam.Login.Presenter;

import com.example.joe.depromeet_partygwam.Data.User;

public interface LoginContract {
    interface View {
        void toast(String msg);
        void onSuccess(User user);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void validateUser(User user);
    }
}
