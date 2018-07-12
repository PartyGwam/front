package com.example.joe.depromeet_partygwam.Retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {
    private static String url = "https://partygwam-staging.herokuapp.com";
    private static Retrofit retrofit;
    private static RetrofitService retrofitService;

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            return retrofitService = retrofit.create(RetrofitService.class);
        }
        return retrofitService;
    }
}
