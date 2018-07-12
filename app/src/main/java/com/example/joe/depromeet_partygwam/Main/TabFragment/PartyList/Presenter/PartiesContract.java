package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Adapter.PartiesAdapterContract;

public interface PartiesContract {
    interface View {
        void toast(String msg);
        void onUnauthorizedError();
        void onUnknownError();
        void onSuccessGetList();
        void onConnectFail();
        void startDetailActivity(Data item);
        void onNotFound();
    }

    interface Presenter {
        void getParties(String search, int sort);
        void getCreatedParties();
        void getJoinedParties();
        void attchView(View view);
        void detachView();
        void setAdapterView(PartiesAdapterContract.View adapterView);
        void setAdapterModel(PartiesAdapterContract.Model adapterModel);
    }
}
