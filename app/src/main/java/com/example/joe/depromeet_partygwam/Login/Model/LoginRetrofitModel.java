package com.example.joe.depromeet_partygwam.Login.Model;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.LoginResponse.LoginResponse;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRetrofitModel {
    private static final String TAG = LoginRetrofitModel.class.getSimpleName();
    private LoginModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public LoginRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(LoginModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void login(String email, String password) {
        String jsonStr = "{" +
                "'email': '" + email + "'," +
                "'password': '" + password + "'" +
                "}";
        Log.d(TAG, jsonStr);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<LoginResponse> call = retrofitService.login(jsonObject);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.code() == ResponseCode.BAD_REQUEST) {
                    callback.onSuccess(ResponseCode.BAD_REQUEST, null);
                    return;
                }

                callback.onSuccess(ResponseCode.SUCCESS, response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
