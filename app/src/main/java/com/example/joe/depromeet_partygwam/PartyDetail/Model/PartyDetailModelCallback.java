package com.example.joe.depromeet_partygwam.PartyDetail.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;

import java.util.List;

public interface PartyDetailModelCallback {
    interface RetrofitCallback {
        void onSuccess(int code, List<CommentSet> data);
        void onFailure();
    }
}
