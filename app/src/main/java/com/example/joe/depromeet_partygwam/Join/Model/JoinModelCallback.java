package com.example.joe.depromeet_partygwam.Join.Model;

public interface JoinModelCallback {
    interface RegularCallback {
        void isEmailValidation(boolean b);
        void isPasswordValidation(boolean b);
        void isNicknameValidation(boolean b);
    }
}
