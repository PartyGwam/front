package com.example.joe.depromeet_partygwam.Login.Model;

import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;

public interface LoginModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code, UserResponse response);
        void onFailure();
    }
}
