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

        void onSuccessPartyDelete();
        void onBadRequestPartyDelete();

        void onSuccessParticipantsLoad(List<Participant> participants);
        void onNotFoundParticipantsLoad();

        void onSuccessPartyJoin(String msg);
        void onBadRequestPartyJoin(String msg);

        void onSuccessPartyLeave(String msg);
        void onBadrequestPartyLeave(String msg);

        void onSuccessOwnerLoad(String owner);
        void onBadRequestOwnerLoad();

        void onSuccessOwnerUpdate();
        void onBadRequestOwnerUpdate();

        void onSuccessCommentsLoad(List<CommentSet> comments);
        void onNotFoundCommentsLoad();

        void onSuccessCommentUpdate();

        void onSuccessCommentModify();

        void onSuccessCommentDelete();
        void onNotFoundCommentDelete();

        void onAuthorization();
        void onForbidden(String msg);

        void onConnectFail();

        void updateContents(Data data);
        void updateProfileImages(List<Participant> participants);

        void onCommentPopup(CommentSet commentSet);
    }

    interface Presenter {
        void attachView(View view);
        void detachView();

        void setAdapterView(RepliesAdapterContract.View adapterView);
        void setAdapterModel(RepliesAdapterContract.Model adapterModel);

        void getPartyContents();
        void getParticipants();
        void getComments();

        void sendComment(String commentText);
        void deleteComment(String commentSlug);
        void updateComment(String commentSlug, String comment);

        void joinParty();
        void leaveParty();
        void deleteParty();

        void getOwner();
        void updateOwner(String owner);
    }
}