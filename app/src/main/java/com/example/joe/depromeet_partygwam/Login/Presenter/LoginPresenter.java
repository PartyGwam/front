package com.example.joe.depromeet_partygwam.Login.Presenter;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Login.Model.LoginRetrofitModel;
import com.example.joe.depromeet_partygwam.Login.Model.LoginModelCallback;

public class Presenter implements LoginContract.Presenter, LoginModelCallback.RetrofitCallback {

    private LoginContract.View view;
    private LoginModelCallback.RetrofitCallback retrofitCallback;
    private LoginRetrofitModel retrofitModel;

    public Presenter() {

    }

    @Override
    public void onSuccess(int code) {

    }

    @Override
    public void onFailure(int code) {

    }

    @Override
    public void attachView(LoginContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void validateUser(User user) {

    }
}
