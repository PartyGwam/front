package com.example.joe.depromeet_partygwam.Retrofit;

import com.example.joe.depromeet_partygwam.Data.LoginResponse.LoginResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @POST("api/users/validate/email/")
    Call<Void> validateEmail(
            @Body JsonObject email
    );

    @POST("api/users/validate/username/")
    Call<Void> validateNickname(
            @Body JsonObject username
    );

    @POST("/api/users/")
    Call<Void> insertUser(
            @Body JsonObject userData
    );

    @POST("/api/users/login/")
    Call<LoginResponse> login(
            @Body JsonObject loginData
    );

    @GET("/api/parties/")
    Call<PartyResponse> getParties(
            @Header("Authorization") String authorization,
            @Query("search") String search,
            @Query("ordering") String ordering,
            @Query("page") int page
    );

    @POST("/api/parties/")
    Call<Void> insertParty(
            @Header("Authorization") String authorization,
            @Body JsonObject party
    );

    @GET("/api/parties/created/")
    Call<PartyResponse> getCreatedParties(
            @Header("Authorization") String authorization,
            @Query("page") int page
    );

    @GET("/api/parties/joined/")
    Call<PartyResponse> getJoinedParties(
            @Header("Authorization") String authorization,
            @Query("page") int page
    );
}
