package com.example.joe.depromeet_partygwam.Join.Presenter;

public interface JoinContract {
    interface View {
        void toast(String msg);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void emailValidation(String email);
        void passwordValidation(String password);
        void nicknameValidation(String nickname);
    }
}
