package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartiesRetrofitModel {
    private PartiesModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public PartiesRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(PartiesModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void getParties(String search, int sort, int page) {
        String ordering = sort == 0 ? "start_time" : "-created_at";
        String token = SharePreferenceManager.getString("Token");
        Call<PartyResponse> call = retrofitService.getParties(token, search, ordering, page);
        call.enqueue(new Callback<PartyResponse>() {
            @Override
            public void onResponse(Call<PartyResponse> call, Response<PartyResponse> response) {
                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccess(ResponseCode.NOT_FOUND, null);
                    return;
                }
                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccess(ResponseCode.UNAUTHORIZED, null);
                    return;
                }
                List<Data> datas = response.body().getResult().getData();
                callback.onSuccess(ResponseCode.SUCCESS, datas);
            }

            @Override
            public void onFailure(Call<PartyResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
