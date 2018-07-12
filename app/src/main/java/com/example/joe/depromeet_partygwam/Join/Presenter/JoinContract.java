package com.example.joe.depromeet_partygwam.Join.Presenter;

import com.example.joe.depromeet_partygwam.Data.UserResponse.User;

public interface JoinContract {
    interface View {
        void toast(String msg);
        void isExistEmail(int code);
        void isExistNickname(int code);
        void isEmail(boolean b);
        void isPassword(boolean b);
        void isPasswordConfirm(boolean b);
        void isNickname(boolean b);
        void setProgressbar(boolean b);
        void startLoginActivity(int code);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();
        void validateExistEmail(String email);
        void validateExistNickname(String nickname);
        void insertUser(User user);
        void validateRegularExpEmail(String email);
        void validateRegularExpPassword(String password);
        void validateRegularExpPasswordConfirm(String password);
        void validateRegularExpNickname(String nickname);
    }
}
