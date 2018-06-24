package com.example.joe.depromeet_partygwam.Join.Presenter;

public interface JoinContract {
    interface View {
        void toast(String msg);
        void isEmail(boolean b);
        void isPassword(boolean b);
        void isPasswordConfirm(boolean b);
        void isNickname(boolean b);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void emailValidation(String email);
        void passwordValidation(String password);
        void passwordConfirmValidation(String password);
        void nicknameValidation(String nickname);
    }
}
