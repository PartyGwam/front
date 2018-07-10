package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Model;

public interface SettingModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code);
        void onFailure();
    }
}
