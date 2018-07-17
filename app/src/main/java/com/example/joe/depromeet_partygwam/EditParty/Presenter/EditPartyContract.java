package com.example.joe.depromeet_partygwam.EditParty.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapterContract;
import com.example.joe.depromeet_partygwam.Retrofit.ResponseCode;

public interface EditPartyContract {
    interface View {
        void toast(String msg);
        void onUnauthorizedError();
        void onSuccess(String slug);
        void onBadRequest();
        void onForbidden();
        void onConnectFail();
    }

    interface Presenter {
        void editParty(String title, String slug, String place, String description, String startTime, int maxPeople);
        void attachView(View view);
        void detachView();
    }
}