package com.example.joe.depromeet_partygwam.PartyWrite.Model;


import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteRetrofitModel {
    private PartyWriteModelCallback.RetrofitModel callback;
    private RetrofitService retrofitService;

    public WriteRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(PartyWriteModelCallback.RetrofitModel callback) {
        this.callback = callback;
    }

    public void insertParty(String title, String place, String description, String startTime, int maxPeople) {
        String jsonStr = "{ " +
                "'title': '" + title + "'," +
                "'place': '" + place + "'," +
                "'description': '" + description + "'," +
                "'start_time': '" + startTime + "'," +
                "'max_people': " + maxPeople +
                "}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<Void> call = retrofitService.insertParty(SharePreferenceManager.getString("Token"), jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == ResponseCode.BAD_REQUEST) {
                    callback.onSuccess(ResponseCode.BAD_REQUEST);
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccess(ResponseCode.UNAUTHORIZED);
                    return;
                }

                callback.onSuccess(ResponseCode.SUCCESS);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
