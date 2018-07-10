package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Presenter;

import android.graphics.Bitmap;

import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;

public interface SettingProfileContract {
    interface View {
        void toast(String msg);
        void onUnauthorizedError();
        void onForbiddenError();
        void onSuccess(UserResponse data);
        void onSupportError();
        void onConnectFail();
        void onUsernameOverlap();
    }

    interface Presenter {
        void attachView(View view);
        void updateUser(String username, Bitmap bitmap);
    }
}
