package com.example.joe.depromeet_partygwam.Join.Model;


import com.example.joe.depromeet_partygwam.Join.Data.Member;
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
        Call<Member> call = retrofitService.validationMember();
        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member member =  response.body();
                if (response.code() != ResponseCode.SUCCESS) {
                    callback.onFailure(response.code());
                    return;
                }

                if (member != null) {

                    return;
                }

                callback.onSuccess(response.code());
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure(ResponseCode.NOT_FOUND);
            }
        });
    }

    public void insertMember(Member member) {

    }
}
