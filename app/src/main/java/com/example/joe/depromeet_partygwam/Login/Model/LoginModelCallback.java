package com.example.joe.depromeet_partygwam.Login.Model;

import com.example.joe.depromeet_partygwam.Data.LoginResponse;
import com.example.joe.depromeet_partygwam.Data.User;

public interface LoginModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code, LoginResponse response);
        void onFailure();
    }
}
