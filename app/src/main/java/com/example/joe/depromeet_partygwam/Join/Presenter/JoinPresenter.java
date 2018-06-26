package com.example.joe.depromeet_partygwam.Join.Presenter;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Join.Model.JoinModelCallback;
import com.example.joe.depromeet_partygwam.Join.Model.JoinRetrofitModel;
import com.example.joe.depromeet_partygwam.Join.Model.RegularExpModel;

public class JoinPresenter implements JoinContract.Presenter,
        JoinModelCallback.RegularCallback, JoinModelCallback.RetrofitCallback {

    private JoinContract.View view;
    private RegularExpModel regularExpModel;
    private JoinRetrofitModel retrofitModel;

    public JoinPresenter() {
        regularExpModel = new RegularExpModel();
        regularExpModel.setCallback(this);
        retrofitModel = new JoinRetrofitModel();
        retrofitModel.setCallback(this);
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
    public void validateMember() {
        retrofitModel.validationMember();
    }

    @Override
    public void InsertMember(User user) {
        retrofitModel.insertMember(user);
    }

    @Override
    public void onSuccess(int code) {

    }

    @Override
    public void onFailure(int code) {

    }

    @Override
    public void validateEmail(String email) {
        regularExpModel.emailValidation(email);
    }

    @Override
    public void validatePassword(String password) {
        regularExpModel.passwordValidation(password);
    }

    @Override
    public void validatePasswordConfirm(String password) {
        regularExpModel.passwordConfirmValidation(password);
    }

    @Override
    public void validateNickname(String nickname) {
        regularExpModel.nicknameValidation(nickname);
    }

    @Override
    public void isEmailValidation(boolean b) {
        view.isEmail(b);
    }

    @Override
    public void isPasswordValidation(boolean b) {
        view.isPassword(b);
    }

    @Override
    public void isPasswordConfirmValidation(boolean b) {
        view.isPasswordConfirm(b);
    }

    @Override
    public void isNicknameValidation(boolean b) {
        view.isNickname(b);
    }
}
