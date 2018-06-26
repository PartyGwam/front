package com.example.joe.depromeet_partygwam.Join.Presenter;

import com.example.joe.depromeet_partygwam.Data.User;

public interface JoinContract {
    interface View {
        void toast(String msg);
        void connectStatus(int code);
        void isEmail(boolean b);
        void isPassword(boolean b);
        void isPasswordConfirm(boolean b);
        void isNickname(boolean b);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void validateMember();
        void InsertMember(User user);
        void validateEmail(String email);
        void validatePassword(String password);
        void validatePasswordConfirm(String password);
        void validateNickname(String nickname);
    }
}
