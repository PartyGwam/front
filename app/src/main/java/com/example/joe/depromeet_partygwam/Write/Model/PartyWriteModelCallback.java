package com.example.joe.depromeet_partygwam.PartyWrite.Model;

public interface PartyWriteModelCallback {
    interface RetrofitModel {
        void onSuccess(int code);
        void onFailure();
    }
}
