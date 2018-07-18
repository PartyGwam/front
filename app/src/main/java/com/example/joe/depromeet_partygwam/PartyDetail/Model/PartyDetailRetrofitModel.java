package com.example.joe.depromeet_partygwam.PartyDetail.Model;

import android.util.Log;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Message;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.ParticipantResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyOneResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.ReplyResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.PartyDetail.View.PartyDetailActivity;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class PartyDetailRetrofitModel {
    private static final String TAG = PartyDetailRetrofitModel.class.getSimpleName();
    private PartyDetailModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;
    private String token;

    public PartyDetailRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
        token = SharePreferenceManager.getString("Token");
    }

    public void setCallback(PartyDetailModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void getPartyContents() {
        Call<PartyOneResponse> call = retrofitService.getParty(token, PartyDetailActivity.SLUG.trim());

        call.enqueue(new Callback<PartyOneResponse>() {
            @Override
            public void onResponse(Call<PartyOneResponse> call, Response<PartyOneResponse> response) {
                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onAuthorizationError();
                    return;
                }

                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccessContentsLoad(ResponseCode.NOT_FOUND, null);
                    return;
                }

                if (response.code() == ResponseCode.SUCCESS) {
                    Data data = response.body().getData();
                    callback.onSuccessContentsLoad(ResponseCode.SUCCESS, data);
                }
            }

            @Override
            public void onFailure(Call<PartyOneResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });

    }

    public void getParticipants() {
        Call<ParticipantResponse> call = retrofitService.getParticipants(token, PartyDetailActivity.SLUG.trim());
        call.enqueue(new Callback<ParticipantResponse>() {
            @Override
            public void onResponse(Call<ParticipantResponse> call, Response<ParticipantResponse> response) {
                if (response.code() == ResponseCode.SUCCESS) {
                    List<Participant> participants = response.body().getResult().getParticipants();
                    callback.onSuccessParticipantsLoad(ResponseCode.SUCCESS, participants);
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onAuthorizationError();
                    return;
                }

                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccessParticipantsLoad(ResponseCode.NOT_FOUND, null);
                    return;
                }
            }

            @Override
            public void onFailure(Call<ParticipantResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void getComments(){
        Call<ReplyResponse> comments = retrofitService.getComments(token, PartyDetailActivity.SLUG.trim());
        comments.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {
                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccessCommentLoad(ResponseCode.NOT_FOUND, null);
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onAuthorizationError();
                    return;
                }

                if (response.code() == ResponseCode.SUCCESS) {
                    List<CommentSet> datas = response.body().getResult();
                    callback.onSuccessCommentLoad(ResponseCode.SUCCESS, datas);
                }
            }

            @Override
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void sendComment(String comment){
        String jsonStr = "{'text': '" + comment + "'}";
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonStr);
        Call<Void> call = retrofitService.sendComment(token, PartyDetailActivity.SLUG, jsonObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == ResponseCode.FORBIDDEN) {
                    callback.onSuccessCommentSend(ResponseCode.FORBIDDEN);
                    return;
                }
                if (response.code() ==  ResponseCode.UNAUTHORIZED){
                    callback.onAuthorizationError();
                    return;
                }

                if (response.code() == ResponseCode.CREATED) {
                    callback.onSuccessCommentSend(ResponseCode.CREATED);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void joinParty() {
        Call<ParticipantResponse> call = retrofitService.joinParty(token, PartyDetailActivity.SLUG);
        call.enqueue(new Callback<ParticipantResponse>() {
            @Override
            public void onResponse(Call<ParticipantResponse> call, Response<ParticipantResponse> response) {
                if (response.code() == ResponseCode.CREATED) {
                    callback.onSuccessJoinedParty(ResponseCode.CREATED, "참가되었습니다.");
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onAuthorizationError();
                    return;
                }

                if (response.code() == ResponseCode.BAD_REQUEST) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String msg = jsonObject.getString("message");
                        callback.onSuccessJoinedParty(ResponseCode.BAD_REQUEST,
                                msg.substring(2, msg.length() - 2));
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ParticipantResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void leaveParty() {
        Call<ParticipantResponse> call = retrofitService.leaveParty(token, PartyDetailActivity.SLUG);
        call.enqueue(new Callback<ParticipantResponse>() {
            @Override
            public void onResponse(Call<ParticipantResponse> call, Response<ParticipantResponse> response) {
                if (response.code() == ResponseCode.NO_CONTENT) {
                    callback.onSuccessLeavedParty(ResponseCode.NO_CONTENT, "파티를 떠났습니다");
                    return;
                }

                if (response.code() == ResponseCode.BAD_REQUEST) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String msg = jsonObject.getString("message");
                        callback.onSuccessLeavedParty(ResponseCode.BAD_REQUEST,
                                msg.substring(2, msg.length() - 2));
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onAuthorizationError();
                    return;
                }
            }

            @Override
            public void onFailure(Call<ParticipantResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    public void deleteComment(String commentSlug) {
        Call<Void> call = retrofitService.deleteComment(token, PartyDetailActivity.SLUG, commentSlug);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == ResponseCode.NO_CONTENT) {
                    callback.onSuccessCommentDelete(ResponseCode.NO_CONTENT);
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onAuthorizationError();
                    return;
                }

                if (response.code() == ResponseCode.FORBIDDEN) {
                    callback.onSuccessCommentDelete(ResponseCode.FORBIDDEN);
                    return;
                }

                if (response.code() == ResponseCode.NOT_FOUND) {
                    callback.onSuccessCommentDelete(ResponseCode.NOT_FOUND);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
