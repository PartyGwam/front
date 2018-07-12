package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;
import com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Model.SettingModelCallback;
import com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Model.SettingRetrofitModel;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

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
        if (bitmap == null) {
            retrofitModel.updateUser(username);
            return;
        }
        retrofitModel.updateUser(username, bitmap);
    }

    @Override
    public void onSuccess(int code, UserResponse data) {
        if (code == ResponseCode.SUCCESS) {
            view.onSuccess(data);
            return;
        }

        if (code == ResponseCode.BAD_REQUEST) {
            view.onSupportError();
            return;
        }

        if (code == ResponseCode.UNAUTHORIZED) {
            view.onUnauthorizedError();
            return;
        }

        if (code == ResponseCode.FORBIDDEN) {
            view.onForbiddenError();
            return;
        }

        view.onUsernameOverlap();
    }

    @Override
    public void onFailure() {
        view.onConnectFail();
    }
}
