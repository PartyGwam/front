package com.example.joe.depromeet_partygwam.Main.Model;

import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;

public class MainRetrofitModel {
    private static final String TAG = MainRetrofitModel.class.getSimpleName();
    private RetrofitService retrofitService;
    private MainModelCallback.RetrofitCallback callback;

    public MainRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(MainModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }
}

