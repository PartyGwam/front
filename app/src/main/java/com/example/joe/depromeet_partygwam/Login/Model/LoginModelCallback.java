package com.example.joe.depromeet_partygwam.Login.Model;

import com.example.joe.depromeet_partygwam.Data.LoginResponse.LoginResponse;

public interface LoginModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code, LoginResponse response);
        void onFailure();
    }
}
