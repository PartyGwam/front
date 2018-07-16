package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterContract;

import java.util.List;

public interface PartyDetailContract {
    interface View {
        void toast(String msg);
        void onSuccess();
        void onAuthorization();
        void onBadRequest();
        void onSuccessUpdateParty(Data data);
        void onConnectFail();
        void updateComment(List<CommentSet> data);
        void onSuccessSendComment();
        void onForbidden();
    }

    interface Presenter {
        void getParty(String slug);
        void getComments(String slug);
        void attachView(View view);
        void detachView();
        void sendComment(String commentText, String slug);
        void setAdapterView(RepliesAdapterContract.View adapterView);
        void setAdapterModel(RepliesAdapterContract.Model adapterModel);
    }
}