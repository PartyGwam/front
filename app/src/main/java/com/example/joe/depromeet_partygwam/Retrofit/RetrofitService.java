package com.example.joe.depromeet_partygwam.Retrofit;

import com.example.joe.depromeet_partygwam.Data.Parties.Participant.ParticipantResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyOneResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.ReplyResponse;
import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("/api/parties/{slug}/")
    Call<PartyOneResponse> getParty(
            @Header("Authorization") String authorization,
            @Path("slug") String slug
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

    @PUT("/api/parties/{slug}/")
    Call<PartyOneResponse> editParty(
            @Header("Authorization") String authorization,
            @Path("slug") String slug,
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

    @DELETE("/api/parties/{slug}/comments/{comment_slug}/")
    Call<Void> deleteComment(
            @Header("Authorization") String authorization,
            @Path("slug") String slug,
            @Path("comment_slug") String commentSlug
    );

    @GET("/api/parties/{slug}/participants/")
    Call<ParticipantResponse> getParticipants(
            @Header("Authorization") String authorization,
            @Path("slug") String slug
    );

    @POST("/api/parties/{slug}/participants/")
    Call<ParticipantResponse> joinParty(
            @Header("Authorization") String authorization,
            @Path("slug") String slug
    );

    @DELETE("/api/parties/{slug}/participants/")
    Call<ParticipantResponse> leaveParty(
            @Header("Authorization") String authorization,
            @Path("slug") String slug
    );
}
