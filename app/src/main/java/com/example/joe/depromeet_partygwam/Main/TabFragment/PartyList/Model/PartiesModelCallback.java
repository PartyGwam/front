package com.example.joe.depromeet_partygwam.Main.TabFragment.PartyList.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.PartyResponse;

import java.util.List;

public interface PartiesModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code, List<Data> data);
        void onFailure();
    }
}
