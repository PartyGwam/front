package com.example.joe.depromeet_partygwam.Retrofit;

import com.example.joe.depromeet_partygwam.Data.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/")
    Call<User> validateUser();

    @GET("/test.php")
    Call<User> getUser();
}
