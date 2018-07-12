package com.example.joe.depromeet_partygwam.EditParty.Model;

public interface EditPartyModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code);
        void onFailure();
    }
}
