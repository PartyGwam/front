package com.example.joe.depromeet_partygwam.PartyDetail.Presenter;

import com.example.joe.depromeet_partygwam.Data.Parties.CommentSet;
import com.example.joe.depromeet_partygwam.Data.Parties.Data;
import com.example.joe.depromeet_partygwam.Data.Parties.Participant.Participant;
import com.example.joe.depromeet_partygwam.PartyDetail.Adapter.RepliesAdapterContract;

import java.util.List;

public interface PartyDetailContract {
    interface View {
        void toast(String msg);

        void onSuccessContentsLoad(Data data);
        void onNotFoundContentsLoad();

        void onSuccessPartyModify();
        void onBadRequestPartyModify();

        void onSuccessPartyDelete();
        void onBadRequestPartyDelete();

        void onSuccessParticipantsLoad(List<Participant> participants);
        void onNotFoundParticipantsLoad();

        void onSuccessParticipantsJoin(String msg);
        void onBadRequestParticipantsJoin(String msg);

        void onSuccessParticipantsCancel(String msg);
        void onBadrequestParticipantsCancel(String msg);

        void onSuccessOwnerUpdate();
        void onBadRequestOwnerUpdate();

        void onSuccessCommentsLoad(List<CommentSet> comments);
        void onNotFoundCommentsLoad();

        void onSuccessCommentsUpdate();

        void onSuccessCommentModify();

        void onSuccessCommentDelete();

        void onAuthorization();
        void onForbidden(String msg);

        void onConnectFail();

        void updateComments(List<CommentSet> data);
        void updateContents(Data data);
        void updateProfileImages(List<Participant> participants);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();

        void setAdapterView(RepliesAdapterContract.View adapterView);
        void setAdapterModel(RepliesAdapterContract.Model adapterModel);

        void getPartyContents();
        void getParticipants();
        void getComments();

        void sendComment(String commentText, String slug);
        void deleteComment();
        void updateComment();

        void joinParty();
        void leaveParty();

        void updateOwner();
    }
}