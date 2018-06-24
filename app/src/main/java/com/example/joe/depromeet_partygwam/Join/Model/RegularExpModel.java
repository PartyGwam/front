package com.example.joe.depromeet_partygwam.Join.Model;

import java.util.regex.Pattern;

public class RegularExpModel {

    private JoinModelCallback.RegularCallback callback;

    public void setCallback(JoinModelCallback.RegularCallback callback) {
        this.callback = callback;
    }

    public void emailValidation(String email) {
        boolean b = Pattern.matches("^[a-zA-Z0-9]+@[a-z]+.[a-z]+$", email);
        callback.isEmailValidation(b);
    }

    public void passwordValidation(String password) {

    }

    public void nicknameValidation(String nickname) {

    }
}
