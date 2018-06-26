package com.example.joe.depromeet_partygwam.Login.Presenter;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Login.Model.LoginRetrofitModel;
import com.example.joe.depromeet_partygwam.Login.Model.LoginModelCallback;

public class LoginPresenter implements LoginContract.Presenter, LoginModelCallback.RetrofitCallback {

    private LoginContract.View view;
    private LoginRetrofitModel retrofitModel;

    public LoginPresenter() {
        retrofitModel = new LoginRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void onSuccess(User user) {
        view.onSuccess(user);
    }

    @Override
    public void onFailure(int code) {

    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void validateUser(User user) {
        retrofitModel.getUser();
    }
}
