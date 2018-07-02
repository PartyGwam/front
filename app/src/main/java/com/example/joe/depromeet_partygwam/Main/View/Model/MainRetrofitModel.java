package com.example.joe.depromeet_partygwam.Main.View.Model;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Join.Model.JoinModelCallback;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Response;

public class MainRetrofitModel {
    private static final String TAG = MainRetrofitModel.class.getSimpleName();
    private RetrofitService retrofitService;
    private MainModelCallback.RetrofitCallback callback;

    public MainRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(MainModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    //파티가 존재하는지
    //
/*
    public void validationEmail(String email) {
        String jsonStr = "{'email': '" + email + "'}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<Void> call = retrofitService.validateEmail(jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, response.code() + "/");
                if (response.code() == ResponseCode.BAD_REQUEST) {
                    //아이디가 있을 때
                    callback.onSuccessValidateEmail(ResponseCode.BAD_REQUEST);
                    return;
                }

                //아이디가 없을 때
                callback.onSuccessValidateEmail(ResponseCode.SUCCESS);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void validateNickname() {
        Call<Void> call = retrofitService.validateNickname();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == ResponseCode.BAD_REQUEST) {
                    //닉네임이 있을 때
                    callback.onSuccessValidateNickname(ResponseCode.BAD_REQUEST);
                    return;
                }

                //닉네임이 없을 떄
                callback.onSuccessValidateNickname(ResponseCode.SUCCESS);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void insertMember(User user) {

    }
    */
}

