package com.example.joe.depromeet_partygwam.PartyDetail.Model;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;

import java.util.List;

public interface PartyDetailModelCallback {
    interface RetrofitCallback {
        void onSuccessParty(int code, Data data);
        void onSuccessComment(int code, List<CommentSet> data);
        void onSuccessCommentSend(int code);
        void onFailure();
    }
}
