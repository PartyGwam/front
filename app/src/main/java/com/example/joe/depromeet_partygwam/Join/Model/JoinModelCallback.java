package com.example.joe.depromeet_partygwam.Join.Model;

import com.example.joe.depromeet_partygwam.Join.Data.Member;

public interface JoinModelCallback {
    interface RegularCallback {
        void isEmailValidation(boolean b);
        void isPasswordValidation(boolean b);
        void isPasswordConfirmValidation(boolean b);
        void isNicknameValidation(boolean b);
    }

    interface RetrofitCallback {
        void onSuccess(int code);
        void onFailure(int code);
    }
}
