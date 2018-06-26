package com.example.joe.depromeet_partygwam.Login.Model;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRetrofitModel {

    private LoginModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public LoginRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(LoginModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void getUser() {
        Call<User> call = retrofitService.getUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (response.code() == ResponseCode.UNAUTHORIZED) {

                    return;
                }

                if (response.code() != ResponseCode.SUCCESS) {

                    return;
                }

                callback.onSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(ResponseCode.UNkNOWN);
            }
        });
    }
}
