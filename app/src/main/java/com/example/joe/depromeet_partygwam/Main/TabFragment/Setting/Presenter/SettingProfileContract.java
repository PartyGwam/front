package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Presenter;

import android.graphics.Bitmap;

public interface SettingProfileContract {
    interface View {
        void toast(String msg);
    }

    interface Presenter {
        void attachView(View view);
        void updateUser(String username, Bitmap bitmap);
    }
}
