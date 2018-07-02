package com.example.joe.depromeet_partygwam.Retrofit;

import com.example.joe.depromeet_partygwam.Data.LoginResponse;
import com.example.joe.depromeet_partygwam.Data.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
}
