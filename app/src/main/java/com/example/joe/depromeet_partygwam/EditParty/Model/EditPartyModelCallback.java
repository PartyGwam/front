package com.example.joe.depromeet_partygwam.EditParty.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;

public interface EditPartyModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code, String slug, String msg);
        void onFailure();
    }
}
