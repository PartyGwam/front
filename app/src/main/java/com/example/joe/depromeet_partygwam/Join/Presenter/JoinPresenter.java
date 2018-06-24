package com.example.joe.depromeet_partygwam.Join.Presenter;

import com.example.joe.depromeet_partygwam.Join.Model.JoinModelCallback;
import com.example.joe.depromeet_partygwam.Join.Model.RegularExpModel;

public class JoinPresenter implements JoinContract.Presenter, JoinModelCallback.RegularCallback {

    private JoinContract.View view;
    private RegularExpModel regularExpModel;

    public JoinPresenter() {
        regularExpModel = new RegularExpModel();
        regularExpModel.setCallback(this);
    }

    @Override
    public void attachView(JoinContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void emailValidation(String email) {
        regularExpModel.emailValidation(email);
    }

    @Override
    public void passwordValidation(String password) {
        regularExpModel.passwordValidation(password);
    }

    @Override
    public void nicknameValidation(String nickname) {
        regularExpModel.nicknameValidation(nickname);
    }

    @Override
    public void isEmailValidation(boolean b) {

    }

    @Override
    public void isPasswordValidation(boolean b) {

    }

    @Override
    public void isNicknameValidation(boolean b) {

    }
}
