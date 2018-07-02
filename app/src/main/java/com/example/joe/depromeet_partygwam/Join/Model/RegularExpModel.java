package com.example.joe.depromeet_partygwam.Join.Model;

import java.util.regex.Pattern;

public class RegularExpModel {

    private JoinModelCallback.RegularCallback callback;
    private String password = "";

    public void setCallback(JoinModelCallback.RegularCallback callback) {
        this.callback = callback;
    }

    public void emailValidation(String email) {
        boolean b = Pattern.matches("^[a-zA-Z0-9]+@[a-z]+(.)([a-z]|[a-z](.)[a-z])+$", email);
        callback.isEmailValidation(b);
    }

    public void passwordValidation(String password) {
        this.password = password;
        boolean b = Pattern
                .matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~`]{6,15}+$", password);
        callback.isPasswordValidation(b);
    }

    public void passwordConfirmValidation(String password) {
        boolean b = this.password.equals(password);
        callback.isPasswordConfirmValidation(b);
    }

    public void nicknameValidation(String nickname) {
        boolean b = Pattern.matches("^([a-zA-Z0-9]{2,8})+$", nickname);
        callback.isNicknameValidation(b);
    }
}
