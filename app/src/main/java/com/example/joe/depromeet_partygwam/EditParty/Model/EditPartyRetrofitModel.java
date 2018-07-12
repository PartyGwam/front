package com.example.joe.depromeet_partygwam.EditParty.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesModelCallback;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPartyRetrofitModel {
    private EditPartyModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public EditPartyRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(EditPartyModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void updateParty(String title, String place, String description, String startTime, int maxPeople) {
        String jsonStr = "{ " +
                "'title': '" + title + "'," +
                "'place': '" + place + "'," +
                "'description': '" + description + "'," +
                "'start_time': '" + startTime + "'," +
                "'max_people': " + maxPeople +
                "}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<Void> call = retrofitService.editParty(SharePreferenceManager.getString("Token"), jsonObject);
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
