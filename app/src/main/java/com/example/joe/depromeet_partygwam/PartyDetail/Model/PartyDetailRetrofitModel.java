package com.example.joe.depromeet_partygwam.PartyDetail.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.PartyDetail.View.PartyDetailActivity;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;

import retrofit2.Call;

public class PartyDetailRetrofitModel {
    private static final String TAG = PartyDetailRetrofitModel.class.getSimpleName();
    private PartyDetailModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public PartyDetailRetrofitModel(){
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(PartyDetailModelCallback.RetrofitCallback callback){
        this.callback = callback;
    }

    public void getParty(){
        //해당하는 파티 받아오기...
        //String partyId =
        String token = SharePreferenceManager.getString("Token");
        //Call<PartyResponse> call = retrofitService.getParties(token, null, page);

    }

    public void updateParty(){
        //서버에 변경된 정보 보내는 코드작성

    }
}
