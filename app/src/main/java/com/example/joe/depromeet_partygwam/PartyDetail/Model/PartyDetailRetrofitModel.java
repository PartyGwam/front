package com.example.joe.depromeet_partygwam.PartyDetail.Model;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
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

    public void getParty() {
        //해당하는 파티 받아오기...
        //String partyId =
        String token = SharePreferenceManager.getString("Token");
        //Call<PartyResponse> call = retrofitService.getParties(token, null, page);

    }

    public void getComments(String slug){
        String token = SharePreferenceManager.getString("Token");
        Call<ReplyResponse> comments = retrofitService.getComments(token, slug.trim());

        comments.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {
                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccess(ResponseCode.NOT_FOUND, null);
                    return;
                }
                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccess(ResponseCode.UNAUTHORIZED, null);
                    return;
                }
                List<CommentSet> datas = response.body().getResult();
                callback.onSuccess(ResponseCode.SUCCESS, datas);

            }

            @Override
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void updateParty() {
        //서버에 변경된 정보 보내는 코드작성

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

    public void editParty(String title, String place, String description, String startTime, int maxPeople) {
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
                    //callback.onSuccess(ResponseCode.BAD_REQUEST);
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    //callback.onSuccess(ResponseCode.UNAUTHORIZED);
                    return;
                }

                //callback.onSuccess(ResponseCode.SUCCESS);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });

    }
}
