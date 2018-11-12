package com.example.joe.depromeet_partygwam.Join.Model;

public interface JoinModelCallback {
    interface RegularCallback {
        void isEmailValidation(boolean b);
        void isPasswordValidation(boolean b);
        void isPasswordConfirmValidation(boolean b);
        void isNicknameValidation(boolean b);
    }

    interface RetrofitCallback {
        void onSuccessValidateEmail(int code);
        void onSuccessValidateNickname(int code);
        void onSuccessJoin(int code);
        void onFailure();
    }
}
