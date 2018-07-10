package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Model.SettingModelCallback;
import com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Model.SettingRetrofitModel;

public class SettingProfilePresenter implements SettingProfileContract.Presenter,
        SettingModelCallback.RetrofitCallback {
    private SettingProfileContract.View view;
    private SettingRetrofitModel retrofitModel;

    public SettingProfilePresenter(Context context) {
        retrofitModel = new SettingRetrofitModel(context);
        retrofitModel.setCallback(this);
    }

    @Override
    public void attachView(SettingProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void updateUser(String username, Bitmap bitmap) {
        retrofitModel.updateUser(username, bitmap);
    }

    @Override
    public void onSuccess(int code) {

    }

    @Override
    public void onFailure() {

    }
}
