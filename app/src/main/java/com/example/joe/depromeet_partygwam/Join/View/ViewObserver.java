package com.example.joe.depromeet_partygwam.Join.View;

public class ViewObserver {
    private ObserverCallback callback;
    private Boolean isExistEmail;
    private Boolean isExistNickname;
    private Boolean isEmail;
    private Boolean isPassword;
    private Boolean isPasswordConfirm ;
    private Boolean isNickname;
    private Boolean isTermOfUse;

    public void update() {

        callback.update();
    }

    public void setCallback(ObserverCallback callback) {
        this.callback = callback;
    }
}
