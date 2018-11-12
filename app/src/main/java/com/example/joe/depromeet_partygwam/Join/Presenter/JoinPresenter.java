package com.example.joe.depromeet_partygwam.Join.Presenter;

import com.example.joe.depromeet_partygwam.Data.UserResponse.User;
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
    public void validateExistEmail(String email) {
        retrofitModel.validationEmail(email);
    }

    @Override
    public void validateExistNickname(String nickname) {
        retrofitModel.validateNickname(nickname);
    }

    @Override
    public void insertUser(User user) {
        retrofitModel.insertUser(user);
    }

    @Override
    public void onSuccessValidateEmail(int code) {
        view.isExistEmail(code);
    }

    @Override
    public void onSuccessValidateNickname(int code) {
        view.isExistNickname(code);
    }

    @Override
    public void onSuccessJoin(int code) {
        view.startLoginActivity(code);
    }

    @Override
    public void onFailure() {
        view.setProgressbar(false);
    }

    @Override
    public void validateRegularExpEmail(String email) {
        regularExpModel.emailValidation(email);
    }

    @Override
    public void validateRegularExpPassword(String password) {
        regularExpModel.passwordValidation(password);
    }

    @Override
    public void validateRegularExpPasswordConfirm(String password) {
        regularExpModel.passwordConfirmValidation(password);
    }

    @Override
    public void validateRegularExpNickname(String nickname) {
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
