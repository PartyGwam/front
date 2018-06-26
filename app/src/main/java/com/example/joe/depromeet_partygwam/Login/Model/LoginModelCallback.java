package com.example.joe.depromeet_partygwam.Login.Model;

import com.example.joe.depromeet_partygwam.Data.User;

public interface LoginModelCallback {
    interface RetrofitCallback {
        void onSuccess(User user);
        void onFailure(int code);
    }
}
