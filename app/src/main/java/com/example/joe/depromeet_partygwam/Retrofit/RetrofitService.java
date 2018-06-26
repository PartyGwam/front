package com.example.joe.depromeet_partygwam.Retrofit;

import com.example.joe.depromeet_partygwam.Join.Data.Member;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/members")
    Call<Member> validationMember();
}
