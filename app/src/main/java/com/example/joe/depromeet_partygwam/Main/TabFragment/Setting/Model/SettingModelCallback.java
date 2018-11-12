package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Model;

import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;

public interface SettingModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code, UserResponse data);
        void onFailure();
    }
}
