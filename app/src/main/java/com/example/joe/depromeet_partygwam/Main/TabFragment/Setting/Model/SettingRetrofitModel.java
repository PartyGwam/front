package com.example.joe.depromeet_partygwam.Main.TabFragment.Setting.Model;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.joe.depromeet_partygwam.Data.UserResponse.UserResponse;
import com.example.joe.depromeet_partygwam.DataStore.SharePreferenceManager;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitService;
import com.example.joe.depromeet_partygwam.Retrofit.RetrofitServiceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingRetrofitModel {
    private RetrofitService retrofitService;
    private SettingModelCallback.RetrofitCallback callback;
    private Context context;

    public SettingRetrofitModel(Context context) {
        retrofitService = RetrofitServiceManager.getInstance();
        this.context = context;
    }

    public void setCallback(SettingModelCallback.RetrofitCallback callback) {
        this.callback = callback;
    }

    public void updateUser(String username, Bitmap bitmap) {
        File file = bitmapToFile(bitmap, SharePreferenceManager.getString("Username") + ".png");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("profile_picture", file.getName(), requestBody);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/plain"), username);
        updateUserCall(body, requestBody2);
    }

    public void updateUser(String username) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), username);
        updateUserCall(null, requestBody);
    }

    private void updateUserCall(MultipartBody.Part body, RequestBody requestBody) {
        Call<UserResponse> call = retrofitService.updateUser(
                SharePreferenceManager.getString("Token"), body, requestBody);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == ResponseCode.SUCCESS) {
                    callback.onSuccess(ResponseCode.SUCCESS, response.body());
                    return;
                }

                if (response.code() == ResponseCode.BAD_REQUEST) {
                    callback.onSuccess(ResponseCode.BAD_REQUEST, null);
                    return;
                }

                if (response.code() == ResponseCode.FORBIDDEN) {
                    callback.onSuccess(ResponseCode.FORBIDDEN, null);
                    return;
                }

                if (response.code() == ResponseCode.UNAUTHORIZED) {
                    callback.onSuccess(ResponseCode.UNAUTHORIZED, null);
                    return;
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }

    private File bitmapToFile(Bitmap bitmap, String fileName) {
        File file = new File(context.getCacheDir(), fileName);
        OutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
