package com.example.joe.depromeet_partygwam.Login.Presenter;

import com.example.joe.depromeet_partygwam.Data.LoginResponse.LoginResponse;
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
    public void onSuccess(int code, LoginResponse response) {
        view.startMainActivity(code, response);
    }

    @Override
    public void onFailure() {
        view.connectFail();
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
    public void login(String email, String password) {
        retrofitModel.login(email, password);
    }
}
