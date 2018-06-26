package com.example.joe.depromeet_partygwam.Join.Model;


import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinRetrofitModel {
    private RetrofitService retrofitService;
    private JoinModelCallback.RetrofitCallback callback;

    public JoinRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(JoinModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void validationMember() {
        Call<User> call = retrofitService.validateUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user =  response.body();
                if (response.code() != ResponseCode.SUCCESS) {
                    callback.onFailure(response.code());
                    return;
                }

                if (user != null) {

                    return;
                }

                callback.onSuccess(response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(ResponseCode.UNkNOWN);
            }
        });
    }

    public void insertMember(User user) {

    }
}
