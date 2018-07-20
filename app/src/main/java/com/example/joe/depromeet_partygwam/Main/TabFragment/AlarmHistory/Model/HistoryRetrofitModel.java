package com.example.joe.depromeet_partygwam.Main.TabFragment.AlarmHistory.Model;


import com.example.joe.depromeet_partygwam.Data.Parties.History.HistoryResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryRetrofitModel {
    private RetrofitService retrofitService;
    private HistoryModelCallback.RetrofitCallback callback;

    public HistoryRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(HistoryModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void getHistory() {
        Call<HistoryResponse> call = retrofitService.getHistory(SharePreferenceManager.getString("Token"));
        call.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if (response.code() == ResponseCode.SUCCESS) {
                    callback.onSuccessHistoryLoad(ResponseCode.SUCCESS, response.body().getResult().getHistoryData());
                    return;
                }

                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccessHistoryLoad(ResponseCode.NOT_FOUND, null);
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccessHistoryLoad(ResponseCode.UNAUTHORIZED, null);
                    return;
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
}
