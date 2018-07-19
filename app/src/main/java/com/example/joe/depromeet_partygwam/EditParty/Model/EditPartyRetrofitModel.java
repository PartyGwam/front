package com.example.joe.depromeet_partygwam.EditParty.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyOneResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model.PartiesModelCallback;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

    public void editParty(String title, String slug, String place, String description, String startTime, int maxPeople) {
        String jsonStr = "{ " +
                "'title': '" + title + "'," +
                "'place': '" + place + "'," +
                "'description': '" + description + "'," +
                "'start_time': '" + startTime + "'," +
                "'max_people': " + maxPeople +
                "}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<PartyOneResponse> call = retrofitService.editParty(SharePreferenceManager.getString("Token"), slug.trim(), jsonObject);
        call.enqueue(new Callback<PartyOneResponse>() {
            @Override
            public void onResponse(Call<PartyOneResponse> call, Response<PartyOneResponse> response) {
                if (response.code() == ResponseCode.BAD_REQUEST) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String msg = jsonObject.getString("message");
                        callback.onSuccess(ResponseCode.BAD_REQUEST, null,
                                msg.substring(2, msg.length() - 2));
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccess(ResponseCode.UNAUTHORIZED,null, null);
                    return;
                }

                if (response.code() == ResponseCode.FORBIDDEN) {
                    callback.onSuccess(ResponseCode.FORBIDDEN, null, null);
                    return;

                }
                if (response.code() == ResponseCode.SUCCESS) {
                    callback.onSuccess(ResponseCode.SUCCESS, response.body().getData().getSlug(), null);
                    return;
                }

                callback.onUnknownError();
            }

            @Override
            public void onFailure(Call<PartyOneResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
