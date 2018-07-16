package com.example.joe.depromeet_partygwam.PartyDetail.Model;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyOneResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.ReplyResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Join.Model.JoinRetrofitModel;
import com.example.joe.depromeet_partygwam.PartyDetail.View.PartyDetailActivity;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyDetailRetrofitModel {
    private static final String TAG = PartyDetailRetrofitModel.class.getSimpleName();
    private PartyDetailModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public PartyDetailRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(PartyDetailModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void getParty(String slug) {
        String token = SharePreferenceManager.getString("Token");
        Call<PartyOneResponse> call = retrofitService.getParty(token, slug);

        call.enqueue(new Callback<PartyOneResponse>() {
            @Override
            public void onResponse(Call<PartyOneResponse> call, Response<PartyOneResponse> response) {
                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccessParty(ResponseCode.NOT_FOUND, null);
                    return;
                }
                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccessParty(ResponseCode.UNAUTHORIZED, null);
                    return;
                }
                Data data = response.body().getData();
                callback.onSuccessParty(ResponseCode.SUCCESS, data);
            }

            @Override
            public void onFailure(Call<PartyOneResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });

    }

    public void getComments(String slug){
        String token = SharePreferenceManager.getString("Token");
        Call<ReplyResponse> comments = retrofitService.getComments(token, slug.trim());

        comments.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {
                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccessComment(ResponseCode.NOT_FOUND, null);
                    return;
                }
                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccessComment(ResponseCode.UNAUTHORIZED, null);
                    return;
                }
                List<CommentSet> datas = response.body().getResult();
                callback.onSuccessComment(ResponseCode.SUCCESS, datas);
            }

            @Override
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void sendComment(String comment, String slug){
        String jsonStr = "{'text': '" + comment + "'}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<Void> call = retrofitService.sendComment(SharePreferenceManager.getString("Token"), slug, jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG, response.code() + "/");
                if (response.code() == ResponseCode.FORBIDDEN) {
                    callback.onSuccessCommentSend(ResponseCode.FORBIDDEN);
                    return;
                }
                if(response.code() ==  ResponseCode.UNAUTHORIZED){
                    callback.onSuccessCommentSend(ResponseCode.UNAUTHORIZED);
                    return;
                }
                callback.onSuccessCommentSend(ResponseCode.COMMENT_SUCCESS);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
