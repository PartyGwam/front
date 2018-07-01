package com.example.joe.depromeet_partygwam.Join.Model;


import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.User;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinRetrofitModel {
    private static final String TAG = JoinRetrofitModel.class.getSimpleName();
    private RetrofitService retrofitService;
    private JoinModelCallback.RetrofitCallback callback;

    public JoinRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(JoinModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

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

    public void validateNickname(String nickname) {
        String jsonStr = "{'username': '" + nickname + "'}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<Void> call = retrofitService.validateNickname(jsonObject);
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

    public void insertUser(User user) {
        String jsonStr = "{" +
                "'email': '" + user.getEmail() + "'," +
                "'username': '" + user.getUsername() + "'," +
                "'password': ':" + user.getPassword() + "'" +
                "}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<Void> call = retrofitService.insertUser(jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == ResponseCode.BAD_REQUEST) {
                    callback.onSuccessJoin(ResponseCode.BAD_REQUEST);
                    return;
                }
                callback.onSuccessJoin(ResponseCode.SUCCESS);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
