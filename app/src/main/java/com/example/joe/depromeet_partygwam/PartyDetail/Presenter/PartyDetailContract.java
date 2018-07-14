package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterConstract;

import java.util.List;

public interface PartyDetailContract {
    interface View {
        void toast(String msg);
        void onSuccess();
        void onAuthorization();
        void onBadRequest();
        //void startCommentWriteActivity();
        void onConnectFail();
        void updateComment(List<CommentSet> data);
    }

    interface Presenter {
        void getParty(Integer partyId);
        void getComments(String slug);
        void attachView(View view);
        void detachView();
        void updateParty();
        void editParty(String title, String place, String description, String startTime, int maxPeople);
        void setAdapterView(RepliesAdapterConstract.View adapterView);
        void setAdapterModel(RepliesAdapterConstract.Model adapterModel);

    }
}