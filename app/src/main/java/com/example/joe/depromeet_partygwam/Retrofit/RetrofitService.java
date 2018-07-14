package com.example.joe.depromeet_partygwam.Retrofit;

import com.example.joe.depromeet_partygwam.Data.Parties.ReplyResponse;
import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
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
    Call<UserResponse> login(
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

    @Multipart
    @POST("/api/profiles/")
    Call<UserResponse> updateUser(
            @Header("Authorization") String authorization,
            @Part MultipartBody.Part image,
            @Part("username") RequestBody username
    );

    @POST("/api/parties/")
    Call<Void> editParty(
            @Header("Authorization") String authorization,
            @Body JsonObject party
    );

    @GET("/api/parties/{slug}/comments/")
    Call<ReplyResponse> getComments(
            @Header("Authorization") String authorization,
            @Path("slug") String slug
    );

    @POST("/api/parties/{slug}/comments/")
    Call<Void> sendComment(
            @Header("Authorization") String authorization,
            @Path("slug") String slug,
            @Body JsonObject data
    );
}
