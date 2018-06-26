package com.example.joe.depromeet_partygwam.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {
    private static String url = "http://www.mocky.io/v2/5b31c8ce310000123b1293c0/";
    private static Retrofit retrofit;
    private static RetrofitService retrofitService;

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofitService = retrofit.create(RetrofitService.class);
        }
        return retrofitService;
    }
}
