package com.example.joe.depromeet_partygwam.Join.View;

public class ViewManager {
    private Boolean isExistEmail;
    private Boolean isExistNickname;
    private Boolean isEmail;
    private Boolean isPassword;
    private Boolean isPasswordConfirm ;
    private Boolean isNickname;
    private Boolean isTermOfUse;
    private ViewObserver observer;

    public ViewManager(Boolean isExistEmail, Boolean isExistNickname, Boolean isEmail, Boolean isPassword, Boolean isPasswordConfirm, Boolean isNickname, Boolean isTermOfUse) {
        this.isExistEmail = isExistEmail;
        this.isExistNickname = isExistNickname;
        this.isEmail = isEmail;
        this.isPassword = isPassword;
        this.isPasswordConfirm = isPasswordConfirm;
        this.isNickname = isNickname;
        this.isTermOfUse = isTermOfUse;
        observer = new ViewObserver();
    }

    public void setCallback(ObserverCallback callback) {
        observer.setCallback(callback);
    }


    public void isExistEmail(Boolean existEmail) {
        isExistEmail = existEmail;
    }

    public void isExistNickname(Boolean existNickname) {
        isExistNickname = existNickname;
    }

    public void isEmail(Boolean email) {
        isEmail = email;
    }

    public void isPassword(Boolean password) {
        isPassword = password;
    }

    public void isPasswordConfirm(Boolean passwordConfirm) {
        isPasswordConfirm = passwordConfirm;
    }

    public void isNickname(Boolean nickname) {
        isNickname = nickname;
    }

    public void isTermOfUse(Boolean termOfUse) {
        isTermOfUse = termOfUse;
    }

    public Boolean getExistEmail() {
        return isExistEmail;
    }

    public Boolean getExistNickname() {
        return isExistNickname;
    }

    public Boolean getEmail() {
        return isEmail;
    }

    public Boolean getPassword() {
        return isPassword;
    }

    public Boolean getPasswordConfirm() {
        return isPasswordConfirm;
    }

    public Boolean getNickname() {
        return isNickname;
    }

    public Boolean getTermOfUse() {
        return isTermOfUse;
    }
}
